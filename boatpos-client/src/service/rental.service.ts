import {Injectable} from 'angular2/core';
import {Http, Headers, HTTP_PROVIDERS} from 'angular2/http';
import 'rxjs/add/operator/map';
import {ConfigService} from "./config.service";
import {Observable} from "rxjs/Observable";
import {Departure} from "../model/departure";
import {Rental} from "../model/rental";
import {Boat} from "../model/boat";
import {Payment} from "../model/payment";
import {Arrival} from "../model/arrival";
import {Bill} from "../model/bill";

@Injectable()
export class RentalService {

    private headers:Headers = new Headers();

    constructor(private http:Http, private configService:ConfigService) {
        this.headers.append('Content-Type', 'application/json');
    }

    depart(depart:Departure):Observable<Rental> {
        return this.http.post(
                this.configService.getBackendUrl() + 'rest/departure/depart', JSON.stringify(depart), {headers: this.headers}
            )
            .map(res => res.json())
            .map((rentalBean) => {
                return Rental.fromDepart(
                    rentalBean.dayId,
                    RentalService.createDate(rentalBean.day),
                    rentalBean.boatBean,
                    RentalService.createDate(rentalBean.departure),
                    rentalBean.commitmentBeans,
                    rentalBean.promotionBeforeBean,
                    rentalBean.coupon,
                    rentalBean.priceCalculatedBefore);
            });
    }

    payBefore(payment:Payment):Observable<Rental> {
        return this.http.post(
                this.configService.getBackendUrl() + 'rest/departure/pay', JSON.stringify(payment), {headers: this.headers}
            )
            .map(res => res.json())
            .map((rentalBean) => {
                return this.convertRentalBeanToRental(rentalBean);
            });
    }

    deleteRental(dayNumber:number):Observable<Rental> {
        return this.http.delete(
                this.configService.getBackendUrl() + 'rest/rental/' + dayNumber, {headers: this.headers})
            .map(res => res.json())
            .map((rentalBean) => {
                return this.convertRentalBeanToRental(rentalBean);
            });
    }

    undoDeleteRental(dayNumber:number):Observable<Rental> {
        return this.http.get(
                this.configService.getBackendUrl() + 'rest/rental/undoDelete/' + dayNumber, {headers: this.headers})
            .map(res => res.json())
            .map((rentalBean) => {
                return this.convertRentalBeanToRental(rentalBean);
            });
    }

    getRental(dayNumber:number):Observable<Rental> {
        return this.http.get(
                this.configService.getBackendUrl() + 'rest/rental/' + dayNumber, {headers: this.headers})
            .map(res => res.json())
            .map((rentalBean) => {
                return this.convertRentalBeanToRental(rentalBean);
            });
    }

    arrive(dayNumber:number):Observable<Rental> {
        return this.http.post(
                this.configService.getBackendUrl() + 'rest/arrival/arrive', JSON.stringify(new Arrival(dayNumber)), {headers: this.headers})
            .map(res => res.json())
            .map((rentalBean) => {
                return this.convertRentalBeanToRental(rentalBean);
            });
    }

    payAfter(payment:Payment):Observable<Rental> {
        return this.http.post(
                this.configService.getBackendUrl() + 'rest/arrival/pay', JSON.stringify(payment), {headers: this.headers}
            )
            .map(res => res.json())
            .map((billBean) => {
                return this.convertBillBeanToBill(billBean);
            });
    }

    loadAllForCurrentDay():Observable<Array<Rental>> {
        return this.http.get(this.configService.getBackendUrl() + 'rest/rental/currentDay')
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
            RentalService.createDate(rentalBean.departure),
            RentalService.createDate(rentalBean.arrival),
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
        return new Bill();
    };

    public static createDate(jsonDate:string):Date {
        let date:Date = new Date(jsonDate);
        date.setUTCHours(date.getUTCHours());
        date.setUTCDate(date.getUTCDate());
        date.setUTCMonth(date.getUTCMonth() + 1);
        return date;
    }
}