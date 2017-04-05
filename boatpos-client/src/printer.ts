import {Injectable} from "angular2/core";
import {isPresent} from "angular2/src/facade/lang";
import {Rental} from "./model/rental";
import {Align, PrettyPrinter} from "./prettyprinter";
import {Boat} from "./model/boat";
import {Bill} from "./model/bill";
import {JournalReport} from "./model/journalReport";
import {Income} from "./model/income";

@Injectable()
export class Printer {

    constructor(private pp: PrettyPrinter) {

    }

    public printDepart(rental: Rental, printerIp: string): void {
        console.log("print repart on " + printerIp);
        if (isPresent(rental)) {
            //noinspection TypeScriptUnresolvedFunction
            let builder = new StarWebPrintBuilder();
            let request = builder.createInitializationElement();
            request = this.addLogo(builder, request);
            request = this.printCompanyData(builder, request);
            request = this.addBoat(builder, request, rental);
            request = this.addCommitmentReturnInfo(builder, request);
            request = this.add5MinuteInfo(builder, request);
            request = this.addMyRental(builder, request, rental);
            this.printPaper(builder, request, printerIp);

            setTimeout(() => this.printNumberForCommitment(rental, printerIp), 3000);
        }
    }

    private printNumberForCommitment(rental: Rental, printerIp: string) {
        if (isPresent(rental) && isPresent(rental.commitments)) {
            let needPaper: boolean = false;
            rental.commitments.forEach((commitment) => {
                if (commitment.paper) {
                    needPaper = true;
                }
            });
            if (needPaper === true) {
                let dayIdString: string = this.pp.pp3Pos(rental.dayId);
                //noinspection TypeScriptUnresolvedFunction
                let builder = new StarWebPrintBuilder();
                let request = builder.createInitializationElement();
                request = this.blankLine(builder, request);
                request = this.printLogo(builder, request, this.convertFromNumberToLogoName(dayIdString.charAt(2)), 'left');
                request = this.printLogo(builder, request, this.convertFromNumberToLogoName(dayIdString.charAt(1)), 'left');
                request = this.printLogo(builder, request, this.convertFromNumberToLogoName(dayIdString.charAt(0)), 'left');
                request = this.blankLine(builder, request);
                request = this.blankLine(builder, request);
                this.printPaper(builder, request, printerIp);
            }
        }
    }

    public printBill(bill: Bill, printerIp: string) {
        console.log("print bill on " + printerIp);
        //noinspection TypeScriptUnresolvedFunction
        let builder = new StarWebPrintBuilder();
        let request = builder.createInitializationElement();
        request += this.printCompleteBill(bill, builder, request);
        if (bill != null && bill.sammelBeleg != null) {
            request += builder.createCutPaperElement({feed: true});
            request = this.blankLine(builder, request);
            request = this.printCompleteBill(bill.sammelBeleg, builder, request);
        }
        if (bill != null && bill.dayReceipt != null) {
            request += builder.createCutPaperElement({feed: true});
            request = this.blankLine(builder, request);
            request = this.printCompleteBill(bill.dayReceipt, builder, request);
        }
        if (bill != null && bill.monthReceipt != null) {
            request += builder.createCutPaperElement({feed: true});
            request = this.blankLine(builder, request);
            request = this.printCompleteBill(bill.monthReceipt, builder, request);
        }
        if (bill != null && bill.yearReceipt != null) {
            request += builder.createCutPaperElement({feed: true});
            request = this.blankLine(builder, request);
            request = this.printCompleteBill(bill.yearReceipt, builder, request);
        }
        if (bill != null && bill.income != null) {
            request += builder.createCutPaperElement({feed: true});
            request = this.blankLine(builder, request);
            request = this.printCompleteIncome(bill.income, builder, request);
        }
        this.printPaper(builder, request, printerIp);
    }

    private printCompleteBill(bill: Bill, builder: any, request: any): any {
        request = this.addLogo(builder, request);
        request = this.printLine(builder, request, 2, 2, 'center', true, false, 'Rechnung');
        request = this.printLine(builder, request, 1, 1, 'center', true, false, 'ID: ' + bill.receiptIdentifier + ", " + 'Kassa: ' + bill.cashBoxId);
        request = this.printLine(builder, request, 1, 1, 'center', true, false, 'Datum: ' + this.pp.printDateAndTime(bill.receiptDateAndTime));
        request = this.blankLine(builder, request);
        request = this.printCompanyDataBill(bill, builder, request);
        request = this.blankLine(builder, request);
        request = this.printReceiptType(bill, builder, request);
        request = this.blankLine(builder, request);
        request = this.printTaxSetElements(bill, builder, request);
        request = this.printSumTaxes(bill, builder, request);
        request = this.blankLine(builder, request);
        request = this.printLine(builder, request, 1, 1, 'center', true, false, 'Vielen Dank für Ihren Besuch!');
        request = this.printSignatureDeviceDamaged(bill, builder, request);
        request += builder.createQrCodeElement({
            model: 'model2',
            level: 'level_l',
            cell: 3,
            data: bill.jwsCompact
        });
        return request;
    }

    //noinspection JSMethodCanBeStatic
    private convertFromNumberToLogoName(logoNumber: string): string {
        if (logoNumber === "0") {
            return "99";
        } else {
            return "0" + logoNumber;
        }
    }

    private addLogo(builder: any, request: any): any {
        // logo and company-info
        request = this.printLogo(builder, request, '10', 'center');
        request = this.blankLine(builder, request);
        return request;
    }

    private printCompanyData(builder: any, request: any): any {
        request = this.printLine(builder, request, 1, 1, 'center', false, false, 'Christiane Ahammer');
        request = this.printLine(builder, request, 1, 1, 'center', false, false, '1220 Wien, Wagramer Str. 48a');
        request = this.printLine(builder, request, 1, 1, 'center', false, false, 'tel: +43 1 2633530');
        request = this.printLine(builder, request, 1, 1, 'center', false, false, 'mail: office@eppel-boote.at');
        request = this.printLine(builder, request, 1, 1, 'center', false, false, 'web: www.eppel-boote.at');
        return request;
    }

    private printCompanyDataBill(bill: Bill, builder: any, request: any): any {
        request = this.printLine(builder, request, 1, 1, 'center', false, false, 'Christiane Ahammer');
        request = this.printLine(builder, request, 1, 1, 'center', false, false, bill.company.zip + ' ' + bill.company.city + ', ' + bill.company.street + ", " + bill.company.atu);
        request = this.printLine(builder, request, 1, 1, 'center', false, false, 'tel: ' + bill.company.phone + ', web: www.eppel-boote.at');
        request = this.printLine(builder, request, 1, 1, 'center', false, false, 'mail: ' + bill.company.mail);
        return request;
    }

    private printReceiptType(bill: Bill, builder: any, request: any): any {
        request = this.printLine(builder, request, 1, 1, 'center', true, false, '***** ' + bill.receiptType + ' *****');
        if ('Sammel-Beleg' === bill.receiptType) {
            request = this.printLine(builder, request, 1, 1, 'center', false, false, this.pp.printDateAndTime(bill.sammelBelegStart) + ' - ' + this.pp.printDateAndTime(bill.sammelBelegEnd));
        }
        return request;
    }

    private printSignatureDeviceDamaged(bill: Bill, builder: any, request: any): any {
        if (bill.signatureDeviceAvailable === false) {
            request = this.printLine(builder, request, 1, 1, 'center', true, false, 'Signatureinrichtung ausgefallen!');
        }
        return request;
    }

    private printTaxSetElements(bill: Bill, builder: any, request: any): any {
        request = this.printText(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength('     ', 4, Align.LEFT));
        request = this.printText(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength('Produkt', 16, Align.LEFT));
        request = this.printText(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength(' ', 4, Align.LEFT));
        request = this.printText(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength('Netto', 8, Align.RIGHT));
        request = this.printText(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength('MWST', 6, Align.RIGHT));
        request = this.printLine(builder, request, 1, 1, 'left', true, false, this.pp.ppFixLength('Brutto', 8, Align.RIGHT));
        bill.taxSetElements.forEach(tse => {
            request = this.printText(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength(tse.amount + ' ', 4, Align.LEFT));
            request = this.printText(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength(tse.name, 16, Align.LEFT));
            request = this.printText(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength(tse.taxPercent + '%', 4, Align.RIGHT));
            request = this.printText(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength(this.pp.ppPrice(tse.pricePreTax), 8, Align.RIGHT));
            request = this.printText(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength(this.pp.ppPrice(tse.priceTax), 6, Align.RIGHT));
            request = this.printLine(builder, request, 1, 1, 'left', true, false, this.pp.ppFixLength(this.pp.ppPrice(tse.priceAfterTax), 8, Align.RIGHT));
        });
        request += builder.createRuledLineElement({thickness: 'medium', width: 832});
        request = this.printLine(builder, request, 2, 1, 'left', true, false, "          Summe " + this.pp.ppPrice(bill.sumTotal));
        return request;
    }

    private printSumTaxes(bill: Bill, builder: any, request: any): any {
        request = this.printSumTax("20", bill.sumTaxSetNormal, builder, request);
        request = this.printSumTax("10", bill.sumTaxSetErmaessigt1, builder, request);
        request = this.printSumTax("13", bill.sumTaxSetErmaessigt2, builder, request);
        request = this.printSumTax(" 0", bill.sumTaxSetNull, builder, request);
        request = this.printSumTax("19", bill.sumTaxSetBesonders, builder, request);
        return request;
    }

    private printSumTax(taxPercent: string, sum: number, builder: any, request: any): any {
        if (isPresent(sum) && sum > 0) {
            request = this.printLine(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength(taxPercent + '% MWST: ' + this.pp.ppPrice(sum), 40, Align.RIGHT));
        }
        return request;
    }

    private addBoat(builder: any, request: any, rental: Rental): any {
        // add boat and number
        request = this.printLogo(builder, request, this.mapBoatToLogoName(rental.boat), 'center');
        request = this.printLine(builder, request, 3, 3, 'center', true, false, this.pp.pp3Pos(rental.dayId));
        // rental-data
        request = this.printText(builder, request, 1, 2, 'left', false, false, '   ');
        request = this.printText(builder, request, 1, 2, 'left', false, true, 'Datum');
        request = this.printText(builder, request, 1, 2, 'left', true, false, ': ' + this.pp.printDate(rental.day));
        request = this.printText(builder, request, 1, 2, 'left', false, false, ', ');
        request = this.printText(builder, request, 1, 2, 'left', false, true, 'Abfahrt');
        request = this.printLine(builder, request, 1, 2, 'left', true, false, ': ' + this.pp.printTime(rental.departure));
        request = this.printText(builder, request, 1, 2, 'left', false, false, '   ');
        request = this.printText(builder, request, 1, 2, 'left', false, true, 'Einsatz');
        request = this.printLine(builder, request, 1, 2, 'left', true, false, ': ' + this.pp.printCommitments(rental.commitments));
        if (isPresent(rental.pricePaidBefore)) {
            request = this.printText(builder, request, 1, 2, 'left', false, false, '   ');
            request = this.printText(builder, request, 1, 2, 'left', false, true, 'Aktion');
            request = this.printLine(builder, request, 1, 2, 'left', true, false, ': ' + rental.promotionBefore.name);
            request = this.printText(builder, request, 1, 2, 'left', false, false, '   ');
            request = this.printText(builder, request, 1, 2, 'left', false, true, 'Bezahlt');
            request = this.printLine(builder, request, 1, 2, 'left', true, false, ': ' + this.pp.ppPrice(rental.pricePaidBefore, '€ '));
        }
        return request;
    }

    //noinspection JSMethodCanBeStatic
    private mapBoatToLogoName(boat: Boat): string {
        if (boat.shortName === "E") {
            return "20";
        } else if (boat.shortName === "T2") {
            return "21";
        } else if (boat.shortName === "T4") {
            return "22";
        } else if (boat.shortName === "TR") {
            return "23";
        } else if (boat.shortName === "L") {
            return "24";
        } else if (boat.shortName === "P") {
            return "25";
        }
    }

    private add5MinuteInfo(builder: any, request: any): any {
        request = this.blankLine(builder, request);
        request = this.printLine(builder, request, 1, 1, 'center', false, false, 'Hinweis: Es werden (für das Ein-/Aussteigen)');
        request = this.printLine(builder, request, 1, 1, 'center', false, false, '5 Minuten auf die Fahrzeit gutgeschrieben!');
        return request;
    }

    private addMyRental(builder: any, request: any, rental: Rental): any {
        request = this.blankLine(builder, request);
        request = this.printLine(builder, request, 2, 2, 'center', false, false, '*** *** NEU *** ***');
        request = this.blankLine(builder, request);
        request = this.printLine(builder, request, 1, 1, 'center', false, false, 'Alle Infos zu deiner Bootsfahrt jetzt online!');
        request = this.printLine(builder, request, 1, 1, 'center', false, false, 'QR-Code am Smartphone scannen und Link öffnen!');
        let myRentalUrl: string = 'https://www.eppel-boote.at/myrental/index.php?id=' + rental.myRentalId;
        console.log("myRentalUrl: " + myRentalUrl);
        request += builder.createQrCodeElement({
            model: 'model2',
            level: 'level_l',
            cell: 4,
            data: myRentalUrl
        });
        return request;
    }

    private addCommitmentReturnInfo(builder: any, request: any): any {
        request = this.blankLine(builder, request);
        request = this.printLine(builder, request, 1, 1, 'center', false, false, 'Achtung: Der Einsatz kann ausschließlich nach');
        request = this.printLine(builder, request, 1, 1, 'center', false, false, 'Vorlage dieses Belegs retourniert werden!');
        return request;
    }

    //noinspection JSMethodCanBeStatic
    private printLogo(builder: any, request: any, logo: string, align: string): any {
        request += builder.createAlignmentElement({position: align});
        request += builder.createLogoElement({number: logo, width: 'single', height: 'single'});
        return request;
    }

    private blankLine(builder: any, request: any): any {
        request = this.printLine(builder, request, 1, 1, 'center', false, false, '');
        return request;
    }

    private printLine(builder: any, request: any, width: number, height: number, align: string, emphasis: boolean, underline: boolean, data: string): any {
        request = this.printText(builder, request, width, height, align, emphasis, underline, data + "\n");
        return request;
    }

    //noinspection JSMethodCanBeStatic
    private printText(builder: any, request: any, width: number, height: number, align: string, emphasis: boolean, underline: boolean, data: string): any {
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

    private printPaper(builder: any, request: any, printerIp: string) {
        // cut
        request += builder.createCutPaperElement({feed: true});
        //noinspection TypeScriptUnresolvedFunction
        let trader = new StarWebPrintTrader({url: 'http://' + printerIp + '/StarWebPRNT/SendMessage'});
        // print
        trader.sendMessage({request: request});
    }

    printJournal(journalReport: JournalReport, printerIp: string): void {
        console.log("print journal on " + printerIp);
        if (isPresent(journalReport)) {
            console.log("print journal between " + this.pp.printDate(journalReport.start) + " and " + this.pp.printDate(journalReport.end) + " on " + printerIp);
            //noinspection TypeScriptUnresolvedFunction
            let builder = new StarWebPrintBuilder();
            let request = builder.createInitializationElement();
            request += this.printCompleteJournal(journalReport, builder, request);
            this.printPaper(builder, request, printerIp);
        }
    }

    private printCompleteJournal(journalReport: JournalReport, builder: any, request: any): any {
        request = this.addLogo(builder, request);
        request = this.printLine(builder, request, 2, 2, "center", true, false, "Einnahmen Bootsverm.");
        request = this.blankLine(builder, request);
        if (this.pp.printDate(journalReport.start) === this.pp.printDate(journalReport.end)) {
            request = this.printLine(builder, request, 1, 1, "left", true, false, "Datum: " + this.pp.printDate(journalReport.start));
        } else {
            request = this.printLine(builder, request, 1, 1, "left", true, false, "Zeitraum: " + this.pp.printDate(journalReport.start) + " - " + this.pp.printDate(journalReport.end));
        }
        request = this.blankLine(builder, request);
        let sumBoat: number = 0;
        request = this.printLine(builder, request, 1, 1, "left", true, false, "Anzahl Vermietungen");
        journalReport.journalReportItems.forEach(jri => {
            request = this.printText(builder, request, 1, 1, "left", false, false, this.pp.ppFixLength(jri.boatName + ":", 18, Align.LEFT));
            request = this.printLine(builder, request, 1, 1, "left", false, false, this.pp.ppFixLength(String(jri.count), 10, Align.RIGHT));
            sumBoat += jri.count;
        });
        request = this.printLine(builder, request, 1, 1, "left", true, false, this.pp.ppFixLength("SUMME:", 18, Align.LEFT) + this.pp.ppFixLength(String(sumBoat), 10, Align.RIGHT));
        request = this.blankLine(builder, request);
        let sumCash = 0;
        let sumCashBeforeTax = 0;
        let sumCashTax = 0;
        request = this.printLine(builder, request, 1, 1, "left", true, false, "Bargeld");
        journalReport.journalReportItems.forEach(jri => {
            request = this.printText(builder, request, 1, 1, "left", false, false, this.pp.ppFixLength(jri.boatName + ":", 18, Align.LEFT));
            request = this.printLine(builder, request, 1, 1, "left", false, false, this.pp.ppFixLength(this.pp.ppPrice(jri.pricePaidBeforeCash + jri.pricePaidAfterCash), 10, Align.RIGHT));
            sumCash += (jri.pricePaidBeforeCash + jri.pricePaidAfterCash);
            sumCashBeforeTax += (jri.pricePaidBeforeCashBeforeTax + jri.pricePaidAfterCashBeforeTax);
            sumCashTax += (jri.pricePaidBeforeCashTax + jri.pricePaidAfterCashTax);
        });
        request = this.printLine(builder, request, 1, 1, "left", true, false,
            this.pp.ppFixLength("SUMME:", 8, Align.LEFT) +
            this.pp.ppFixLength(this.pp.ppPrice(sumCash), 10, Align.RIGHT) + " / " +
            this.pp.ppFixLength(this.pp.ppPrice(sumCashTax), 9, Align.RIGHT) + " / " +
            this.pp.ppFixLength(this.pp.ppPrice(sumCashBeforeTax), 10, Align.RIGHT)
        );
        request = this.blankLine(builder, request);
        let sumCard = 0;
        let sumCardBeforeTax = 0;
        let sumCardTax = 0;
        request = this.printLine(builder, request, 1, 1, "left", true, false, "Karte");
        journalReport.journalReportItems.forEach(jri => {
            request = this.printText(builder, request, 1, 1, "left", false, false, this.pp.ppFixLength(jri.boatName + ":", 18, Align.LEFT));
            request = this.printLine(builder, request, 1, 1, "left", false, false, this.pp.ppFixLength(this.pp.ppPrice(jri.pricePaidBeforeCard + jri.pricePaidAfterCard), 10, Align.RIGHT));
            sumCard += (jri.pricePaidBeforeCard + jri.pricePaidAfterCard);
            sumCardBeforeTax += (jri.pricePaidBeforeCardBeforeTax + jri.pricePaidAfterCardBeforeTax);
            sumCardTax += (jri.pricePaidBeforeCardTax + jri.pricePaidAfterCardTax);
        });
        request = this.printLine(builder, request, 1, 1, "left", true, false,
            this.pp.ppFixLength("SUMME:", 8, Align.LEFT) +
            this.pp.ppFixLength(this.pp.ppPrice(sumCard), 10, Align.RIGHT) + " / " +
            this.pp.ppFixLength(this.pp.ppPrice(sumCardTax), 9, Align.RIGHT) + " / " +
            this.pp.ppFixLength(this.pp.ppPrice(sumCardBeforeTax), 10, Align.RIGHT)
        );
        request = this.blankLine(builder, request);
        request = this.printLine(builder, request, 1, 1, "left", true, false,
            this.pp.ppFixLength("TOTAL:", 8, Align.LEFT) +
            this.pp.ppFixLength(this.pp.ppPrice(sumCash + sumCard), 10, Align.RIGHT) + " / " +
            this.pp.ppFixLength(this.pp.ppPrice(sumCashTax + sumCardTax), 9, Align.RIGHT) + " / " +
            this.pp.ppFixLength(this.pp.ppPrice(sumCashBeforeTax + sumCardBeforeTax), 10, Align.RIGHT)
        );
        request = this.printLogo(builder, request, 13, 'center');
        return request;
    }

    private printCompleteIncome(income: Income, builder: any, request: any): any {
        request = this.printLine(builder, request, 2, 2, "center", true, false, "Einnahmen Bootsverm.");
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
                    request = this.printText(builder, request, 1, 1, "left", false, false, this.pp.ppFixLength("  " + this.pp.ppPrice(te.price), 12, Align.RIGHT));
                    request = this.printText(builder, request, 1, 1, "left", false, false, this.pp.ppFixLength(" /  " + this.pp.ppPrice(te.priceTax), 14, Align.RIGHT));
                    request = this.printLine(builder, request, 1, 1, "left", false, false, this.pp.ppFixLength(" /  " + this.pp.ppPrice(te.priceBeforeTax), 14, Align.RIGHT));
                }
            });
        }
        request = this.printLogo(builder, request, 13, 'center');
        return request;
    }

    printTest(printerIp: string) {
        console.log("test printer on " + printerIp);
        //noinspection TypeScriptUnresolvedFunction
        let builder = new StarWebPrintBuilder();
        let request = builder.createInitializationElement();
        request += builder.createTextElement({
            codepage: 'utf8',
            data: 'Drucker für das Abrechnungssystem funktioniert!\n\n'
        });
        // cut
        request += builder.createCutPaperElement({feed: true});
        //noinspection TypeScriptUnresolvedFunction
        let trader = new StarWebPrintTrader({url: 'http://' + printerIp + '/StarWebPRNT/SendMessage'});
        // print
        trader.sendMessage({request: request});
    }
}
