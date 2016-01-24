import {Boat, BoatCount} from './boat';
import {Injectable} from 'angular2/core';
import {Http, Headers, HTTP_PROVIDERS} from 'angular2/http';
import 'rxjs/add/operator/map';
import {ConfigService} from "./config.service";
import {ObservableWrapper} from "angular2/src/facade/async";
import {Observable} from "rxjs/Observable";

@Injectable()
export class BoatService {

    private boatsCache:Boat[];

    // constructors do dependency injection in Angular2
    constructor(private http:Http, private configService:ConfigService) {
        // when configuration is finished, load and cache boats
        this.configService.isConfigured().subscribe((config) => {
            console.log("constructor of BoatService");
            this.loadBoats().subscribe(boats => this.boatsCache = boats)
        });
    }

    private loadBoats():Observable<Array<Boat>> {
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

    getBoatCount():Observable<Array<BoatCount>> {
        return this.http.get(this.configService.getBackendUrl() + 'rest/boat/count')
            .map((res) => {
                return res.json()
            })
            .map((boatCounts:Array<any>) => {
                let result:Array<BoatCount> = [];
                if (boatCounts) {
                    boatCounts.forEach((boatCount) => {
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

    getBoats():Array<Boat> {
        return this.boatsCache;
    }

    getBoatByKeyBinding(keyBinding:string):Boat {
        let boat:Boat = null;
        this.getBoats().forEach((b) => {
            if (b.keyBinding == keyBinding) {
                boat = b;
            }
        });
        return boat;
    }

    resetSelected() {
        this.getBoats().forEach(boat => boat.selected = false);
    }

    getSelectedBoat():Boat {
        var boatSelected:Boat = null;
        this.getBoats().forEach(boat => {
            if (boat.selected === true) {
                boatSelected = boat;
            }
        });
        return boatSelected;
    }
}