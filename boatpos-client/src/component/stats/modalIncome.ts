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
import {ConfigService} from "../../service/config.service";

export class ModalIncomeContext {
    constructor(public journalService:JournalService, public pp:PrettyPrinter, public printer:Printer, public config:ConfigService, public year:number, public month:number, public day:number) {
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
                    <td valign="top">{{getCountForBoat('E-Boot')}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getCashBeforeTaxForBoat('E-Boot'))}}<br/>{{pp.ppPrice(getCashTaxForBoat('E-Boot'))}}<br/>{{pp.ppPrice(getCashAfterTaxForBoat('E-Boot'))}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getCardBeforeTaxForBoat('E-Boot'))}}<br/>{{pp.ppPrice(getCardTaxForBoat('E-Boot'))}}<br/>{{pp.ppPrice(getCardAfterTaxForBoat('E-Boot'))}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getSumBeforeTaxForBoat('E-Boot'))}}<br/>{{pp.ppPrice(getSumTaxForBoat('E-Boot'))}}<br/>{{pp.ppPrice(getSumAfterTaxForBoat('E-Boot'))}}</td>
                </tr>
                <tr>
                    <td valign="top">Tretboot klein</td>
                    <td valign="top">{{getCountForBoat('Tretboot klein')}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getCashBeforeTaxForBoat('Tretboot klein'))}}<br/>{{pp.ppPrice(getCashTaxForBoat('Tretboot klein'))}}<br/>{{pp.ppPrice(getCashAfterTaxForBoat('Tretboot klein'))}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getCardBeforeTaxForBoat('Tretboot klein'))}}<br/>{{pp.ppPrice(getCardTaxForBoat('Tretboot klein'))}}<br/>{{pp.ppPrice(getCardAfterTaxForBoat('Tretboot klein'))}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getSumBeforeTaxForBoat('Tretboot klein'))}}<br/>{{pp.ppPrice(getSumTaxForBoat('Tretboot klein'))}}<br/>{{pp.ppPrice(getSumAfterTaxForBoat('Tretboot klein'))}}</td>
                </tr>
                <tr>
                    <td valign="top">Tretboot groß</td>
                    <td valign="top">{{getCountForBoat('Tretboot groß')}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getCashBeforeTaxForBoat('Tretboot groß'))}}<br/>{{pp.ppPrice(getCashTaxForBoat('Tretboot groß'))}}<br/>{{pp.ppPrice(getCashAfterTaxForBoat('Tretboot groß'))}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getCardBeforeTaxForBoat('Tretboot groß'))}}<br/>{{pp.ppPrice(getCardTaxForBoat('Tretboot groß'))}}<br/>{{pp.ppPrice(getCardAfterTaxForBoat('Tretboot groß'))}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getSumBeforeTaxForBoat('Tretboot groß'))}}<br/>{{pp.ppPrice(getSumTaxForBoat('Tretboot groß'))}}<br/>{{pp.ppPrice(getSumAfterTaxForBoat('Tretboot groß'))}}</td>
                </tr>
                <tr>
                    <td valign="top">Tretboot Rutsche</td>
                    <td valign="top">{{getCountForBoat('Tretboot Rutsche')}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getCashBeforeTaxForBoat('Tretboot Rutsche'))}}<br/>{{pp.ppPrice(getCashTaxForBoat('Tretboot Rutsche'))}}<br/>{{pp.ppPrice(getCashAfterTaxForBoat('Tretboot Rutsche'))}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getCardBeforeTaxForBoat('Tretboot Rutsche'))}}<br/>{{pp.ppPrice(getCardTaxForBoat('Tretboot Rutsche'))}}<br/>{{pp.ppPrice(getCardAfterTaxForBoat('Tretboot Rutsche'))}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getSumBeforeTaxForBoat('Tretboot Rutsche'))}}<br/>{{pp.ppPrice(getSumTaxForBoat('Tretboot Rutsche'))}}<br/>{{pp.ppPrice(getSumAfterTaxForBoat('Tretboot Rutsche'))}}</td>
                </tr>
                <tr>
                    <td valign="top">Liegeboot</td>
                    <td valign="top">{{getCountForBoat('Liegeboot')}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getCashBeforeTaxForBoat('Liegeboot'))}}<br/>{{pp.ppPrice(getCashTaxForBoat('Liegeboot'))}}<br/>{{pp.ppPrice(getCashAfterTaxForBoat('Liegeboot'))}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getCardBeforeTaxForBoat('Liegeboot'))}}<br/>{{pp.ppPrice(getCardTaxForBoat('Liegeboot'))}}<br/>{{pp.ppPrice(getCardAfterTaxForBoat('Liegeboot'))}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getSumBeforeTaxForBoat('Liegeboot'))}}<br/>{{pp.ppPrice(getSumTaxForBoat('Liegeboot'))}}<br/>{{pp.ppPrice(getSumAfterTaxForBoat('Liegeboot'))}}</td>
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
    private config:ConfigService;

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

    getCountForBoat(name:string):number {
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

    getCashBeforeTaxForBoat(name:string):number {
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

    getCashTaxForBoat(name:string):number {
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

    getCashAfterTaxForBoat(name:string):number {
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

    getCardBeforeTaxForBoat(name:string):number {
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

    getCardTaxForBoat(name:string):number {
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

    getCardAfterTaxForBoat(name:string):number {
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

    getSumBeforeTaxForBoat(name:string):number {
        return this.getCashBeforeTaxForBoat(name) + this.getCardBeforeTaxForBoat(name);
    }

    getSumBeforeTax():number {
        return this.getCashBeforeTax() + this.getCardBeforeTax();
    }

    getSumTaxForBoat(name:string):number {
        return this.getCashTaxForBoat(name) + this.getCardTaxForBoat(name);
    }

    getSumTax():number {
        return this.getCashTax() + this.getCardTax();
    }

    getSumAfterTaxForBoat(name:string):number {
        return this.getCashAfterTaxForBoat(name) + this.getCardAfterTaxForBoat(name);
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
        this.printer.printJournal(this.report, this.config.getPrinterIp());
    }

    cancel() {
        //noinspection TypeScriptUnresolvedFunction
        this.dialog.dismiss();
    }
}
