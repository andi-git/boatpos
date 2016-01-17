import {BoatCompact} from './boat';
import {BOATS_COMPACT} from './mock-boats';
import {Injectable} from 'angular2/core';
import {Http, Headers, HTTP_PROVIDERS} from 'angular2/http';
import 'rxjs/add/operator/map';

@Injectable()
export class BoatService {

    // constructors do dependency injection in Angular2
    constructor(private http:Http) {

    }

    getBoats() {
        console.log("getBoats()");
        // call the rest-service
        return this.http.get('http://home.com:8180/boatpos-server/rest/boat')
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
            })
    }
}