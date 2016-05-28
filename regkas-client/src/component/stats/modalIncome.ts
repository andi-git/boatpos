import {Component} from "angular2/core";
import {NgIf, NgFor} from "angular2/common";
//noinspection TypeScriptCheckImport
import {Modal, ModalConfig, ICustomModal, ModalDialogInstance, ICustomModalComponent} from "lib/angular2-modal";
import {JournalService} from "../../service/journal.service.ts";
import {Printer} from "../../printer";
import {ICustomModalComponent, ModalDialogInstance, ICustomModal} from "angular2-modal/dist/angular2-modal";
import {PrettyPrinter} from "../../prettyprinter";
import {isPresent} from "angular2/src/facade/lang";
import {ConfigService} from "../../service/config.service";
import {Income} from "../../model/income";

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
                    <td class="button-ok">Produktgruppe</td>
                    <td class="button-ok">Einnahmen</td>
                    <td class="button-ok">Steuer</td>
                </tr>
                <tr>
                    <td valign="top">Eskimoeis</td>
                    <td align="right" valign="top">{{pp.ppPrice(getIncomeProductGroup('Eskimoeis').income)}}</td>
                    <td align="right" valign="top">{{getIncomeProductGroup('Eskimoeis').taxPercent}}%</td>
                </tr>
                <tr>
                    <td valign="top">alkoholfreies Getränk</td>
                    <td align="right" valign="top">{{pp.ppPrice(getIncomeProductGroup('alkoholfreies Getränk').income)}}</td>
                    <td align="right" valign="top">{{getIncomeProductGroup('alkoholfreies Getränk').taxPercent}}%</td>
                </tr>
                <tr>
                    <td valign="top">Kaffee</td>
                    <td align="right" valign="top">{{pp.ppPrice(getIncomeProductGroup('Kaffee').income)}}</td>
                    <td align="right" valign="top">{{getIncomeProductGroup('Kaffee').taxPercent}}%</td>
                </tr>
                <tr>
                    <td valign="top">Bier</td>
                    <td align="right" valign="top">{{pp.ppPrice(getIncomeProductGroup('Bier').income)}}</td>
                    <td align="right" valign="top">{{getIncomeProductGroup('Bier').taxPercent}}%</td>
                </tr>
                <tr>
                    <td valign="top">Wein</td>
                    <td align="right" valign="top">{{pp.ppPrice(getIncomeProductGroup('Wein').income)}}</td>
                    <td align="right" valign="top">{{getIncomeProductGroup('Wein').taxPercent}}%</td>
                </tr>
                <tr>
                    <td valign="top">Warme Speisen</td>
                    <td align="right" valign="top">{{pp.ppPrice(getIncomeProductGroup('Warme Speisen').income)}}</td>
                    <td align="right" valign="top">{{getIncomeProductGroup('Warme Speisen').taxPercent}}%</td>
                </tr>
                <tr>
                    <td valign="top">Belegtes Gebäck</td>
                    <td align="right" valign="top">{{pp.ppPrice(getIncomeProductGroup('Belegtes Gebäck').income)}}</td>
                    <td align="right" valign="top">{{getIncomeProductGroup('Belegtes Gebäck').taxPercent}}%</td>
                </tr>
                <tr>
                    <td valign="top">Kuchen</td>
                    <td align="right" valign="top">{{pp.ppPrice(getIncomeProductGroup('Kuchen').income)}}</td>
                    <td align="right" valign="top">{{getIncomeProductGroup('Kuchen').taxPercent}}%</td>
                </tr>
                <tr>
                    <td valign="top">Snack</td>
                    <td align="right" valign="top">{{pp.ppPrice(getIncomeProductGroup('Snack').income)}}</td>
                    <td align="right" valign="top">{{getIncomeProductGroup('Snack').taxPercent}}%</td>
                </tr>
                <tr>
                    <td valign="top">Eismischgetränk</td>
                    <td align="right" valign="top">{{pp.ppPrice(getIncomeProductGroup('Eismischgetränk').income)}}</td>
                    <td align="right" valign="top">{{getIncomeProductGroup('Eismischgetränk').taxPercent}}%</td>
                </tr>
            </table>
            <table class="table-no-style">
                <tr>
                    <td class="button-ok">Steuersatz</td>
                    <td class="button-ok">Netto</td>
                    <td class="button-ok">Steuer</td>
                    <td class="button-ok">Brutto</td>
                </tr>
                <tr>
                    <td valign="top">10%</td>
                    <td align="right" valign="top">{{pp.ppPrice(getTaxElement(10).priceBeforeTax)}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getTaxElement(10).priceTax)}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getTaxElement(10).price)}}</td>
                </tr>
                <tr>
                    <td valign="top">20%</td>
                    <td align="right" valign="top">{{pp.ppPrice(getTaxElement(20).priceBeforeTax)}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getTaxElement(20).priceTax)}}</td>
                    <td align="right" valign="top">{{pp.ppPrice(getTaxElement(20).price)}}</td>
                </tr>
                <tr>
                    <td valign="top" class="button-action">Summe</td>
                    <td align="right" valign="top" class="button-action">{{pp.ppPrice(getSumTaxElement().priceBeforeTax)}}</td>
                    <td align="right" valign="top" class="button-action">{{pp.ppPrice(getSumTaxElement().priceTax)}}</td>
                    <td align="right" valign="top" class="button-cancel">{{pp.ppPrice(getSumTaxElement().price)}}</td>
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
    private income:Income;
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
        this.journalService.income(this.year, this.month, this.day).subscribe((income) => {
            console.log("report");
            this.income = income;
            this.loaded = true;
        });
    }

    ppDate():string {
        if (isPresent(this.income)) {
            return this.pp.printDate(this.income.start) + " - " + this.pp.printDate(this.income.end);
        }
    }

    getIncomeProductGroup(name:string):IncomeProductGroupResult {
        let result:IncomeProductGroupResult = new IncomeProductGroupResult();
        if (isPresent(this.income)) {
            this.income.incomeProductGroups.forEach(productGroup => {
                if (productGroup.name == name) {
                    result = new IncomeProductGroupResult(productGroup.name, productGroup.income, productGroup.taxPercent);
                }
            });
        }
        return result;
    }


    getTaxElement(taxPercent:number):IncomeTaxElementResult {
        let result:IncomeTaxElementResult = new IncomeTaxElementResult();
        if (isPresent(this.income)) {
            this.income.taxElements.forEach(taxElement => {
                if (taxElement.taxPercent == taxPercent) {
                    result = new IncomeTaxElementResult(taxElement.taxPercent, taxElement.price, taxElement.priceBeforeTax, taxElement.priceTax);
                }
            });
        }
        return result;
    }

    getSumTaxElement():IncomeTaxElementResult {
        let result:IncomeTaxElementResult = new IncomeTaxElementResult();
        let sumPrice:number = 0;
        let sumPriceBeforeTax:number = 0;
        let sumPriceTax:number = 0;
        if (isPresent(this.income)) {
            this.income.taxElements.forEach(taxElement => {
                sumPrice = sumPrice + taxElement.price;
                sumPriceBeforeTax = sumPriceBeforeTax + taxElement.priceBeforeTax;
                sumPriceTax = sumPriceTax + taxElement.priceTax;
            });
        }
        return new IncomeTaxElementResult("Summe", sumPrice, sumPriceBeforeTax, sumPriceTax);
    }

    close($event) {
        $event.stopPropagation();
        //noinspection TypeScriptUnresolvedFunction
        this.dialog.close(true);
    }

    print($event) {
        this.printer.printIncome(this.income, this.config.getPrinterIp());
    }

    cancel() {
        //noinspection TypeScriptUnresolvedFunction
        this.dialog.dismiss();
    }
}

class IncomeProductGroupResult {

    private name:string;
    private income:number;
    private taxPercent:number;

    constructor(name:string, income:number, taxPercent:number) {
        this.name = name;
        this.income = income;
        this.taxPercent = taxPercent;
    }
}

class IncomeTaxElementResult {

    name:string;
    price:number;
    priceBeforeTax:number;
    priceTax:number;

    constructor(name:string, price:number, priceBeforeTax:number, priceTax:number) {
        this.name = name;
        this.price = price;
        this.priceBeforeTax = priceBeforeTax;
        this.priceTax = priceTax;
    }
}
