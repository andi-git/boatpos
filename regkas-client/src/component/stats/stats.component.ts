import {Component} from "angular2/core";
import {JournalService} from "../../service/journal.service";
import {Printer} from "../../printer";
import {InfoService} from "../../service/info.service";
import {ConfigService} from "../../service/config.service";
import {DatePicker} from "../../model/datePicker";

@Component({
    selector: 'stats',
    templateUrl: "html/component/stats/stats.component.html",
    styleUrls: ["css/component/stats/stats.component.css"]
})
export class StatsComponent {

    private datePickerIncome = new DatePicker();
    private datePickerDep = new DatePicker();

    constructor(private journalService:JournalService, private printer:Printer, private infoService:InfoService, private config:ConfigService, private info:InfoService) {
        console.log("constructor of StatsComponent");
    }

    dayIncomeChange(day:any) {
        this.datePickerIncome.setCurrentDay(day);
    }

    monthIncomeChange(month:any) {
        this.datePickerIncome.setCurrentMonth(month);
    }

    yearIncomeChange(year:any) {
        this.datePickerIncome.setCurrentYear(year);
    }

    dayDepChange(day:any) {
        this.datePickerDep.setCurrentDay(day);
    }

    monthDepChange(month:any) {
        this.datePickerDep.setCurrentMonth(month);
    }

    yearDepChange(year:any) {
        this.datePickerDep.setCurrentYear(year);
    }

    incomeDay() {
        this.infoService.event().emit("Einnahmen für " + this.datePickerIncome.getCurrentDay() + ". " + this.datePickerIncome.getCurrentMonthAsString() + " " + this.datePickerIncome.getCurrentYear() + " werden angeizeigt.");
        this.journalService.income(this.datePickerIncome.getCurrentYear(), this.datePickerIncome.getCurrentMonthAsNumber(), this.datePickerIncome.getCurrentDay()).subscribe((income) => this.printer.printIncome(income, this.config.getPrinterIp()));
    }

    incomeMonth() {
        this.infoService.event().emit("Einnahmen für " + this.datePickerIncome.getCurrentMonthAsString() + " " + this.datePickerIncome.getCurrentYear() + " werden angeizeigt.");
        this.journalService.income(this.datePickerIncome.getCurrentYear(), this.datePickerIncome.getCurrentMonthAsNumber()).subscribe((income) => this.printer.printIncome(income, this.config.getPrinterIp()));
    }

    incomeYear() {
        this.infoService.event().emit("Einnahmen für " + this.datePickerIncome.getCurrentYear() + " werden angeizeigt.");
        this.journalService.income(this.datePickerIncome.getCurrentYear()).subscribe((income) => this.printer.printIncome(income, this.config.getPrinterIp()));
    }

    depDay() {
        this.info.event().emit("DatenErfassungsProtokoll für " + this.datePickerDep.getCurrentDay() + ". " + this.datePickerDep.getCurrentMonthAsString() + " " + this.datePickerDep.getCurrentYear() + " wird erstellt.");
        window.open(this.config.addQueryParamCredentials(this.config.getBackendUrl() + "rest/journal/dep/"
            + this.datePickerDep.getCurrentYear() + "/"
            + this.datePickerDep.getCurrentMonthAsNumber() + "/"
            + this.datePickerDep.getCurrentDay() + "?"));
    }

    depMonth() {
        this.info.event().emit("DatenErfassungsProtokoll für " + this.datePickerDep.getCurrentMonthAsString() + " " + this.datePickerDep.getCurrentYear() + " wird erstellt.");
        window.open(this.config.addQueryParamCredentials(this.config.getBackendUrl() + "rest/journal/dep/"
            + this.datePickerDep.getCurrentYear() + "/"
            + this.datePickerDep.getCurrentMonthAsNumber() + "?"));
    }

    depYear() {
        this.info.event().emit("DatenErfassungsProtokoll für " + this.datePickerDep.getCurrentYear() + " wird erstellt.");
        window.open(this.config.addQueryParamCredentials(this.config.getBackendUrl() + "rest/journal/dep/"
            + this.datePickerDep.getCurrentYear() + "?"));
    }
}