import {Component} from 'angular2/core';
import {BoatCount} from './boat';
import {BoatService} from "./boat.service";
import {ConfigService} from "./config.service";

@Component({
    selector: 'boatCount',
    templateUrl: "boatCount.component.html",
    styleUrls: ["boatCount.component.css"]
})
export class BoatCountComponent {

    private boatCounts:BoatCount[];
    private subscription:any;

    constructor(private boatService:BoatService, private configService:ConfigService) {
    }

    getNextDayNumber():string {
        return this.boatService.getNextDayNumber();
    }

    getBoatCounts():Array<BoatCount> {
        return this.boatService.getBoatCounts();
    }
}