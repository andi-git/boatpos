import {Component} from 'angular2/core';
import {BoatCount} from './boat';
import {BoatService} from "./boat.service";
import {ConfigService} from "./config.service";
import {PrettyPrinter} from "./prettyprinter";

@Component({
    selector: 'statistik',
    templateUrl: "statistik.component.html",
    styleUrls: ["statistik.component.css"]
})
export class StatistikComponent {

    private subscription:any;
    private dateString:string = "Datum: ";
    private timeString:string = "Uhrzeit: ";

    constructor(private boatService:BoatService, private configService:ConfigService, private pp:PrettyPrinter) {
        this.updateDate();
        this.updateTime();
    }

    getNextDayNumber():string {
        return this.boatService.getNextDayNumber();
    }

    private updateDate() {
        let date:Date = new Date();
        this.dateString = "Datum: " +
            this.pp.pp2Pos(date.getDate()) + ". " +
            this.pp.pp2Pos(date.getMonth() + 1) + ". " +
            date.getFullYear()
    }

    private updateTime() {
        let date:Date = new Date();
        this.timeString = "Uhrzeit: " +
            this.pp.pp2Pos(date.getHours()) + ":" +
            this.pp.pp2Pos(date.getMinutes());
        setTimeout(() => this.updateTime(), 60000);
    }
}