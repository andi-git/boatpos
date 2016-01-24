import {Injectable} from 'angular2/core';
import {Http, Headers, HTTP_PROVIDERS} from 'angular2/http';
import 'rxjs/add/operator/map';
import {ConfigService} from "./config.service";
import {Observable} from "rxjs/Observable";
import {Departure} from "./departure";
import {Rental} from "./rental";

@Injectable()
export class RentalService {

    constructor(private http:Http, private configService:ConfigService) {
    }

    departe(departe:Departure):Observable<Rental> {
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        return this.http.post(
                this.configService.getBackendUrl() + 'rest/departure/depart', JSON.stringify(departe), {headers: headers}
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
}