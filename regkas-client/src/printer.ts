import {Injectable} from "angular2/core";
import {isPresent} from "angular2/src/facade/lang";
import {PrettyPrinter, Align} from "./prettyprinter";
import {Bill} from "./model/bill";
import {Income} from "./model/income";
// import {JournalReport} from "./model/journalReport";

@Injectable()
export class Printer {

    constructor(private pp:PrettyPrinter) {

    }

    public printBill(bill:Bill, printerIp:string) {
        //noinspection TypeScriptUnresolvedFunction
        var builder = new StarWebPrintBuilder();
        var request = builder.createInitializationElement();
        request = this.addLogo(builder, request);
        request = this.printLine(builder, request, 2, 2, 'center', true, false, 'Rechnung');
        request = this.printLine(builder, request, 1, 1, 'center', true, false, 'ID: ' + bill.receiptIdentifier + ", " + 'Kassa: ' + bill.cashBoxId);
        request = this.printLine(builder, request, 1, 1, 'center', true, false, 'Datum: ' + this.pp.printDate(bill.receiptDateAndTime) + ", " + this.pp.printTime(bill.receiptDateAndTime));
        request = this.blankLine(builder, request);
        request = this.printCompanyDataBill(bill, builder, request);
        request = this.blankLine(builder, request);
        request = this.printTaxSetElements(bill, builder, request);
        request = this.printSumTaxes(bill, builder, request);
        request = this.blankLine(builder, request);
        request = this.printLine(builder, request, 1, 1, 'center', true, false, 'Vielen Dank für Ihren Besuch!');
        request += builder.createQrCodeElement({
            model: 'model2',
            level: 'level_l',
            cell: 3,
            data: 'https://www.eppel-boote.at'
        });
        request += builder.createPeripheralElement({channel:1, on:200, off:200});
        this.printPaper(builder, request, printerIp);
    }

    private convertFromNumberToLogoName(logoNumber:string):string {
        if (logoNumber === "0") {
            return "99";
        } else {
            return "0" + logoNumber;
        }
    }

    private addLogo(builder:any, request:any):any {
        // logo and company-info
        request = this.printLogo(builder, request, 10, 'center');
        request = this.blankLine(builder, request);
        return request;
    }

    private printCompanyData(builder:any, request:any):any {
        request = this.printLine(builder, request, 1, 1, 'center', false, false, 'Christiane Ahammer');
        request = this.printLine(builder, request, 1, 1, 'center', false, false, '1220 Wien, Wagramer Str. 48a');
        request = this.printLine(builder, request, 1, 1, 'center', false, false, 'tel: +43 1 2633530');
        request = this.printLine(builder, request, 1, 1, 'center', false, false, 'mail: office@eppel-boote.at');
        request = this.printLine(builder, request, 1, 1, 'center', false, false, 'web: www.eppel-boote.at');
        return request;
    }

    private printCompanyDataBill(bill:Bill, builder:any, request:any):any {
        request = this.printLine(builder, request, 1, 1, 'center', false, false, 'Christiane Ahammer');
        request = this.printLine(builder, request, 1, 1, 'center', false, false, bill.company.zip + ' ' + bill.company.city + ', ' + bill.company.street + ", " + bill.company.atu);
        request = this.printLine(builder, request, 1, 1, 'center', false, false, 'tel: ' + bill.company.phone + ', web: www.eppel-boote.at');
        request = this.printLine(builder, request, 1, 1, 'center', false, false, 'mail: ' + bill.company.mail);
        return request;
    }

    private printTaxSetElements(bill:Bill, builder:any, request:any):any {
        request = this.printText(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength('     ', 4, 'left'));
        request = this.printText(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength('Produkt', 16, 'left'));
        request = this.printText(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength(' ', 4, 'left'));
        request = this.printText(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength('Netto', 8, 'right'));
        request = this.printText(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength('MWST', 6, 'right'));
        request = this.printLine(builder, request, 1, 1, 'left', true, false, this.pp.ppFixLength('Brutto', 8, 'right'));
        bill.taxSetElements.forEach(tse => {
            request = this.printText(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength(tse.amount + ' ', 4, 'left'));
            request = this.printText(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength(tse.name, 16, 'left'));
            request = this.printText(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength(' ' + tse.taxPercent + '%', 4, 'right'));
            request = this.printText(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength(this.pp.ppPrice(tse.pricePreTax, ''), 8, 'right'));
            request = this.printText(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength(this.pp.ppPrice(tse.priceTax, ''), 6, 'right'));
            request = this.printLine(builder, request, 1, 1, 'left', true, false, this.pp.ppFixLength(this.pp.ppPrice(tse.priceAfterTax), 8, 'right'));
        });
        request += builder.createRuledLineElement({thickness: 'medium', width: 832});
        request = this.printLine(builder, request, 2, 1, 'left', true, false, "          Summe " + this.pp.ppPrice(bill.sumTotal));
        return request;
    }

    private printSumTaxes(bill:Bill, builder:any, request:any):any {
        request = this.printSumTax("20", bill.sumTaxSetNormal, builder, request);
        request = this.printSumTax("10", bill.sumTaxSetErmaessigt1, builder, request);
        request = this.printSumTax("13", bill.sumTaxSetErmaessigt2, builder, request);
        request = this.printSumTax(" 0", bill.sumTaxSetNull, builder, request);
        request = this.printSumTax("19", bill.sumTaxSetBesonders, builder, request);
        return request;
    }

    private printSumTax(taxPercent:string, sum:number, builder:any, request:any):any {
        if (isPresent(sum) && sum > 0) {
            request = this.printLine(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength(taxPercent + '% MWST: ' + this.pp.ppPrice(sum), 40, 'right'));
        }
        return request;
    }

    private printLogo(builder:any, request:any, logo:number, align:string):any {
        request += builder.createAlignmentElement({position: align});
        request += builder.createLogoElement({number: logo, width: 'single', height: 'single'});
        return request;
    }

    private blankLine(builder:any, request:any):any {
        request = this.printLine(builder, request, 1, 1, 'center', false, false, '');
        return request;
    }

    private printLine(builder:any, request:any, width:number, height:number, align:string, emphasis:boolean, underline:boolean, data:string):any {
        request = this.printText(builder, request, width, height, align, emphasis, underline, data + "\n");
        return request;
    }

    private printText(builder:any, request:any, width:number, height:number, align:string, emphasis:boolean, underline:boolean, data:string):any {
        request += builder.createAlignmentElement({position: align});
        request += builder.createTextElement({
            width: width,
            height: height,
            emphasis: emphasis,
            underline: underline,
            codepage: 'utf8',
            data: data
        });
        return request;
    }

    private printPaper(builder:any, request:any, printerIp:string) {
        // cut
        request += builder.createCutPaperElement({feed: true});
        //noinspection TypeScriptUnresolvedFunction
        var trader = new StarWebPrintTrader({url: 'http://' + printerIp + '/StarWebPRNT/SendMessage'});
        // print
        trader.sendMessage({request: request});
    }

    printIncome(income:Income, printerIp:string) {
        if (isPresent(income)) {
            console.log("print journal between " + this.pp.printDate(income.start) + " and " + this.pp.printDate(income.end));
            // noinspection TypeScriptUnresolvedFunction
            var builder = new StarWebPrintBuilder();
            var request = builder.createInitializationElement();
            request = this.addLogo(builder, request);
            request = this.printLine(builder, request, 2, 2, "center", true, false, "Einnahmen Buffet");
            request = this.blankLine(builder, request);
            if (this.pp.printDate(income.start) === this.pp.printDate(income.end)) {
                request = this.printLine(builder, request, 1, 1, "left", true, false, "Datum: " + this.pp.printDate(income.start));
            } else {
                request = this.printLine(builder, request, 1, 1, "left", true, false, "Zeitraum: " + this.pp.printDate(income.start) + " - " + this.pp.printDate(income.end));
            }

            if (isPresent(income.incomeProductGroups)) {
                request = this.blankLine(builder, request);
                income.incomeProductGroups.forEach(ipg => {
                    request = this.printText(builder, request, 1, 1, "left", false, false, this.pp.ppFixLength(ipg.name, 26, Align.LEFT));
                    request = this.printText(builder, request, 1, 1, "left", false, false, this.pp.ppFixLength("  " + ipg.taxPercent + "%", 6, Align.LEFT));
                    request = this.printLine(builder, request, 1, 1, "left", false, false, this.pp.ppFixLength("  " + this.pp.ppPrice(ipg.income), 10, Align.RIGHT));
                });
            }
            request += builder.createRuledLineElement({thickness: 'medium', width: 832});
            request = this.printLine(builder, request, 1, 1, "left", true, false, this.pp.ppFixLength("SUMME:", 26, Align.LEFT) + this.pp.ppFixLength(this.pp.ppPrice(income.totalIncome), 16, Align.RIGHT));

            if (isPresent(income.taxElements)) {
                request = this.blankLine(builder, request);
                income.taxElements.forEach(te => {
                    if (te.price > 0) {
                        request = this.printText(builder, request, 1, 1, "left", false, false, this.pp.ppFixLength(te.taxPercent + "%", 6, Align.LEFT));
                        request = this.printText(builder, request, 1, 1, "left", false, false, this.pp.ppFixLength("  " + this.pp.ppPrice(te.price), 10, Align.RIGHT));
                        request = this.printText(builder, request, 1, 1, "left", false, false, this.pp.ppFixLength(" /  " + this.pp.ppPrice(te.priceTax), 10, Align.RIGHT));
                        request = this.printLine(builder, request, 1, 1, "left", false, false, this.pp.ppFixLength(" /  " + this.pp.ppPrice(te.priceBeforeTax), 10, Align.RIGHT));
                    }
                });
            }

            request = this.printLogo(builder, request, 13, 'center');
            this.printPaper(builder, request, printerIp);
        }
    }


    printTest(printerIp:string) {
        console.log("test printer on " + printerIp);
        //noinspection TypeScriptUnresolvedFunction
        var builder = new StarWebPrintBuilder();
        var request = builder.createInitializationElement();
        request += builder.createTextElement({
            codepage: 'utf8',
            data: 'Drucker für die Registrierkassa funktioniert!\n\n'
        });
        // cut
        request += builder.createCutPaperElement({feed: true});
        //noinspection TypeScriptUnresolvedFunction
        var trader = new StarWebPrintTrader({url: 'http://' + printerIp + '/StarWebPRNT/SendMessage'});
        // print
        trader.sendMessage({request: request});
    }
}
