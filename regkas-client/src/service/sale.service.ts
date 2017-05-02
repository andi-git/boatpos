import {Injectable} from "angular2/core";
import {InfoService} from "./info.service";
import {ReceiptElement} from "../model/receiptElement";
import {isPresent} from "angular2/src/facade/lang";
import {Product} from "../model/product";
import {Http} from "angular2/http";
import {ConfigService} from "./config.service";
import {Sale} from "../model/sale";
import {Bill, Company, TaxSetElement} from "../model/bill";
import {Printer} from "../printer";
import {ErrorService} from "./error.service";
import {Observable} from "rxjs";
import {JournalService} from "./journal.service";
import {Income} from "../model/income";
import {PrettyPrinter} from "../prettyprinter";

@Injectable()
export class SaleService {

    private numberInput: string = "";

    private receiptElements: Array<ReceiptElement> = [];

    private signatureDeviceAvailableText: string = "";

    constructor(private http: Http, private infoService: InfoService, private errorService: ErrorService, private configService: ConfigService, private printer: Printer, private pp: PrettyPrinter) {

    }

    getNumberInput(): string {
        return this.numberInput;
    }

    setNumberInput(numberInput: string) {
        if ('<' === numberInput) {
            if (isPresent(this.numberInput) && this.numberInput.length > 0) {
                this.numberInput = this.numberInput.substr(0, this.numberInput.length - 1);
                this.infoService.event().emit("Letzte Eingabe gelöscht.");
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

    public getSignatureDeviceAvailableText(): string {
        return this.signatureDeviceAvailableText;
    }

    private setSignatureDeviceAvailableText(available: boolean, date: Date): void {
        if (available === false) {
            this.signatureDeviceAvailableText = this.pp.printDateAndTime(date) + ' Signatureinrichtung ist ausgefallen - Meldung an BMF wenn länger als 48 Stunden!';
        } else {
            this.signatureDeviceAvailableText = "";
        }
    }

    bill() {
        if (isPresent(this.receiptElements) && this.receiptElements.length > 0) {
            this.sale("Standard-Beleg", this.receiptElements);
        } else {
            this.errorService.event().emit("Rechnung wurde nicht gedruckt - kein Produkt ausgewählt.");
        }
    }

    cancelBill() {
        if (isPresent(this.receiptElements) && this.receiptElements.length > 0) {
            this.receiptElements.forEach(re => {
                re.totalPrice = (-1) * re.totalPrice;
            });
            this.sale("Storno-Beleg", this.receiptElements);
        } else {
            this.errorService.event().emit("Rechnung wurde nicht gedruckt - kein Produkt ausgewählt.");
        }
    }

    private sale(receiptType: string, receiptElements?: Array<ReceiptElement>) {
        this.http.post(this.configService.getBackendUrl() + 'rest/sale', JSON.stringify(new Sale("CASH", receiptType, receiptElements)), {headers: this.configService.getDefaultHeader()})
            .map(res => {
                return res.json();
            })
            .map((billBean) => {
                return this.convertBillBeanToBill(billBean);
            }).subscribe((bill: Bill) => {
                this.setSignatureDeviceAvailableText(bill.signatureDeviceAvailable, bill.receiptDateAndTime);
                this.printer.printBill(bill, this.configService.getPrinterIp());
                this.reset();
                this.infoService.event().emit("Rechnung '" + bill.receiptIdentifier + "' wurde gedruckt.");
            }
            , error => {
                this.reset();
                console.log("error: " + JSON.stringify(error));
                this.errorService.event().emit("Rechnung konnte NICHT erstellt werden - Vorgang wurde abgebrochen!");
            });
    }

    startBeleg() {
        this.checkIfStarbelegMustBePrinted().subscribe(check => {
            if (check === true) {
                this.sale("Start-Beleg");
            }
        });
    }

    nullBeleg() {
        this.sale("Null-Beleg");
    }

    tagesBeleg() {
        this.sale("Tages-Beleg");
    }

    monatsBeleg() {
        this.sale("Monats-Beleg");
    }

    jahresBeleg() {
        this.sale("Jahres-Beleg");
    }

    reset() {
        this.deleteNumberInput();
        this.cancelAllElements();
    }

    getReceiptElements(): Array<ReceiptElement> {
        return this.receiptElements;
    }

    deleteNumberInput() {
        this.numberInput = "";
        this.infoService.event().emit("Eingabe gelöscht.");
    }

    chooseProduct(product: Product) {
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

    getSum(): number {
        let sum: number = 0;
        this.receiptElements.forEach(re => sum += re.totalPrice);
        return sum;
    }

    public checkIfStarbelegMustBePrinted(): Observable<boolean> {
        // call the rest-service
        return this.http.get(this.configService.getBackendUrl() + 'rest/receipt/start/check', {headers: this.configService.getDefaultHeader()})
            .map(res => res.text() === 'true');
    }

    private convertBillBeanToBill(billBean): Bill {
        let taxSetElements: Array<TaxSetElement> = [];
        billBean.billTaxSetElements.forEach(tse => {
            taxSetElements.push(new TaxSetElement(
                tse.name,
                tse.taxPercent,
                tse.amount,
                tse.pricePreTax,
                tse.priceAfterTax,
                tse.priceTax
            ))
        });
        let sammelBeleg: Bill = null;
        let tagesBeleg: Bill = null;
        let monatsBeleg: Bill = null;
        let jahresBeleg: Bill = null;
        let income: Income = null;
        if (billBean.sammelBeleg != null) {
            sammelBeleg = this.convertBillBeanToBill(billBean.sammelBeleg);
        }
        if (billBean.tagesBeleg != null) {
            tagesBeleg = this.convertBillBeanToBill(billBean.tagesBeleg);
        }
        if (billBean.monatsBeleg != null) {
            monatsBeleg = this.convertBillBeanToBill(billBean.monatsBeleg);
        }
        if (billBean.jahresBeleg != null) {
            jahresBeleg = this.convertBillBeanToBill(billBean.jahresBeleg);
        }
        if (billBean.incomeBean != null) {
            income = JournalService.convertToIncome(billBean.incomeBean);
        }
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
            taxSetElements,
            sammelBeleg,
            new Date(billBean.sammelBelegStart),
            new Date(billBean.sammelBelegEnd),
            income,
            tagesBeleg,
            monatsBeleg,
            jahresBeleg,
            billBean.receiptType,
            billBean.jwsCompact,
            billBean.signatureDeviceAvailable
        );
    };
}
