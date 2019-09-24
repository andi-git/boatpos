import {Injectable} from "angular2/core";
import {Http} from "angular2/http";
import "rxjs/add/operator/map";
import {ConfigService} from "./config.service";
import {Observable} from "rxjs/Observable";
import {Departure} from "../model/departure";
import {Rental} from "../model/rental";
import {Payment} from "../model/payment";
import {Arrival} from "../model/arrival";
import {Bill, Company, TaxSetElement} from "../model/bill";
import {PromotionService} from "./promotion.service";
import {AddPromotion, RemovePromotionsAfter} from "../model/promotion";
import {Income, IncomeProductGroup, TaxElement} from "../model/income";
import {InfoService} from "./info.service";
import {ErrorService} from "./error.service";
import {Printer} from "../printer";
import {PrettyPrinter} from "../prettyprinter";
import {isPresent} from "angular2/src/facade/lang";
import {JournalService} from "./journal.service";
import {JournalReport} from "../model/journalReport";

@Injectable()
export class RentalService {

    private signatureDeviceAvailableText: string = "";

    constructor(private http: Http, private configService: ConfigService, private promotionService: PromotionService, private errorService: ErrorService, private infoService: InfoService, private printer: Printer, private pp: PrettyPrinter, private journalService: JournalService) {
    }

    depart(depart: Departure): Observable<Rental> {
        return this.http.post(
            this.configService.getBackendUrl() + 'rest/departure/depart', JSON.stringify(depart), {headers: this.configService.getDefaultHeader()}
        )
            .map(res => res.json())
            .map((rentalBean) => {
                return Rental.fromDepart(
                    rentalBean.dayId,
                    RentalService.createDate(rentalBean.day),
                    rentalBean.boatBean,
                    RentalService.createDateTime(rentalBean.departure),
                    rentalBean.commitmentBeans,
                    rentalBean.promotionBeforeBean,
                    rentalBean.coupon,
                    rentalBean.priceCalculatedBefore,
                    rentalBean.myRentalId);
            });
    }

    payBefore(payment: Payment): Observable<Rental> {
        return this.http.post(
            this.configService.getBackendUrl() + 'rest/departure/pay', JSON.stringify(payment), {headers: this.configService.getDefaultHeader()}
        )
            .map(res => res.json())
            .map((rentalBean) => {
                return this.convertRentalBeanToRental(rentalBean);
            });
    }

    deleteRental(dayNumber: number): Observable<Rental> {
        return this.http.delete(
            this.configService.getBackendUrl() + 'rest/rental/' + dayNumber, {headers: this.configService.getDefaultHeader()})
            .map(res => res.json())
            .map((rentalBean) => {
                return this.convertRentalBeanToRental(rentalBean);
            });
    }

    undoDeleteRental(dayNumber: number): Observable<Rental> {
        return this.http.get(
            this.configService.getBackendUrl() + 'rest/rental/undoDelete/' + dayNumber, {headers: this.configService.getDefaultHeader()})
            .map(res => res.json())
            .map((rentalBean) => {
                return this.convertRentalBeanToRental(rentalBean);
            });
    }

    getRental(dayNumber: number): Observable<Rental> {
        return this.http.get(
            this.configService.getBackendUrl() + 'rest/rental/' + dayNumber, {headers: this.configService.getDefaultHeader()})
            .map(res => res.json())
            .map((rentalBean) => {
                return this.convertRentalBeanToRental(rentalBean);
            });
    }

    arrive(dayNumber: number): Observable<Rental> {
        return this.http.post(
            this.configService.getBackendUrl() + 'rest/arrival/arrive', JSON.stringify(new Arrival(dayNumber)), {headers: this.configService.getDefaultHeader()})
            .map(res => res.json())
            .map((rentalBean) => {
                return this.convertRentalBeanToRental(rentalBean);
            });
    }

    addHolliKnolli(rental: Rental): Observable<Rental> {
        return this.http.post(
            this.configService.getBackendUrl() + 'rest/arrival/promotion', JSON.stringify(new AddPromotion(rental.dayId, this.promotionService.getHolliKnolli().id)), {headers: this.configService.getDefaultHeader()})
            .map(res => res.json())
            .map((rentalBean) => {
                return this.convertRentalBeanToRental(rentalBean);
            });
    }

    removeHolliKnolli(rental: Rental): Observable<Rental> {
        return this.http.put(
            this.configService.getBackendUrl() + 'rest/arrival/promotion', JSON.stringify(new RemovePromotionsAfter(rental.dayId)), {headers: this.configService.getDefaultHeader()})
            .map(res => res.json())
            .map((rentalBean) => {
                return this.convertRentalBeanToRental(rentalBean);
            });
    }

    payAfter(payment: Payment): Observable<Bill> {
        return this.http.post(this.configService.getBackendUrl() + 'rest/arrival/pay', JSON.stringify(payment), {headers: this.configService.getDefaultHeader()})
            .map(res => {
                console.log(res.json());
                return res.json();
            })
            .map((billBean) => {
                console.log("billBean: " + billBean);
                return this.convertBillBeanToBill(billBean);
            });
    }

    startBeleg() {
        this.checkIfStarbelegMustBePrinted().subscribe(check => {
            if (check === true) {
                this.receipt("Start-Beleg");
            }
        });
    }

    schlussBeleg() {
        this.receipt("Schluss-Beleg");
    }

    nullBeleg() {
        this.receipt("Null-Beleg");
    }

    tagesBeleg() {
        this.receipt("Tages-Beleg", this.currentYear(), this.currentMonth(), this.currentDay());
    }

    monatsBeleg() {
        this.receipt("Monats-Beleg", this.currentYear(), this.currentMonth());
    }

    jahresBeleg() {
        this.receipt("Jahres-Beleg", this.currentYear());
    }

    //noinspection JSMethodCanBeStatic
    private currentDay(): number {
        return new Date(Date.now()).getDate();
    }

    //noinspection JSMethodCanBeStatic
    private currentMonth(): number {
        return new Date(Date.now()).getMonth() + 1;
    }

    //noinspection JSMethodCanBeStatic
    private currentYear(): number {
        return new Date(Date.now()).getFullYear();
    }

    private receipt(receiptType: string, withJournalYear?: number, withJournalMonth?: number, withJournalDay?: number): void {
        this.http.post(this.configService.getBackendUrl() + 'rest/arrival/receipt', receiptType, {headers: this.configService.getDefaultHeader()})
            .map(res => {
                return res.json();
            })
            .map((billBean) => {
                return this.convertBillBeanToBill(billBean);
            }).subscribe((bill: Bill) => {
                if (isPresent(withJournalYear)) {
                    this.journalService.income(withJournalYear, withJournalMonth, withJournalDay).subscribe(
                        (journalReport: JournalReport) => {
                            this.printer.printBill(bill, this.configService.getPrinterIp(), journalReport);
                        }
                    )
                } else {
                    this.printer.printBill(bill, this.configService.getPrinterIp());
                }
                this.infoService.event().emit("Rechnung '" + bill.receiptIdentifier + "' wurde gedruckt.");
            }
            , error => {
                console.log("error: " + JSON.stringify(error));
                this.errorService.event().emit("Rechnung konnte NICHT erstellt werden - Vorgang wurde abgebrochen!");
            });
    }

    public checkIfStarbelegMustBePrinted(): Observable<boolean> {
        // call the rest-service
        return this.http.get(this.configService.getBackendUrl() + 'rest/arrival/start/check', {headers: this.configService.getDefaultHeader()})
            .map(res => res.text() === 'true');
    }

    loadAllForCurrentDay(): Observable<Array<Rental>> {
        return this.http.get(this.configService.getBackendUrl() + 'rest/rental/currentDay', {headers: this.configService.getDefaultHeader()})
        // map the result to json
            .map(res => res.json())
            // map the result to Boat
            .map((rentals: Array<any>) => {
                let result: Array<Rental> = [];
                if (rentals) {
                    rentals.forEach((rental) => {
                        result.push(this.convertRentalBeanToRental(rental));
                    });
                }
                return result;
            })
    }

    loadAllFor(year: number, month: number, day: number): Observable<Array<Rental>> {
        return this.http.get(this.configService.getBackendUrl() + 'rest/rental/' + year + '/' + month + '/' + day, {headers: this.configService.getDefaultHeader()})
        // map the result to json
            .map(res => res.json())
            // map the result to Boat
            .map((rentals: Array<any>) => {
                let result: Array<Rental> = [];
                if (rentals) {
                    rentals.forEach((rental) => {
                        result.push(this.convertRentalBeanToRental(rental));
                    });
                }
                return result;
            })
    }

    //noinspection JSMethodCanBeStatic
    private convertRentalBeanToRental(rentalBean): Rental {
        return new Rental(
            rentalBean.dayId,
            RentalService.createDate(rentalBean.day),
            rentalBean.boatBean,
            RentalService.createDateTime(rentalBean.departure),
            RentalService.createDateTime(rentalBean.arrival),
            rentalBean.pricePaidAfter,
            rentalBean.pricePaidBefore,
            rentalBean.priceCalculatedAfter,
            rentalBean.priceCalculatedBefore,
            rentalBean.finished,
            rentalBean.deleted,
            rentalBean.coupon,
            rentalBean.promotionBeforeBean,
            rentalBean.promotionAfterBean,
            rentalBean.commitmentBeans,
            rentalBean.timeOfTravel,
            rentalBean.timeOfTravelCalculated,
            rentalBean.receiptId,
            rentalBean.myRentalId);
    };

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
            income = this.convertToIncome(billBean.incomeBean);
        }
        return new Bill(
            billBean.cashBoxID,
            billBean.receiptIdentifier,
            RentalService.createDateTime(billBean.receiptDateAndTime),
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

    public convertToIncome(incomeBean: any): Income {
        let incomeProductGroups: Array<IncomeProductGroup> = [];
        incomeBean.incomeElements.forEach(
            pg => incomeProductGroups.push(new IncomeProductGroup(
                pg.name,
                pg.income,
                pg.taxPercent,
                pg.priority
            ))
        );
        let taxElements: Array<TaxElement> = [];
        incomeBean.taxElements.forEach(
            te => taxElements.push(new TaxElement(
                te.taxPercent,
                te.priority,
                te.price,
                te.priceBeforeTax,
                te.priceTax
            ))
        );
        return new Income(
            RentalService.createDate(incomeBean.start),
            RentalService.createDate(incomeBean.end),
            incomeBean.totalIncome,
            incomeProductGroups,
            taxElements);
    }

    public static createDate(jsonDate: string): Date {
        return new Date(jsonDate + "T00:00:00.000Z");
    }

    public static createDateTime(jsonDateTime: string): Date {
        return new Date(jsonDateTime);
    }

    public getSignatureDeviceAvailableText(): string {
        return this.signatureDeviceAvailableText;
    }

    public setSignatureDeviceAvailableText(available: boolean, date: Date): void {
        console.log('setSignatureDeviceAvailableText: ' + available + ', ' + date);
        if (available === false) {
            console.log('    1');
            this.signatureDeviceAvailableText = this.pp.printDateAndTime(date) + ' Signatureinrichtung ist ausgefallen - Meldung an BMF wenn länger als 48 Stunden!';
        } else {
            console.log('    2');
            this.signatureDeviceAvailableText = "";
        }
    }

    public printReceipt(receiptId: String) {
        return this.http.get(
            this.configService.getBackendUrl() + 'rest/journal/receipt/id/' + receiptId, {headers: this.configService.getDefaultHeader()})
            .map(res => {
                return res.json();
            })
            .map((billBean) => {
                return this.convertBillBeanToBill(billBean);
            }).subscribe((bill: Bill) => {
                    this.printer.printBill(bill, this.configService.getPrinterIp());
                    this.infoService.event().emit("Rechnung '" + bill.receiptIdentifier + "' wurde gedruckt.");
                }
                , error => {
                    console.log("error: " + JSON.stringify(error));
                    this.errorService.event().emit("Rechnung konnte NICHT gedruckt werden - Vorgang wurde abgebrochen!");
                });
    }
}
