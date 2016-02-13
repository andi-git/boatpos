import {Component} from 'angular2/core';
import {BoatCount} from './boat';
import {BoatService} from "./boat.service";
import {ConfigService} from "./config.service";
import {PrettyPrinter} from "./prettyprinter";

@Component({
    selector: 'boatCount',
    templateUrl: "boatCount.component.html",
    styleUrls: ["boatCount.component.css"]
})
export class BoatCountComponent {

    private boatCounts:BoatCount[];
    private subscription:any;

    constructor(private boatService:BoatService, private configService:ConfigService, private pp:PrettyPrinter) {
    }

    getBoatCounts():Array<BoatCount> {
        return this.boatService.getBoatCounts();
    }
}