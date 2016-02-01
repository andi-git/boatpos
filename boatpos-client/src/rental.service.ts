import {Injectable} from 'angular2/core';
import {Http, Headers, HTTP_PROVIDERS} from 'angular2/http';
import 'rxjs/add/operator/map';
import {ConfigService} from "./config.service";
import {Observable} from "rxjs/Observable";
import {Departure} from "./departure";
import {Rental} from "./rental";
import {Boat} from "./boat";

@Injectable()
export class RentalService {

    private headers:Headers = new Headers();

    constructor(private http:Http, private configService:ConfigService) {
        this.headers.append('Content-Type', 'application/json');
    }

    departe(departe:Departure):Observable<Rental> {
        return this.http.post(
                this.configService.getBackendUrl() + 'rest/departure/depart', JSON.stringify(departe), {headers: this.headers}
            )
            .map(res => res.json())
            .map((rentalBean) => {
                return Rental.fromDeparte(
                    rentalBean.dayId,
                    rentalBean.day,
                    rentalBean.boatBean,
                    rentalBean.departure,
                    rentalBean.commitmentBeans,
                    rentalBean.promotionBeforeBean,
                    rentalBean.coupon,
                    rentalBean.priceCalculatedBefore);
            });
    }

    deleteRental(dayNumber:number):Observable<Rental> {
        return this.http.delete(
                this.configService.getBackendUrl() + 'rest/rental/' + dayNumber, {headers: this.headers})
            .map(res => res.json())
            .map((rentalBean) => {
                return new Rental(
                    rentalBean.dayId,
                    rentalBean.day,
                    rentalBean.boatBean,
                    rentalBean.departure,
                    rentalBean.arrival,
                    rentalBean.pricePaidAfter,
                    rentalBean.pricePaidBefore,
                    rentalBean.priceCalculatedAfter,
                    rentalBean.priceCalculatedBefore,
                    rentalBean.finished,
                    rentalBean.deleted,
                    rentalBean.coupon,
                    rentalBean.promotionBeforeBean,
                    rentalBean.promotionAfterBean,
                    rentalBean.commitmentBeans);
            });
    }

    getRental(dayNumber:number):Observable<Rental> {
        return this.http.get(
                this.configService.getBackendUrl() + 'rest/rental/' + dayNumber, {headers: this.headers})
            .map(res => res.json())
            .map((rentalBean) => {
                return new Rental(
                    rentalBean.dayId,
                    rentalBean.day,
                    rentalBean.boatBean,
                    rentalBean.departure,
                    rentalBean.arrival,
                    rentalBean.pricePaidAfter,
                    rentalBean.pricePaidBefore,
                    rentalBean.priceCalculatedAfter,
                    rentalBean.priceCalculatedBefore,
                    rentalBean.finished,
                    rentalBean.deleted,
                    rentalBean.coupon,
                    rentalBean.promotionBeforeBean,
                    rentalBean.promotionAfterBean,
                    rentalBean.commitmentBeans);
            });
    }
}