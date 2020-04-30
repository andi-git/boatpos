import {Component} from 'angular2/core';
import {BoatCount} from '../../model/boat';
import {BoatService} from "../../service/boat.service";
import {ConfigService} from "../../service/config.service";
import {PrettyPrinter} from "../../prettyprinter";
import {Printer} from "../../printer";

@Component({
    selector: 'statistik',
    templateUrl: "html/component/rental/statistik.component.html",
    styleUrls: ["css/component/rental/statistik.component.css"]
})
export class StatistikComponent {

    private subscription:any;
    private dateString:string = "Datum: ";
    private timeString:string = "Uhrzeit: ";

    constructor(private boatService:BoatService, private configService:ConfigService, private pp:PrettyPrinter, private printer:Printer) {
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

    testIp() {
        console.log("test ip " + this.configService.getPrinterIp());
        this.printer.printTest(this.configService.getPrinterIp());
    }

    testClick() {
        console.log("Test KLICK")
    }
}
