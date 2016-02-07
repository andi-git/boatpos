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
    private dateTime:string = "Datum: ";

    constructor(private boatService:BoatService, private configService:ConfigService, private pp:PrettyPrinter) {
        this.updateDateTime();
    }

    getNextDayNumber():string {
        return this.boatService.getNextDayNumber();
    }

    getBoatCounts():Array<BoatCount> {
        return this.boatService.getBoatCounts();
    }

    private updateDateTime() {
        let date:Date = new Date();
        this.dateTime = "Datum: " +
            this.pp.pp2Pos(date.getDate()) + ". " +
            this.pp.pp2Pos(date.getMonth() + 1) + ". " +
            date.getFullYear() + ", " +
            this.pp.pp2Pos(date.getHours()) + ":" +
            this.pp.pp2Pos(date.getMinutes());
        setTimeout(() => this.updateDateTime(), 60000);
    }
}