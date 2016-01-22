import {Boat, BoatCount} from './boat';
import {Injectable} from 'angular2/core';
import {Http, Headers, HTTP_PROVIDERS} from 'angular2/http';
import 'rxjs/add/operator/map';
import {ConfigService} from "./config.service";
import {ObservableWrapper} from "angular2/src/facade/async";
import {Observable} from "rxjs/Observable";

@Injectable()
export class BoatService {

    // constructors do dependency injection in Angular2
    constructor(private http:Http, private configService:ConfigService) {
    }

    getBoats(): Observable<Array<Boat>> {
        // call the rest-service
        return this.http.get(this.configService.getBackendUrl() + 'rest/boat/enabled')
            // map the result to json
            .map(res => res.json())
            // map the result to Boat
            .map((boats:Array<any>) => {
                let result:Array<Boat> = [];
                if (boats) {
                    boats.forEach((boat) => {
                        result.push(new Boat(
                            boat.id,
                            boat.name,
                            boat.shortName,
                            boat.enabled,
                            boat.priority,
                            boat.pictureUrlSmall,
                            boat.pictureUrlMedium,
                            boat.keyBinding));
                    });
                }
                return result;
            });
    }


    getBoatCount(): Observable<Array<BoatCount>> {
        return this.http.get(this.configService.getBackendUrl() + 'rest/boat/count')
            .map((res) => {
                console.log("### " + res.json());
                console.log("### " + res.json().id);
                return res.json()
            })
            .map((boatCounts:Array<any>) => {
                let result:Array<BoatCount> = [];
                if (boatCounts) {
                    boatCounts.forEach((boatCount) => {
                        console.log("___ " + boatCount);
                        result.push(new BoatCount(
                            boatCount.id,
                            boatCount.name,
                            boatCount.shortName,
                            boatCount.count,
                            boatCount.max));
                    });
                }
                return result;
            });
    }
}