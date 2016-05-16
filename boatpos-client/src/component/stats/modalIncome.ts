import {Component} from "angular2/core";
import {NgIf, NgFor} from "angular2/common";
//noinspection TypeScriptCheckImport
import {Modal, ModalConfig, ICustomModal, ModalDialogInstance, ICustomModalComponent} from "lib/angular2-modal";
import {JournalService} from "../../service/journal.service.ts";
import {Printer} from "../../printer";
import {ICustomModalComponent, ModalDialogInstance, ICustomModal} from "angular2-modal/dist/angular2-modal";
import {PrettyPrinter} from "../../prettyprinter";
import {JournalReport} from "../../model/journalReport";
import {isPresent} from "angular2/src/facade/lang";
import {Config} from "../../model/config";

export class ModalIncomeContext {
    constructor(public journalService:JournalService, public pp:PrettyPrinter, public printer:Printer, public config:Config, public year:number, public month:number, public day:number) {
    }
}

// this class is ugly because of a bug in angluar2-beta-0: https://github.com/angular/angular/issues/4330
@Component({
    selector: 'modal-content',
    directives: [NgIf, NgFor],
    template: `<div class="modal-header">
        <h2 class="header header-main">Einnahmen: {{ppDate()}}</h2>
        </div>
        <div class="modal-body">
            <table class="table-no-style">
                <tr>
                    <td class="button-ok">Boot</td>
                    <td class="button-ok">Anzahl</td>
                    <td class="button-ok">Bar</td>
                    <td class="button-ok">Karte</td>
                    <td class="button-ok">Summe</td>
                </tr>
                <tr>
                    <td valign="top">E-Boot</td>
                    <td valign="top">{{getCount('E-Boot')}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getCashBeforeTax('E-Boot'))}}<br/>{{pp.ppPrice(getCashTax('E-Boot'))}}<br/>{{pp.ppPrice(getCashAfterTax('E-Boot'))}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getCardBeforeTax('E-Boot'))}}<br/>{{pp.ppPrice(getCardTax('E-Boot'))}}<br/>{{pp.ppPrice(getCardAfterTax('E-Boot'))}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getSumBeforeTax('E-Boot'))}}<br/>{{pp.ppPrice(getSumTax('E-Boot'))}}<br/>{{pp.ppPrice(getSumAfterTax('E-Boot'))}}</td>
                </tr>
                <tr>
                    <td valign="top">Tretboot klein</td>
                    <td valign="top">{{getCount('Tretboot klein')}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getCashBeforeTax('Tretboot klein'))}}<br/>{{pp.ppPrice(getCashTax('Tretboot klein'))}}<br/>{{pp.ppPrice(getCashAfterTax('Tretboot klein'))}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getCardBeforeTax('Tretboot klein'))}}<br/>{{pp.ppPrice(getCardTax('Tretboot klein'))}}<br/>{{pp.ppPrice(getCardAfterTax('Tretboot klein'))}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getSumBeforeTax('Tretboot klein'))}}<br/>{{pp.ppPrice(getSumTax('Tretboot klein'))}}<br/>{{pp.ppPrice(getSumAfterTax('Tretboot klein'))}}</td>
                </tr>
                <tr>
                    <td valign="top">Tretboot groß</td>
                    <td valign="top">{{getCount('Tretboot groß')}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getCashBeforeTax('Tretboot groß'))}}<br/>{{pp.ppPrice(getCashTax('Tretboot groß'))}}<br/>{{pp.ppPrice(getCashAfterTax('Tretboot groß'))}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getCardBeforeTax('Tretboot groß'))}}<br/>{{pp.ppPrice(getCardTax('Tretboot groß'))}}<br/>{{pp.ppPrice(getCardAfterTax('Tretboot groß'))}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getSumBeforeTax('Tretboot groß'))}}<br/>{{pp.ppPrice(getSumTax('Tretboot groß'))}}<br/>{{pp.ppPrice(getSumAfterTax('Tretboot groß'))}}</td>
                </tr>
                <tr>
                    <td valign="top">Tretboot Rutsche</td>
                    <td valign="top">{{getCount('Tretboot Rutsche')}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getCashBeforeTax('Tretboot Rutsche'))}}<br/>{{pp.ppPrice(getCashTax('Tretboot Rutsche'))}}<br/>{{pp.ppPrice(getCashAfterTax('Tretboot Rutsche'))}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getCardBeforeTax('Tretboot Rutsche'))}}<br/>{{pp.ppPrice(getCardTax('Tretboot Rutsche'))}}<br/>{{pp.ppPrice(getCardAfterTax('Tretboot Rutsche'))}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getSumBeforeTax('Tretboot Rutsche'))}}<br/>{{pp.ppPrice(getSumTax('Tretboot Rutsche'))}}<br/>{{pp.ppPrice(getSumAfterTax('Tretboot Rutsche'))}}</td>
                </tr>
                <tr>
                    <td valign="top">Liegeboot</td>
                    <td valign="top">{{getCount('Liegeboot')}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getCashBeforeTax('Liegeboot'))}}<br/>{{pp.ppPrice(getCashTax('Liegeboot'))}}<br/>{{pp.ppPrice(getCashAfterTax('Liegeboot'))}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getCardBeforeTax('Liegeboot'))}}<br/>{{pp.ppPrice(getCardTax('Liegeboot'))}}<br/>{{pp.ppPrice(getCardAfterTax('Liegeboot'))}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getSumBeforeTax('Liegeboot'))}}<br/>{{pp.ppPrice(getSumTax('Liegeboot'))}}<br/>{{pp.ppPrice(getSumAfterTax('Liegeboot'))}}</td>
                </tr>
                <tr>
                    <td valign="top" class="button-action">Summe</td>
                    <td valign="top" class="button-action">{{getCountSum()}}</td>
                    <td align="right" valign="top" class="button-action">{{pp.ppPrice(getCashBeforeTax())}}<br/>{{pp.ppPrice(getCashTax())}}<br/>{{pp.ppPrice(getCashAfterTax())}}</td>
                    <td align="right" valign="top" class="button-action">{{pp.ppPrice(getCardBeforeTax())}}<br/>{{pp.ppPrice(getCardTax())}}<br/>{{pp.ppPrice(getCardAfterTax())}}</td>
                    <td align="right" valign="top" class="button-cancel">{{pp.ppPrice(getSumBeforeTax())}}<br/>{{pp.ppPrice(getSumTax())}}<br/>{{pp.ppPrice(getSumAfterTax())}}</td>
                </tr>
            </table>
        </div>
        <div class="modal-footer">
            <button class="buttonSmall button-action" (click)="print($event)">Drucken</button>
            <button class="buttonSmall button-ok" (click)="close($event)">Schließen</button>
        </div>`,
})
export class ModalIncome implements ICustomModalComponent {

    private dialog:ModalDialogInstance;
    private journalService:JournalService;
    private pp:PrettyPrinter;
    private printer:Printer;
    private year:number;
    private month:number;
    private day:number;
    private report:JournalReport;
    private loaded:boolean = false;
    private config:Config;

    constructor(dialog:ModalDialogInstance, modelContentData:ICustomModal) {
        this.dialog = dialog;
        this.journalService = (<ModalIncomeContext>modelContentData).journalService;
        // let resolvedProviders = Injector.resolve([provide(JournalService, {useValue: this.journalService})]);

        this.printer = (<ModalIncomeContext>modelContentData).printer;
        this.pp = (<ModalIncomeContext>modelContentData).pp;
        this.config = (<ModalIncomeContext>modelContentData).config;
        this.year = (<ModalIncomeContext>modelContentData).year;
        this.month = (<ModalIncomeContext>modelContentData).month;
        this.day = (<ModalIncomeContext>modelContentData).day;
        this.journalService.income(this.year, this.month, this.day).subscribe((journalReport) => {
            console.log("report");
            this.report = journalReport;
            this.loaded = true;
        });
    }

    ppDate():string {
        if (isPresent(this.report)) {
            return this.pp.printDate(this.report.start) + " - " + this.pp.printDate(this.report.end);
        }
    }

    getCount(name:string):number {
        let result:number = 0;
        if (isPresent(this.report)) {
            this.report.journalReportItems.forEach(item => {
                if (item.boatName == name) {
                    result = item.count;
                }
            });
        }
        return result;
    }

    getCountSum():number {
        let result:number = 0;
        if (isPresent(this.report)) {
            this.report.journalReportItems.forEach(item => {
                result += item.count;
            });
        }
        return result;
    }

    getCashBeforeTax(name:string):number {
        let result:number = 0;
        if (isPresent(this.report)) {
            this.report.journalReportItems.forEach(item => {
                if (item.boatName == name) {
                    result = item.pricePaidAfterCashBeforeTax + item.pricePaidBeforeCashBeforeTax;
                }
            });
        }
        return result;
    }

    getCashBeforeTax():number {
        let result:number = 0;
        if (isPresent(this.report)) {
            this.report.journalReportItems.forEach(item => {
                result += item.pricePaidAfterCashBeforeTax + item.pricePaidBeforeCashBeforeTax;
            });
        }
        return result;
    }

    getCashTax(name:string):number {
        let result:number = 0;
        if (isPresent(this.report)) {
            this.report.journalReportItems.forEach(item => {
                if (item.boatName == name) {
                    result = item.pricePaidAfterCashTax + item.pricePaidBeforeCashTax;
                }
            });
        }
        return result;
    }

    getCashTax():number {
        let result:number = 0;
        if (isPresent(this.report)) {
            this.report.journalReportItems.forEach(item => {
                result += item.pricePaidAfterCashTax + item.pricePaidBeforeCashTax;
            });
        }
        return result;
    }

    getCashAfterTax(name:string):number {
        let result:number = 0;
        if (isPresent(this.report)) {
            this.report.journalReportItems.forEach(item => {
                if (item.boatName == name) {
                    result = item.pricePaidAfterCash + item.pricePaidBeforeCash;
                }
            });
        }
        return result;
    }

    getCashAfterTax():number {
        let result:number = 0;
        if (isPresent(this.report)) {
            this.report.journalReportItems.forEach(item => {
                result += item.pricePaidAfterCash + item.pricePaidBeforeCash;
            });
        }
        return result;
    }

    getCardBeforeTax(name:string):number {
        let result:number = 0;
        if (isPresent(this.report)) {
            this.report.journalReportItems.forEach(item => {
                if (item.boatName == name) {
                    result = item.pricePaidAfterCardBeforeTax + item.pricePaidBeforeCardBeforeTax;
                }
            });
        }
        return result;
    }

    getCardBeforeTax():number {
        let result:number = 0;
        if (isPresent(this.report)) {
            this.report.journalReportItems.forEach(item => {
                result += item.pricePaidAfterCardBeforeTax + item.pricePaidBeforeCardBeforeTax;
            });
        }
        return result;
    }

    getCardTax(name:string):number {
        let result:number = 0;
        if (isPresent(this.report)) {
            this.report.journalReportItems.forEach(item => {
                if (item.boatName == name) {
                    result = item.pricePaidAfterCardTax + item.pricePaidBeforeCardTax;
                }
            });
        }
        return result;
    }

    getCardTax():number {
        let result:number = 0;
        if (isPresent(this.report)) {
            this.report.journalReportItems.forEach(item => {
                result += item.pricePaidAfterCardTax + item.pricePaidBeforeCardTax;
            });
        }
        return result;
    }

    getCardAfterTax(name:string):number {
        let result:number = 0;
        if (isPresent(this.report)) {
            this.report.journalReportItems.forEach(item => {
                if (item.boatName == name) {
                    result = item.pricePaidAfterCard + item.pricePaidBeforeCard;
                }
            });
        }
        return result;
    }

    getCardAfterTax():number {
        let result:number = 0;
        if (isPresent(this.report)) {
            this.report.journalReportItems.forEach(item => {
                result += item.pricePaidAfterCard + item.pricePaidBeforeCard;
            });
        }
        return result;
    }

    getSumBeforeTax(name:string):number {
        return this.getCashBeforeTax(name) + this.getCardBeforeTax(name);
    }

    getSumBeforeTax():number {
        return this.getCashBeforeTax() + this.getCardBeforeTax();
    }

    getSumTax(name:string):number {
        return this.getCashTax(name) + this.getCardTax(name);
    }

    getSumTax():number {
        return this.getCashTax() + this.getCardTax();
    }

    getSumAfterTax(name:string):number {
        return this.getCashAfterTax(name) + this.getCardAfterTax(name);
    }

    getSumAfterTax():number {
        return this.getCashAfterTax() + this.getCardAfterTax();
    }

    close($event) {
        $event.stopPropagation();
        //noinspection TypeScriptUnresolvedFunction
        this.dialog.close(true);
    }

    print($event) {
        this.printer.printJournal(this.report, this.config.printerIp);
    }

    cancel() {
        //noinspection TypeScriptUnresolvedFunction
        this.dialog.dismiss();
    }
}
