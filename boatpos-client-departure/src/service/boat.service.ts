import {Boat, BoatCount} from "../model/boat";
import {Injectable} from "angular2/core";
import {Http, RequestOptions} from "angular2/http";
import "rxjs/add/operator/map";
import {ConfigService} from "./config.service";
import {Observable} from "rxjs/Observable";

@Injectable()
export class BoatService {

    private boatsCache:Boat[];

    private boatCounts:BoatCount[];

    private nextDayNumber;

    // constructors do dependency injection in Angular2
    constructor(private http:Http, private configService:ConfigService) {
        // when configuration is finished, load and cache boats
        this.configService.isConfigured().subscribe((config) => {
            console.log("constructor of BoatService");
            this.loadBoats().subscribe(boats => this.boatsCache = boats);
            this.updateStats();
        });
    }

    private loadBoats():Observable<Array<Boat>> {
        // call the rest-service
        return this.http.get(this.configService.getBackendUrl() + 'rest/boat/enabled', {headers : this.configService.getDefaultHeader()})
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
                            boat.enabled,
                            boat.priority,
                            boat.shortName,
                            boat.pictureUrl,
                            boat.pictureUrlThumb,
                            boat.keyBinding));
                    });
                }
                return result;
            });
    }

    private loadBoatCount() {
        this.http.get(this.configService.getBackendUrl() + 'rest/boat/count', {headers: this.configService.getDefaultHeader()})
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
            })
            .subscribe((boatCounts:Array<BoatCount>) => {
                this.boatCounts = boatCounts;
            });
    }

    getBoats():Array<Boat> {
        return this.boatsCache;
    }

    getBoatCounts():Array<BoatCount> {
        return this.boatCounts;
    }

    getNextDayNumber():string {
        return this.nextDayNumber;
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

    getBoatByShortName(shortName:string):Boat {
        let boat:Boat = null;
        this.getBoats().forEach((b) => {
            if (b.shortName == shortName) {
                boat = b;
            }
        });
        return boat;
    }

    getBoatByName(name:string):Boat {
        let boat:Boat = null;
        this.getBoats().forEach((b) => {
            if (b.name == name) {
                boat = b;
            }
        });
        return boat;
    }

    resetSelected() {
        this.getBoats().forEach(boat => boat.setSelected(false));
    }

    reset() {
        this.resetSelected();
        this.loadBoatCount();
        this.loadNextDayNumber();
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

    private loadNextDayNumber() {
        this.http.get(this.configService.getBackendUrl() + 'rest/rental/nextId', {headers: this.configService.getDefaultHeader()})
            .map((res) => {
                return res.json()
            })
            .subscribe((result:any) => {
                this.nextDayNumber = "";
                let nextDayNumber = result.dayNumber;
                if (nextDayNumber < 100) {
                    nextDayNumber = "0" + nextDayNumber;
                }
                if (nextDayNumber < 10) {
                    nextDayNumber = "0" + nextDayNumber;
                }
                this.nextDayNumber = nextDayNumber;
            });
    }

    updateStats() {
        this.loadNextDayNumber();
        this.loadBoatCount();
    }
}