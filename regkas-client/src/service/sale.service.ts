import {Injectable} from "angular2/core";
import {InfoService} from "./info.service";
import {ReceiptElement} from "../model/receiptElement";
import {isPresent} from "angular2/src/facade/lang";
import {Product} from "../model/product";
import {Http} from "angular2/http";
import {ConfigService} from "./config.service";
import {Sale} from "../model/sale";
import {Company, TaxSetElement, Bill} from "../model/bill";
import {Printer} from "../printer";

@Injectable()
export class SaleService {

    private numberInput:string = "";

    private receiptElements:Array<ReceiptElement> = [];

    constructor(private http:Http, private infoService:InfoService, private configService:ConfigService, private printer:Printer) {

    }

    getNumberInput():string {
        return this.numberInput;
    }

    setNumberInput(numberInput:string) {
        if ('<' === numberInput) {
            if (isPresent(this.numberInput) && this.numberInput.length > 0) {
                this.numberInput = this.numberInput.substr(0, this.numberInput.length - 1);
                this.infoService.event().emit("Letzte Einfabe gelöscht.");
            }
        } else {
            this.numberInput = (this.numberInput == null ? "" : this.numberInput) + numberInput;
            this.infoService.event().emit("Nummer eingegeben.");
        }
    }

    cancelLastElement() {
        this.receiptElements.pop();
        this.infoService.event().emit("Letztes Element gelöscht.");
    }

    cancelAllElements() {
        this.receiptElements = [];
        this.infoService.event().emit("Alle Elemente gelöscht.");
    }

    bill() {
        if (isPresent(this.receiptElements) && this.receiptElements.length > 0) {
            this.http.post(
                    this.configService.getBackendUrl() + 'rest/sale', JSON.stringify(new Sale("CASH", "Standard-Beleg", this.receiptElements)), {headers: this.configService.getDefaultHeader()}
                )
                .map(res => res.json())
                .map((billBean) => {
                    return this.convertBillBeanToBill(billBean);
                }).subscribe((bill:Bill) => {
                    console.log("print bill");
                    this.printer.printBill(bill);
                    this.reset();
                    this.infoService.event().emit("Rechnung '" + bill.receiptIdentifier + "' wurde gedruckt.");
                }
            );
        } else {
            this.infoService.event().emit("Rechnung wurde nicht gedruckt - kein Produkt ausgewählt.");
        }
    }

    reset() {
        this.deleteNumberInput();
        this.cancelAllElements();
    }

    getReceiptElements():Array<ReceiptElement> {
        return this.receiptElements;
    }

    deleteNumberInput() {
        this.numberInput = "";
        this.infoService.event().emit("Eingabe gelöscht.");
    }

    chooseProduct(product:Product) {
        if (isPresent(product)) {
            if (product.generic === true) {
                if (isPresent(this.numberInput) && this.numberInput != "") {
                    this.receiptElements.push(new ReceiptElement(Number.parseFloat(this.numberInput), 1, product));
                    this.numberInput = "";
                    this.infoService.event().emit("Produktgruppe '" + product.name + "' ausgewählt.");
                } else {
                    this.infoService.event().emit("Kein Preis für die Produktgruppe '" + product.name + "' ausgewählt.");
                }
            } else {
                let amount = 1;
                if (isPresent(this.numberInput) && this.numberInput != "") {
                    amount = Number.parseFloat(this.numberInput);
                }
                this.receiptElements.push(new ReceiptElement(Number.parseFloat(product.price * amount), amount, product));
                this.numberInput = "";
                this.infoService.event().emit("Produkt '" + product.name + "' ausgewählt.");
            }
        } else {
            this.infoService.event().emit("Kein Produkt ausgewählt.");
        }
    }

    getSum():number {
        let sum:number = 0;
        this.receiptElements.forEach(re => sum += re.totalPrice);
        return sum;
    }

    private convertBillBeanToBill(billBean):Bill {
        let taxSetElements:Array<TaxSetElement> = [];
        billBean.billTaxSetElements.forEach(tse => {
            taxSetElements.push(new TaxSetElement(
                tse.name,
                tse.taxPercent,
                tse.priority,
                tse.pricePreTax,
                tse.priceAfterTax,
                tse.priceTax
            ))
        });
        return new Bill(
            billBean.cashBoxID,
            billBean.receiptIdentifier,
            new Date(billBean.receiptDateAndTime),
            billBean.sumTaxSetNormal,
            billBean.sumTaxSetErmaessigt1,
            billBean.sumTaxSetErmaessigt2,
            billBean.sumTaxSetNull,
            billBean.sumTaxSetBesonders,
            billBean.encryptedTurnoverValue,
            billBean.signatureCertificateSerialNumber,
            billBean.signatureValuePreviousReceipt,
            new Company(
                billBean.company.name,
                billBean.company.street,
                billBean.company.zip,
                billBean.company.city,
                billBean.company.country,
                billBean.company.phone,
                billBean.company.mail,
                billBean.company.atu
            ),
            billBean.sumTotal,
            taxSetElements
        );
    };
}
