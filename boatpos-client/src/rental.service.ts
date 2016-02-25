import {Injectable} from 'angular2/core';
import {Http, Headers, HTTP_PROVIDERS} from 'angular2/http';
import 'rxjs/add/operator/map';
import {ConfigService} from "./config.service";
import {Observable} from "rxjs/Observable";
import {Departure} from "./departure";
import {Rental} from "./rental";
import {Boat} from "./boat";
import {Payment} from "./payment";
import {Arrival} from "./arrival";

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
                    this.createDate(rentalBean.day),
                    rentalBean.boatBean,
                    this.createDate(rentalBean.departure),
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
                return this.convertRentalBeanTorRental(rentalBean);
            });
    }

    deleteRental(dayNumber:number):Observable<Rental> {
        return this.http.delete(
                this.configService.getBackendUrl() + 'rest/rental/' + dayNumber, {headers: this.headers})
            .map(res => res.json())
            .map((rentalBean) => {
                return this.convertRentalBeanTorRental(rentalBean);
            });
    }

    undoDeleteRental(dayNumber:number):Observable<Rental> {
        return this.http.get(
                this.configService.getBackendUrl() + 'rest/rental/undoDelete/' + dayNumber, {headers: this.headers})
            .map(res => res.json())
            .map((rentalBean) => {
                return this.convertRentalBeanTorRental(rentalBean);
            });
    }

    getRental(dayNumber:number):Observable<Rental> {
        return this.http.get(
                this.configService.getBackendUrl() + 'rest/rental/' + dayNumber, {headers: this.headers})
            .map(res => res.json())
            .map((rentalBean) => {
                return this.convertRentalBeanTorRental(rentalBean);
            });
    }

    arrive(dayNumber:number):Observable<Rental> {
        return this.http.post(
                this.configService.getBackendUrl() + 'rest/arrival/arrive', JSON.stringify(new Arrival(dayNumber)), {headers: this.headers})
            .map(res => res.json())
            .map((rentalBean) => {
                return this.convertRentalBeanTorRental(rentalBean);
            });
    }

    payAfter(payment:Payment):Observable<Rental> {
        return this.http.post(
                this.configService.getBackendUrl() + 'rest/arrival/pay', JSON.stringify(payment), {headers: this.headers}
            )
            .map(res => res.json())
            .map((rentalBean) => {
                return this.convertRentalBeanTorRental(rentalBean);
            });
    }

    private convertRentalBeanTorRental(rentalBean):Rental {
        return new Rental(
            rentalBean.dayId,
            this.createDate(rentalBean.day),
            rentalBean.boatBean,
            this.createDate(rentalBean.departure),
            this.createDate(rentalBean.arrival),
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

    private createDate(jsonDate:string):Date {
        let date:Date = new Date(jsonDate);
        date.setUTCHours(date.getUTCHours());
        date.setUTCDate(date.getUTCDate());
        date.setUTCMonth(date.getUTCMonth() + 1);
        return date;
    }
}