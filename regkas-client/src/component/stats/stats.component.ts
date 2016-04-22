import {Component} from "angular2/core";
import {ModeService} from "../../service/mode.service";
import {JournalService} from "../../service/journal.service";
import {Printer} from "../../printer";
import {InfoService} from "../../service/info.service";
import {ConfigService} from "../../service/config.service";

@Component({
    selector: 'stats',
    templateUrl: "html/component/stats/stats.component.html",
    styleUrls: ["css/component/stats/stats.component.css"]
})
export class StatsComponent {

    private days:Array<number> = [];
    private currentDay:number;
    private months:Array<string> = [];
    private currentMonth:string;
    private years:Array<number> = [];
    private currentYear:number;

    constructor(private modeService:ModeService, private journalService:JournalService, private printer:Printer, private infoService:InfoService, private config:ConfigService) {
        console.log("constructor of StatsComponent");
        for (let i:number = 0; i < 31; i++) {
            this.days[i] = i + 1;
        }
        this.months.push("Jänner");
        this.months.push("Februar");
        this.months.push("März");
        this.months.push("April");
        this.months.push("Mai");
        this.months.push("Juni");
        this.months.push("Juli");
        this.months.push("August");
        this.months.push("September");
        this.months.push("Oktober");
        this.months.push("November");
        this.months.push("Dezember");
        for (let i:number = 0; i < 10; i++) {
            this.years[i] = i + 2016;
        }
        this.resetIncome();
    }

    dayChange(day:any) {
        this.currentDay = day;
    }

    monthChange(month:any) {
        this.currentMonth = month;
    }

    yearChange(year:any) {
        this.currentYear = year;
    }

    incomeDay() {
        this.infoService.event().emit("Einnahmen werden gedruckt.");
        this.journalService.income(this.currentYear, this.convertMonth(this.currentMonth), this.currentDay).subscribe((income) => this.printer.printIncome(income, this.config.getPrinterIp()));
    }

    incomeMonth() {
        this.infoService.event().emit("Einnahmen werden gedruckt.");
        this.journalService.income(this.currentYear, this.convertMonth(this.currentMonth)).subscribe((income) => this.printer.printIncome(income, this.config.getPrinterIp()));
    }

    incomeYear() {
        this.infoService.event().emit("Einnahmen werden gedruckt.");
        this.journalService.income(this.currentYear).subscribe((income) => this.printer.printIncome(income, this.config.getPrinterIp()));
    }

    convertMonth(month:string):number {
        for (let i = 0; i < this.months.length; i++) {
            if (this.months[i] == month) {
                return i + 1;
            }
        }
        return 0;
    }

    resetIncome() {
        this.currentDay = new Date(Date.now()).getDate();
        this.currentMonth = this.months[new Date(Date.now()).getMonth()];
        this.currentYear = new Date(Date.now()).getFullYear();
    }
}