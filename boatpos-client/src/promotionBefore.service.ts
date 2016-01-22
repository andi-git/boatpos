import {PromotionBefore} from './promotionBefore';
import {Injectable} from 'angular2/core';
import {Http, Headers, HTTP_PROVIDERS} from 'angular2/http';
import 'rxjs/add/operator/map';
import {ConfigService} from "./config.service";
import {ObservableWrapper} from "angular2/src/facade/async";
import {Observable} from "rxjs/Observable";

@Injectable()
export class PromotionBeforeService {

    constructor(private http:Http, private configService:ConfigService) {
    }

    getPromotionsBefore():Observable<Array<PromotionBefore>> {
        return this.http.get(this.configService.getBackendUrl() + 'rest/promotion/before/enabled')
            .map(res => res.json())
            .map((promotions:Array<any>) => {
                let result:Array<PromotionBefore> = [];
                if (promotions) {
                    promotions.forEach((promotion) => {
                        result.push(new PromotionBefore(
                            promotion.id,
                            promotion.name,
                            promotion.timeCredit,
                            promotion.enabled,
                            promotion.priority,
                            promotion.keyBinding));
                    });
                }
                return result;
            });
    }
}