import {BoatCompact} from './boat';
import {BOATS_COMPACT} from './mock-boats';
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

    getBoats(): Observable<Array<BoatCompact>> {
        // call the rest-service
        return this.http.get(this.configService.getBackendUrl() + 'rest/boat')
            // map the result to json
            .map(res => res.json())
            // map the result to BoatCompact
            .map((boats:Array<any>) => {
                let result:Array<BoatCompact> = [];
                if (boats) {
                    boats.forEach((boat) => {
                        result.push(new BoatCompact(boat.id, boat.name, boat.shortName, boat.pictureUrlMedium));
                    });
                }
                return result;
            });
    }
}