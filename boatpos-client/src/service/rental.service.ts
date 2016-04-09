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

@Injectable()
export class RentalService {

    constructor(private http:Http, private configService:ConfigService) {
    }

    depart(depart:Departure):Observable<Rental> {
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
                    rentalBean.priceCalculatedBefore);
            });
    }

    payBefore(payment:Payment):Observable<Rental> {
        return this.http.post(
                this.configService.getBackendUrl() + 'rest/departure/pay', JSON.stringify(payment), {headers: this.configService.getDefaultHeader()}
            )
            .map(res => res.json())
            .map((rentalBean) => {
                return this.convertRentalBeanToRental(rentalBean);
            });
    }

    deleteRental(dayNumber:number):Observable<Rental> {
        return this.http.delete(
                this.configService.getBackendUrl() + 'rest/rental/' + dayNumber, {headers: this.configService.getDefaultHeader()})
            .map(res => res.json())
            .map((rentalBean) => {
                return this.convertRentalBeanToRental(rentalBean);
            });
    }

    undoDeleteRental(dayNumber:number):Observable<Rental> {
        return this.http.get(
                this.configService.getBackendUrl() + 'rest/rental/undoDelete/' + dayNumber, {headers: this.configService.getDefaultHeader()})
            .map(res => res.json())
            .map((rentalBean) => {
                return this.convertRentalBeanToRental(rentalBean);
            });
    }

    getRental(dayNumber:number):Observable<Rental> {
        return this.http.get(
                this.configService.getBackendUrl() + 'rest/rental/' + dayNumber, {headers: this.configService.getDefaultHeader()})
            .map(res => res.json())
            .map((rentalBean) => {
                return this.convertRentalBeanToRental(rentalBean);
            });
    }

    arrive(dayNumber:number):Observable<Rental> {
        return this.http.post(
                this.configService.getBackendUrl() + 'rest/arrival/arrive', JSON.stringify(new Arrival(dayNumber)), {headers: this.configService.getDefaultHeader()})
            .map(res => res.json())
            .map((rentalBean) => {
                return this.convertRentalBeanToRental(rentalBean);
            });
    }

    payAfter(payment:Payment):Observable<Bill> {
        return this.http.post(
                this.configService.getBackendUrl() + 'rest/arrival/pay', JSON.stringify(payment), {headers: this.configService.getDefaultHeader()}
            )
            .map(res => res.json())
            .map((billBean) => {
                return this.convertBillBeanToBill(billBean);
            });
    }

    loadAllForCurrentDay():Observable<Array<Rental>> {
        return this.http.get(this.configService.getBackendUrl() + 'rest/rental/currentDay', {headers: this.configService.getDefaultHeader()})
            // map the result to json
            .map(res => res.json())
            // map the result to Boat
            .map((rentals:Array<any>) => {
                let result:Array<Rental> = [];
                if (rentals) {
                    rentals.forEach((rental) => {
                        result.push(this.convertRentalBeanToRental(rental));
                    });
                }
                return result;
            })
    }

    private convertRentalBeanToRental(rentalBean):Rental {
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
            rentalBean.timeOfTravelCalculated);
    };

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
            taxSetElements
        );
    };

    public static createDate(jsonDate:string):Date {
        return new Date(jsonDate + "T00:00:00.000Z");
    }

    public static createDateTime(jsonDateTime:string):Date {
        return new Date(jsonDateTime);
    }
}