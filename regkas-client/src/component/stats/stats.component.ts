import {Component} from "angular2/core";
import {JournalService} from "../../service/journal.service";
import {Printer} from "../../printer";
import {InfoService} from "../../service/info.service";
import {ConfigService} from "../../service/config.service";
import {DatePicker} from "../../model/datePicker";
import {ModalIncome, ModalIncomeContext} from "./modalIncome";
import {PrettyPrinter} from "../../prettyprinter";
import {ModalHandler} from "../../modalHandler";
import {SaleService} from "../../service/sale.service";
import {ModalCheckPrint, ModalCheckPrintContext} from "./modalCheckPrint";
import {ErrorService} from "../../service/error.service";

@Component({
    selector: 'stats',
    templateUrl: "html/component/stats/stats.component.html",
    styleUrls: ["css/component/stats/stats.component.css"]
})
export class StatsComponent {

    private datePickerIncome = new DatePicker();
    private datePickerDep = new DatePicker();
    private startbelegMustBePrinted: boolean = false;
    private receiptId: String;

    constructor(private journalService: JournalService, private printer: Printer, private infoService: InfoService, private errorService: ErrorService, private config: ConfigService, private info: InfoService, private pp: PrettyPrinter, private modalHandler: ModalHandler, private saleService: SaleService) {
        console.log("constructor of StatsComponent");
        this.checkIfStartBelegMustBePrinted();
    }

    dayIncomeChange(day: any) {
        this.datePickerIncome.setCurrentDay(day);
    }

    monthIncomeChange(month: any) {
        this.datePickerIncome.setCurrentMonth(month);
    }

    yearIncomeChange(year: any) {
        this.datePickerIncome.setCurrentYear(year);
    }

    dayDepChange(day: any) {
        this.datePickerDep.setCurrentDay(day);
    }

    monthDepChange(month: any) {
        this.datePickerDep.setCurrentMonth(month);
    }

    yearDepChange(year: any) {
        this.datePickerDep.setCurrentYear(year);
    }

    incomeDay() {
        this.info.event().emit("Einnahmen für " + this.datePickerIncome.getCurrentDay() + ". " + this.datePickerIncome.getCurrentMonthAsString() + " " + this.datePickerIncome.getCurrentYear() + " werden angezeigt.");
        this.modalHandler.open(ModalIncome, new ModalIncomeContext(this.journalService, this.pp, this.printer, this.config, this.datePickerIncome.getCurrentYear(), this.datePickerIncome.getCurrentMonthAsNumber(), this.datePickerIncome.getCurrentDay())).then((resultPromise) => {
        });
    }

    incomeMonth() {
        this.info.event().emit("Einnahmen für " + this.datePickerIncome.getCurrentMonthAsString() + " " + this.datePickerIncome.getCurrentYear() + " werden angezeigt.");
        this.modalHandler.open(ModalIncome, new ModalIncomeContext(this.journalService, this.pp, this.printer, this.config, this.datePickerIncome.getCurrentYear(), this.datePickerIncome.getCurrentMonthAsNumber())).then((resultPromise) => {
        });
    }

    incomeYear() {
        this.info.event().emit("Einnahmen für " + this.datePickerIncome.getCurrentYear() + " werden angezeigt.");
        this.modalHandler.open(ModalIncome, new ModalIncomeContext(this.journalService, this.pp, this.printer, this.config, this.datePickerIncome.getCurrentYear())).then((resultPromise) => {
        });
    }

    depRKV2012() {
        this.info.event().emit("DatenErfassungsProtokoll RKV 2012 wird erstellt.");
        window.open(this.config.addQueryParamCredentials(this.config.getBackendUrl() + "rest/journal/dep/latest?"));
    }

    depRKSV2017() {
        this.info.event().emit("DatenErfassungsProtokoll RKSV 2017 wird erstellt.");
        window.open(this.config.addQueryParamCredentials(this.config.getBackendUrl() + "rest/journal/dep/rksv/latest?"));
    }

    printStartBeleg() {
        this.saleService.startBeleg();
        this.startbelegMustBePrinted = false;
    }

    printSchlussBeleg() {
        this.modalHandler.open(ModalCheckPrint, new ModalCheckPrintContext('Schlussbeleg')).then((resultPromise) => {
            resultPromise.result.then((result) => {
                return resultPromise.result.then((result) => {
                    this.saleService.schlussBeleg();
                }, () => {
                    this.errorService.event().emit('Erstellen des Schluss-Belegs wurde abgebrochen!');
                });
            });
        });
    }

    checkIfStartBelegMustBePrinted() {
        this.saleService.checkIfStarbelegMustBePrinted().subscribe(check => {
            this.startbelegMustBePrinted = check;
        });
    }

    printNullBeleg() {
        this.saleService.nullBeleg();
    }

    printTagesBeleg() {
        this.modalHandler.open(ModalCheckPrint, new ModalCheckPrintContext('Tagesbeleg')).then((resultPromise) => {
            resultPromise.result.then((result) => {
                return resultPromise.result.then((result) => {
                    this.saleService.tagesBeleg();
                }, () => {
                    this.errorService.event().emit('Erstellen des Tages-Belegs wurde abgebrochen!');
                });
            });
        });
    }

    printMonatsBeleg() {
        this.modalHandler.open(ModalCheckPrint, new ModalCheckPrintContext('Monatsbeleg')).then((resultPromise) => {
            resultPromise.result.then((result) => {
                return resultPromise.result.then((result) => {
                    this.saleService.monatsBeleg();
                }, () => {
                    this.errorService.event().emit('Erstellen des Monats-Belegs wurde abgebrochen!');
                });
            });
        });
    }

    printJahresBeleg() {
        this.modalHandler.open(ModalCheckPrint, new ModalCheckPrintContext('Jahresbeleg')).then((resultPromise) => {
            resultPromise.result.then((result) => {
                return resultPromise.result.then((result) => {
                    this.saleService.jahresBeleg();
                }, () => {
                    this.errorService.event().emit('Erstellen des Jahres-Belegs wurde abgebrochen');
                });
            });
        });
    }

    printReceipt() {
        if (this.receiptId != null && this.receiptId != "") {
            this.saleService.printReceipt(this.receiptId);
            this.infoService.event().emit("Rechnung für " + this.receiptId + " wurde gedruckt.");
            this.deleteReceiptId();
        }
    }

    deleteReceiptId() {
        this.receiptId = null;
        this.infoService.event().emit("Aktion abgebrochen, Rechnungsnummer zurückgesetzt.");
    }
}
