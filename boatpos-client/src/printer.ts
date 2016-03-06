import {Injectable} from 'angular2/core';
import {isPresent} from "angular2/src/facade/lang";
import {Rental} from "./model/rental";
import {ConfigService} from "./service/config.service";
import {PrettyPrinter} from "./prettyprinter";
import {Boat} from "./model/boat";
import {Bill} from "./model/bill";
import {JournalReport} from "./model/journalReport";
import {Align} from "./prettyprinter";

@Injectable()
export class Printer {

    constructor(private configService:ConfigService, private pp:PrettyPrinter) {

    }

    public printDepart(rental:Rental):void {
        if (isPresent(rental)) {
            //noinspection TypeScriptUnresolvedFunction
            var builder = new StarWebPrintBuilder();
            var request = builder.createInitializationElement();
            request = this.addLogo(builder, request);
            request = this.printCompanyData(builder, request, rental);
            request = this.addBoat(builder, request, rental);
            request = this.add5MinuteInfo(builder, request);
            this.printPaper(builder, request);

            setTimeout(() => this.printNumberForCommitment(rental), 3000);
        }
    }

    public printNumberForCommitment(rental:Rental) {
        if (isPresent(rental) && isPresent(rental.commitments)) {
            let needPaper:boolean = false;
            rental.commitments.forEach((commitment) => {
                if (commitment.paper) {
                    needPaper = true;
                }
            });
            if (needPaper === true) {
                let dayIdString:string = this.pp.pp3Pos(rental.dayId);
                //noinspection TypeScriptUnresolvedFunction
                var builder = new StarWebPrintBuilder();
                var request = builder.createInitializationElement();
                request = this.blankLine(builder, request);
                request = this.printLogo(builder, request, this.convertFromNumberToLogoName(dayIdString.charAt(2)), 'left');
                request = this.printLogo(builder, request, this.convertFromNumberToLogoName(dayIdString.charAt(1)), 'left');
                request = this.printLogo(builder, request, this.convertFromNumberToLogoName(dayIdString.charAt(0)), 'left');
                request = this.blankLine(builder, request);
                request = this.blankLine(builder, request);
                this.printPaper(builder, request);
            }
        }
    }

    public printBill(bill:Bill) {
        //noinspection TypeScriptUnresolvedFunction
        var builder = new StarWebPrintBuilder();
        var request = builder.createInitializationElement();
        request = this.addLogo(builder, request);
        request = this.printLine(builder, request, 3, 3, 'center', true, false, 'Rechnung');
        request = this.blankLine(builder, request);
        request = this.printLine(builder, request, 1, 1, 'left', false, false, '*');
        request = this.printLine(builder, request, 1, 1, 'left', false, false, '*');
        request = this.printLine(builder, request, 1, 1, 'left', false, false, '*');
        request = this.printLine(builder, request, 1, 1, 'left', false, false, '*');
        request = this.printLine(builder, request, 1, 1, 'left', false, false, '*');
        request = this.blankLine(builder, request);
        request = this.printCompanyData(builder, request);
        this.printPaper(builder, request);
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
        request = this.printLine(builder, request, 1, 1, 'center', false, false, '1220 Wien, Wagramertraße 48a');
        request = this.printLine(builder, request, 1, 1, 'center', false, false, 'tel: +43 1 2633530');
        request = this.printLine(builder, request, 1, 1, 'center', false, false, 'mail: office@eppel-boote.at');
        request = this.printLine(builder, request, 1, 1, 'center', false, false, 'web: www.eppel-boote.at');
        return request;
    }

    private addBoat(builder:any, request:any, rental:Rental):any {
        // add boat and number
        request = this.printLogo(builder, request, this.mapBoatToLogoName(rental.boat), 'center');
        request = this.printLine(builder, request, 3, 3, 'center', true, false, this.pp.pp3Pos(rental.dayId));
        // rental-data
        request = this.printText(builder, request, 1, 2, 'left', false, true, 'Datum');
        request = this.printLine(builder, request, 1, 2, 'left', true, false, ': ' + this.pp.printDate(rental.day));
        request = this.printText(builder, request, 1, 2, 'left', false, true, 'Abfahrt');
        request = this.printLine(builder, request, 1, 2, 'left', true, false, ': ' + this.pp.printTime(rental.departure));
        request = this.printText(builder, request, 1, 2, 'left', false, true, 'Einsatz');
        request = this.printLine(builder, request, 1, 2, 'left', true, false, ': ' + this.pp.printCommitments(rental.commitments));
        if (isPresent(rental.pricePaidBefore)) {
            request = this.printText(builder, request, 1, 2, 'left', false, true, 'Aktion');
            request = this.printLine(builder, request, 1, 2, 'left', true, false, ': ' + rental.promotionBefore.name);
            request = this.printText(builder, request, 1, 2, 'left', false, true, 'Bezahlt');
            request = this.printLine(builder, request, 1, 2, 'left', true, false, ': ' + this.pp.ppPrice(rental.pricePaidBefore, '€ '));
        }
        return request;
    }

    private mapBoatToLogoName(boat:Boat):string {
        if (boat.shortName === "E") {
            return "20";
        } else if (boat.shortName === "T2") {
            return "21";
        } else if (boat.shortName === "T2") {
            return "22";
        } else if (boat.shortName === "T4") {
            return "23";
        } else if (boat.shortName === "TR") {
            return "24";
        } else if (boat.shortName === "L") {
            return "25";
        } else if (boat.shortName === "P") {
            return "26";
        }
    }

    private add5MinuteInfo(builder:any, request:any):any {
        request = this.blankLine(builder, request);
        request = this.printLine(builder, request, 1, 1, 'center', false, false, 'Hinweis: Es werden (für das Ein-/Aussteigen)');
        request = this.printLine(builder, request, 1, 1, 'center', false, false, '5 Minuten auf Fahrzeit gutgeschrieben!');
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

    private printPaper(builder:any, request:any) {
        // cut
        request += builder.createCutPaperElement({feed: true});
        //noinspection TypeScriptUnresolvedFunction
        var trader = new StarWebPrintTrader({url: 'http://' + this.configService.getPrinterUrl() + '/StarWebPRNT/SendMessage'});
        // print
        trader.sendMessage({request: request});
    }

    printJournal(journalReport:JournalReport):void {
        if (isPresent(journalReport)) {
            //noinspection TypeScriptUnresolvedFunction
            var builder = new StarWebPrintBuilder();
            var request = builder.createInitializationElement();
            request = this.addLogo(builder, request);
            request = this.printLine(builder, request, 2, 2, "center", true, false, "Einnahmen");
            request = this.blankLine(builder, request);
            if (this.pp.printDate(journalReport.start) === this.pp.printDate(journalReport.end)) {
                request = this.printLine(builder, request, 1, 1, "left", true, false, "Datum: " + this.pp.printDate(journalReport.start));
            } else {
                request = this.printLine(builder, request, 1, 1, "left", true, false, "Periode: " + this.pp.printDate(journalReport.start) + " - " + this.pp.printDate(journalReport.end));
            }
            request = this.blankLine(builder, request);
            let sum:number = 0;
            request = this.printLine(builder, request, 1, 1, "left", true, false, "Anzahl Vermietungen");
            journalReport.journalReportItems.forEach(jri => {
                request = this.printText(builder, request, 1, 1, "left", false, false, this.pp.ppFixLength(jri.boatName + ":", 18, Align.LEFT));
                request = this.printLine(builder, request, 1, 1, "left", false, false, this.pp.ppFixLength(this.pp.pp3Pos(jri.count), 10, Align.RIGHT));
                sum += jri.count;
            });
            request = this.printLine(builder, request, 1, 1, "left", true, false, this.pp.ppFixLength("SUMME:", 18, Align.LEFT) + this.pp.ppFixLength(this.pp.pp3Pos(sum), 10, Align.RIGHT));
            request = this.blankLine(builder, request);
            sum = 0;
            request = this.printLine(builder, request, 1, 1, "left", true, false, "Bargeld");
            journalReport.journalReportItems.forEach(jri => {
                request = this.printText(builder, request, 1, 1, "left", false, false, this.pp.ppFixLength(jri.boatName + ":", 18, Align.LEFT));
                request = this.printLine(builder, request, 1, 1, "left", false, false, this.pp.ppFixLength(this.pp.ppPrice(jri.pricePaidBeforeCash + jri.pricePaidAfterCash), 10, Align.RIGHT));
                sum += (jri.pricePaidBeforeCash + jri.pricePaidAfterCash);
            });
            request = this.printLine(builder, request, 1, 1, "left", true, false, this.pp.ppFixLength("SUMME:", 18, Align.LEFT) + this.pp.ppFixLength(this.pp.ppPrice(sum), 10, Align.RIGHT));
            request = this.blankLine(builder, request);
            sum = 0;
            request = this.printLine(builder, request, 1, 1, "left", true, false, "Karte");
            journalReport.journalReportItems.forEach(jri => {
                request = this.printText(builder, request, 1, 1, "left", false, false, this.pp.ppFixLength(jri.boatName + ":", 18, Align.LEFT));
                request = this.printLine(builder, request, 1, 1, "left", false, false, this.pp.ppFixLength(this.pp.ppPrice(jri.pricePaidBeforeCard + jri.pricePaidAfterCard), 10, Align.RIGHT));
                sum += (jri.pricePaidBeforeCard + jri.pricePaidAfterCard);
            });
            request = this.printLine(builder, request, 1, 1, "left", true, false, this.pp.ppFixLength("SUMME:", 18, Align.LEFT) + this.pp.ppFixLength(this.pp.ppPrice(sum), 10, Align.RIGHT));
            request = this.printLogo(builder, request, 13, 'center');
            this.printPaper(builder, request);
        }
    }
}