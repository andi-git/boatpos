System.register(["angular2/core", "angular2/src/facade/lang", "./prettyprinter"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, lang_1, prettyprinter_1;
    var Printer;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (lang_1_1) {
                lang_1 = lang_1_1;
            },
            function (prettyprinter_1_1) {
                prettyprinter_1 = prettyprinter_1_1;
            }],
        execute: function() {
            Printer = (function () {
                function Printer(pp) {
                    this.pp = pp;
                }
                Printer.prototype.printDepart = function (rental, printerIp) {
                    var _this = this;
                    console.log("print repart on " + printerIp);
                    if (lang_1.isPresent(rental)) {
                        //noinspection TypeScriptUnresolvedFunction
                        var builder = new StarWebPrintBuilder();
                        var request = builder.createInitializationElement();
                        request = this.addLogo(builder, request);
                        request = this.printCompanyData(builder, request);
                        request = this.addBoat(builder, request, rental);
                        request = this.addCommitmentReturnInfo(builder, request);
                        request = this.add5MinuteInfo(builder, request);
                        request = this.addMyRental(builder, request, rental);
                        this.printPaper(builder, request, printerIp);
                        setTimeout(function () { return _this.printNumberForCommitment(rental, printerIp); }, 3000);
                    }
                };
                Printer.prototype.printNumberForCommitment = function (rental, printerIp) {
                    if (lang_1.isPresent(rental) && lang_1.isPresent(rental.commitments)) {
                        var needPaper_1 = false;
                        rental.commitments.forEach(function (commitment) {
                            if (commitment.paper) {
                                needPaper_1 = true;
                            }
                        });
                        if (needPaper_1 === true) {
                            var dayIdString = this.pp.pp3Pos(rental.dayId);
                            //noinspection TypeScriptUnresolvedFunction
                            var builder = new StarWebPrintBuilder();
                            var request = builder.createInitializationElement();
                            request = this.blankLine(builder, request);
                            request = this.printLogo(builder, request, this.convertFromNumberToLogoName(dayIdString.charAt(2)), 'left');
                            request = this.printLogo(builder, request, this.convertFromNumberToLogoName(dayIdString.charAt(1)), 'left');
                            request = this.printLogo(builder, request, this.convertFromNumberToLogoName(dayIdString.charAt(0)), 'left');
                            request = this.blankLine(builder, request);
                            request = this.blankLine(builder, request);
                            this.printPaper(builder, request, printerIp);
                        }
                    }
                };
                Printer.prototype.printBill = function (bill, printerIp, journalReport) {
                    console.log("print bill on " + printerIp);
                    //noinspection TypeScriptUnresolvedFunction
                    var builder = new StarWebPrintBuilder();
                    var request = builder.createInitializationElement();
                    request += this.printCompleteBill(bill, builder, request);
                    if (bill != null && bill.sammelBeleg != null) {
                        request += builder.createCutPaperElement({ feed: true });
                        request = this.blankLine(builder, request);
                        request = this.printCompleteBill(bill.sammelBeleg, builder, request);
                    }
                    if (bill != null && bill.dayReceipt != null) {
                        request += builder.createCutPaperElement({ feed: true });
                        request = this.blankLine(builder, request);
                        request = this.printCompleteBill(bill.dayReceipt, builder, request);
                    }
                    if (bill != null && bill.monthReceipt != null) {
                        request += builder.createCutPaperElement({ feed: true });
                        request = this.blankLine(builder, request);
                        request = this.printCompleteBill(bill.monthReceipt, builder, request);
                    }
                    if (bill != null && bill.yearReceipt != null) {
                        request += builder.createCutPaperElement({ feed: true });
                        request = this.blankLine(builder, request);
                        request = this.printCompleteBill(bill.yearReceipt, builder, request);
                    }
                    if (bill != null && bill.income != null) {
                        request = this.blankLine(builder, request);
                        request = this.printCompleteIncome(bill.income, builder, request);
                        if (lang_1.isPresent(journalReport)) {
                            request = this.blankLine(builder, request);
                            request = this.printCompleteJournal(journalReport, builder, request);
                        }
                        request = this.printLogo(builder, request, '13', 'center');
                    }
                    this.printPaper(builder, request, printerIp);
                };
                Printer.prototype.printCompleteBill = function (bill, builder, request) {
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
                };
                //noinspection JSMethodCanBeStatic
                Printer.prototype.convertFromNumberToLogoName = function (logoNumber) {
                    if (logoNumber === "0") {
                        return "99";
                    }
                    else {
                        return "0" + logoNumber;
                    }
                };
                Printer.prototype.addLogo = function (builder, request) {
                    // logo and company-info
                    request = this.printLogo(builder, request, '10', 'center');
                    request = this.blankLine(builder, request);
                    return request;
                };
                Printer.prototype.printCompanyData = function (builder, request) {
                    request = this.printLine(builder, request, 1, 1, 'center', false, false, 'Christiane Ahammer');
                    request = this.printLine(builder, request, 1, 1, 'center', false, false, '1220 Wien, Wagramer Str. 48a');
                    request = this.printLine(builder, request, 1, 1, 'center', false, false, 'tel: +43 1 2633530');
                    request = this.printLine(builder, request, 1, 1, 'center', false, false, 'mail: office@eppel-boote.at');
                    request = this.printLine(builder, request, 1, 1, 'center', false, false, 'web: www.eppel-boote.at');
                    return request;
                };
                Printer.prototype.printCompanyDataBill = function (bill, builder, request) {
                    request = this.printLine(builder, request, 1, 1, 'center', false, false, 'Christiane Ahammer');
                    request = this.printLine(builder, request, 1, 1, 'center', false, false, bill.company.zip + ' ' + bill.company.city + ', ' + bill.company.street + ", " + bill.company.atu);
                    request = this.printLine(builder, request, 1, 1, 'center', false, false, 'tel: ' + bill.company.phone + ', web: www.eppel-boote.at');
                    request = this.printLine(builder, request, 1, 1, 'center', false, false, 'mail: ' + bill.company.mail);
                    return request;
                };
                Printer.prototype.printReceiptType = function (bill, builder, request) {
                    request = this.printLine(builder, request, 1, 1, 'center', true, false, '***** ' + bill.receiptType + ' *****');
                    if ('Sammel-Beleg' === bill.receiptType) {
                        request = this.printLine(builder, request, 1, 1, 'center', false, false, this.pp.printDateAndTime(bill.sammelBelegStart) + ' - ' + this.pp.printDateAndTime(bill.sammelBelegEnd));
                    }
                    return request;
                };
                Printer.prototype.printSignatureDeviceDamaged = function (bill, builder, request) {
                    if (bill.signatureDeviceAvailable === false) {
                        request = this.printLine(builder, request, 1, 1, 'center', true, false, 'Signatureinrichtung ausgefallen!');
                    }
                    return request;
                };
                Printer.prototype.printTaxSetElements = function (bill, builder, request) {
                    var _this = this;
                    request = this.printText(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength('     ', 4, prettyprinter_1.Align.LEFT));
                    request = this.printText(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength('Produkt', 16, prettyprinter_1.Align.LEFT));
                    request = this.printText(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength(' ', 4, prettyprinter_1.Align.LEFT));
                    request = this.printText(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength('Netto', 8, prettyprinter_1.Align.RIGHT));
                    request = this.printText(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength('MWST', 6, prettyprinter_1.Align.RIGHT));
                    request = this.printLine(builder, request, 1, 1, 'left', true, false, this.pp.ppFixLength('Brutto', 8, prettyprinter_1.Align.RIGHT));
                    bill.taxSetElements.forEach(function (tse) {
                        request = _this.printText(builder, request, 1, 1, 'left', false, false, _this.pp.ppFixLength(tse.amount + ' ', 4, prettyprinter_1.Align.LEFT));
                        request = _this.printText(builder, request, 1, 1, 'left', false, false, _this.pp.ppFixLength(tse.name, 16, prettyprinter_1.Align.LEFT));
                        request = _this.printText(builder, request, 1, 1, 'left', false, false, _this.pp.ppFixLength(tse.taxPercent + '%', 4, prettyprinter_1.Align.RIGHT));
                        request = _this.printText(builder, request, 1, 1, 'left', false, false, _this.pp.ppFixLength(_this.pp.ppPrice(tse.pricePreTax), 8, prettyprinter_1.Align.RIGHT));
                        request = _this.printText(builder, request, 1, 1, 'left', false, false, _this.pp.ppFixLength(_this.pp.ppPrice(tse.priceTax), 6, prettyprinter_1.Align.RIGHT));
                        request = _this.printLine(builder, request, 1, 1, 'left', true, false, _this.pp.ppFixLength(_this.pp.ppPrice(tse.priceAfterTax), 8, prettyprinter_1.Align.RIGHT));
                    });
                    request += builder.createRuledLineElement({ thickness: 'medium', width: 832 });
                    request = this.printLine(builder, request, 2, 1, 'left', true, false, "          Summe " + this.pp.ppPrice(bill.sumTotal));
                    return request;
                };
                Printer.prototype.printSumTaxes = function (bill, builder, request) {
                    request = this.printSumTax("20", bill.sumTaxSetNormal, builder, request);
                    request = this.printSumTax("10", bill.sumTaxSetErmaessigt1, builder, request);
                    request = this.printSumTax("13", bill.sumTaxSetErmaessigt2, builder, request);
                    request = this.printSumTax(" 0", bill.sumTaxSetNull, builder, request);
                    request = this.printSumTax("19", bill.sumTaxSetBesonders, builder, request);
                    return request;
                };
                Printer.prototype.printSumTax = function (taxPercent, sum, builder, request) {
                    if (lang_1.isPresent(sum) && sum > 0) {
                        request = this.printLine(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength(taxPercent + '% MWST: ' + this.pp.ppPrice(sum), 40, prettyprinter_1.Align.RIGHT));
                    }
                    return request;
                };
                Printer.prototype.addBoat = function (builder, request, rental) {
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
                    if (lang_1.isPresent(rental.pricePaidBefore)) {
                        request = this.printText(builder, request, 1, 2, 'left', false, false, '   ');
                        request = this.printText(builder, request, 1, 2, 'left', false, true, 'Aktion');
                        request = this.printLine(builder, request, 1, 2, 'left', true, false, ': ' + rental.promotionBefore.name);
                        request = this.printText(builder, request, 1, 2, 'left', false, false, '   ');
                        request = this.printText(builder, request, 1, 2, 'left', false, true, 'Bezahlt');
                        request = this.printLine(builder, request, 1, 2, 'left', true, false, ': ' + this.pp.ppPrice(rental.pricePaidBefore, '€ '));
                    }
                    return request;
                };
                //noinspection JSMethodCanBeStatic
                Printer.prototype.mapBoatToLogoName = function (boat) {
                    if (boat.shortName === "E") {
                        return "20";
                    }
                    else if (boat.shortName === "T2") {
                        return "21";
                    }
                    else if (boat.shortName === "T4") {
                        return "22";
                    }
                    else if (boat.shortName === "TR") {
                        return "23";
                    }
                    else if (boat.shortName === "L") {
                        return "24";
                    }
                    else if (boat.shortName === "P") {
                        return "25";
                    }
                };
                Printer.prototype.add5MinuteInfo = function (builder, request) {
                    request = this.blankLine(builder, request);
                    request = this.printLine(builder, request, 1, 1, 'center', false, false, 'Hinweis: Es werden (für das Ein-/Aussteigen)');
                    request = this.printLine(builder, request, 1, 1, 'center', false, false, '5 Minuten auf die Fahrzeit gutgeschrieben!');
                    return request;
                };
                Printer.prototype.addMyRental = function (builder, request, rental) {
                    request = this.blankLine(builder, request);
                    request = this.printLine(builder, request, 2, 2, 'center', false, false, '*** *** NEU *** ***');
                    request = this.blankLine(builder, request);
                    request = this.printLine(builder, request, 1, 1, 'center', false, false, 'Alle Infos zu deiner Bootsfahrt jetzt online!');
                    request = this.printLine(builder, request, 1, 1, 'center', false, false, 'QR-Code am Smartphone scannen und Link öffnen!');
                    var myRentalUrl = 'https://www.eppel-boote.at/myrental/index.php?id=' + rental.myRentalId;
                    console.log("myRentalUrl: " + myRentalUrl);
                    request += builder.createQrCodeElement({
                        model: 'model2',
                        level: 'level_l',
                        cell: 4,
                        data: myRentalUrl
                    });
                    return request;
                };
                Printer.prototype.addCommitmentReturnInfo = function (builder, request) {
                    request = this.blankLine(builder, request);
                    request = this.printLine(builder, request, 1, 1, 'center', false, false, 'Achtung: Der Einsatz kann ausschließlich nach');
                    request = this.printLine(builder, request, 1, 1, 'center', false, false, 'Vorlage dieses Belegs retourniert werden!');
                    return request;
                };
                //noinspection JSMethodCanBeStatic
                Printer.prototype.printLogo = function (builder, request, logo, align) {
                    request += builder.createAlignmentElement({ position: align });
                    request += builder.createLogoElement({ number: logo, width: 'single', height: 'single' });
                    return request;
                };
                Printer.prototype.blankLine = function (builder, request) {
                    request = this.printLine(builder, request, 1, 1, 'center', false, false, '');
                    return request;
                };
                Printer.prototype.printLine = function (builder, request, width, height, align, emphasis, underline, data) {
                    request = this.printText(builder, request, width, height, align, emphasis, underline, data + "\n");
                    return request;
                };
                //noinspection JSMethodCanBeStatic
                Printer.prototype.printText = function (builder, request, width, height, align, emphasis, underline, data) {
                    request += builder.createAlignmentElement({ position: align });
                    request += builder.createTextElement({
                        width: width,
                        height: height,
                        emphasis: emphasis,
                        underline: underline,
                        codepage: 'utf8',
                        data: data
                    });
                    return request;
                };
                Printer.prototype.printPaper = function (builder, request, printerIp) {
                    // cut
                    request += builder.createCutPaperElement({ feed: true });
                    //noinspection TypeScriptUnresolvedFunction
                    var trader = new StarWebPrintTrader({ url: 'http://' + printerIp + '/StarWebPRNT/SendMessage' });
                    // print
                    trader.sendMessage({ request: request });
                };
                Printer.prototype.printJournal = function (journalReport, printerIp) {
                    console.log("print journal on " + printerIp);
                    if (lang_1.isPresent(journalReport)) {
                        console.log("print journal between " + this.pp.printDate(journalReport.start) + " and " + this.pp.printDate(journalReport.end) + " on " + printerIp);
                        //noinspection TypeScriptUnresolvedFunction
                        var builder = new StarWebPrintBuilder();
                        var request = builder.createInitializationElement();
                        request = this.addLogo(builder, request);
                        request = this.printCompleteJournal(journalReport, builder, request);
                        request = this.printLogo(builder, request, '13', 'center');
                        this.printPaper(builder, request, printerIp);
                    }
                };
                Printer.prototype.printCompleteJournal = function (journalReport, builder, request) {
                    var _this = this;
                    request = this.printLine(builder, request, 2, 2, "center", true, false, "Einnahmen Bootsverm.");
                    request = this.blankLine(builder, request);
                    if (this.pp.printDate(journalReport.start) === this.pp.printDate(journalReport.end)) {
                        request = this.printLine(builder, request, 1, 1, "left", true, false, "Datum: " + this.pp.printDate(journalReport.start));
                    }
                    else {
                        request = this.printLine(builder, request, 1, 1, "left", true, false, "Zeitraum: " + this.pp.printDate(journalReport.start) + " - " + this.pp.printDate(journalReport.end));
                    }
                    request = this.blankLine(builder, request);
                    var sumBoat = 0;
                    request = this.printLine(builder, request, 1, 1, "left", true, false, "Anzahl Vermietungen");
                    journalReport.journalReportItems.forEach(function (jri) {
                        request = _this.printText(builder, request, 1, 1, "left", false, false, _this.pp.ppFixLength(jri.boatName + ":", 18, prettyprinter_1.Align.LEFT));
                        request = _this.printLine(builder, request, 1, 1, "left", false, false, _this.pp.ppFixLength(String(jri.count), 10, prettyprinter_1.Align.RIGHT));
                        sumBoat += jri.count;
                    });
                    request = this.printLine(builder, request, 1, 1, "left", true, false, this.pp.ppFixLength("SUMME:", 18, prettyprinter_1.Align.LEFT) + this.pp.ppFixLength(String(sumBoat), 10, prettyprinter_1.Align.RIGHT));
                    request = this.blankLine(builder, request);
                    var sumCash = 0;
                    var sumCashBeforeTax = 0;
                    var sumCashTax = 0;
                    request = this.printLine(builder, request, 1, 1, "left", true, false, "Bargeld");
                    journalReport.journalReportItems.forEach(function (jri) {
                        request = _this.printText(builder, request, 1, 1, "left", false, false, _this.pp.ppFixLength(jri.boatName + ":", 18, prettyprinter_1.Align.LEFT));
                        request = _this.printLine(builder, request, 1, 1, "left", false, false, _this.pp.ppFixLength(_this.pp.ppPrice(jri.pricePaidBeforeCash + jri.pricePaidAfterCash), 10, prettyprinter_1.Align.RIGHT));
                        sumCash += (jri.pricePaidBeforeCash + jri.pricePaidAfterCash);
                        sumCashBeforeTax += (jri.pricePaidBeforeCashBeforeTax + jri.pricePaidAfterCashBeforeTax);
                        sumCashTax += (jri.pricePaidBeforeCashTax + jri.pricePaidAfterCashTax);
                    });
                    request = this.printLine(builder, request, 1, 1, "left", true, false, this.pp.ppFixLength("SUMME:", 8, prettyprinter_1.Align.LEFT) +
                        this.pp.ppFixLength(this.pp.ppPrice(sumCash), 10, prettyprinter_1.Align.RIGHT) + " / " +
                        this.pp.ppFixLength(this.pp.ppPrice(sumCashTax), 9, prettyprinter_1.Align.RIGHT) + " / " +
                        this.pp.ppFixLength(this.pp.ppPrice(sumCashBeforeTax), 10, prettyprinter_1.Align.RIGHT));
                    request = this.blankLine(builder, request);
                    var sumCard = 0;
                    var sumCardBeforeTax = 0;
                    var sumCardTax = 0;
                    request = this.printLine(builder, request, 1, 1, "left", true, false, "Karte");
                    journalReport.journalReportItems.forEach(function (jri) {
                        request = _this.printText(builder, request, 1, 1, "left", false, false, _this.pp.ppFixLength(jri.boatName + ":", 18, prettyprinter_1.Align.LEFT));
                        request = _this.printLine(builder, request, 1, 1, "left", false, false, _this.pp.ppFixLength(_this.pp.ppPrice(jri.pricePaidBeforeCard + jri.pricePaidAfterCard), 10, prettyprinter_1.Align.RIGHT));
                        sumCard += (jri.pricePaidBeforeCard + jri.pricePaidAfterCard);
                        sumCardBeforeTax += (jri.pricePaidBeforeCardBeforeTax + jri.pricePaidAfterCardBeforeTax);
                        sumCardTax += (jri.pricePaidBeforeCardTax + jri.pricePaidAfterCardTax);
                    });
                    request = this.printLine(builder, request, 1, 1, "left", true, false, this.pp.ppFixLength("SUMME:", 8, prettyprinter_1.Align.LEFT) +
                        this.pp.ppFixLength(this.pp.ppPrice(sumCard), 10, prettyprinter_1.Align.RIGHT) + " / " +
                        this.pp.ppFixLength(this.pp.ppPrice(sumCardTax), 9, prettyprinter_1.Align.RIGHT) + " / " +
                        this.pp.ppFixLength(this.pp.ppPrice(sumCardBeforeTax), 10, prettyprinter_1.Align.RIGHT));
                    request = this.blankLine(builder, request);
                    request = this.printLine(builder, request, 1, 1, "left", true, false, this.pp.ppFixLength("TOTAL:", 8, prettyprinter_1.Align.LEFT) +
                        this.pp.ppFixLength(this.pp.ppPrice(sumCash + sumCard), 10, prettyprinter_1.Align.RIGHT) + " / " +
                        this.pp.ppFixLength(this.pp.ppPrice(sumCashTax + sumCardTax), 9, prettyprinter_1.Align.RIGHT) + " / " +
                        this.pp.ppFixLength(this.pp.ppPrice(sumCashBeforeTax + sumCardBeforeTax), 10, prettyprinter_1.Align.RIGHT));
                    return request;
                };
                Printer.prototype.printCompleteIncome = function (income, builder, request) {
                    var _this = this;
                    request = this.printLine(builder, request, 2, 2, "center", true, false, "Einnahmen Bootsverm.");
                    request = this.blankLine(builder, request);
                    if (this.pp.printDate(income.start) === this.pp.printDate(income.end)) {
                        request = this.printLine(builder, request, 1, 1, "left", true, false, "Datum: " + this.pp.printDate(income.start));
                    }
                    else {
                        request = this.printLine(builder, request, 1, 1, "left", true, false, "Zeitraum: " + this.pp.printDate(income.start) + " - " + this.pp.printDate(income.end));
                    }
                    if (lang_1.isPresent(income.incomeProductGroups)) {
                        request = this.blankLine(builder, request);
                        income.incomeProductGroups.forEach(function (ipg) {
                            request = _this.printText(builder, request, 1, 1, "left", false, false, _this.pp.ppFixLength(ipg.name, 26, prettyprinter_1.Align.LEFT));
                            request = _this.printText(builder, request, 1, 1, "left", false, false, _this.pp.ppFixLength("  " + ipg.taxPercent + "%", 6, prettyprinter_1.Align.LEFT));
                            request = _this.printLine(builder, request, 1, 1, "left", false, false, _this.pp.ppFixLength("  " + _this.pp.ppPrice(ipg.income), 10, prettyprinter_1.Align.RIGHT));
                        });
                    }
                    request += builder.createRuledLineElement({ thickness: 'medium', width: 832 });
                    request = this.printLine(builder, request, 1, 1, "left", true, false, this.pp.ppFixLength("SUMME:", 26, prettyprinter_1.Align.LEFT) + this.pp.ppFixLength(this.pp.ppPrice(income.totalIncome), 16, prettyprinter_1.Align.RIGHT));
                    if (lang_1.isPresent(income.taxElements)) {
                        request = this.blankLine(builder, request);
                        income.taxElements.forEach(function (te) {
                            if (te.price > 0) {
                                request = _this.printText(builder, request, 1, 1, "left", false, false, _this.pp.ppFixLength(te.taxPercent + "%", 6, prettyprinter_1.Align.LEFT));
                                request = _this.printText(builder, request, 1, 1, "left", false, false, _this.pp.ppFixLength("  " + _this.pp.ppPrice(te.price), 12, prettyprinter_1.Align.RIGHT));
                                request = _this.printText(builder, request, 1, 1, "left", false, false, _this.pp.ppFixLength(" /  " + _this.pp.ppPrice(te.priceTax), 14, prettyprinter_1.Align.RIGHT));
                                request = _this.printLine(builder, request, 1, 1, "left", false, false, _this.pp.ppFixLength(" /  " + _this.pp.ppPrice(te.priceBeforeTax), 14, prettyprinter_1.Align.RIGHT));
                            }
                        });
                    }
                    return request;
                };
                Printer.prototype.printTest = function (printerIp) {
                    console.log("test printer on " + printerIp);
                    //noinspection TypeScriptUnresolvedFunction
                    var builder = new StarWebPrintBuilder();
                    var request = builder.createInitializationElement();
                    request += builder.createTextElement({
                        codepage: 'utf8',
                        data: 'Drucker für das Abrechnungssystem funktioniert!\n\n'
                    });
                    // cut
                    request += builder.createCutPaperElement({ feed: true });
                    //noinspection TypeScriptUnresolvedFunction
                    var trader = new StarWebPrintTrader({ url: 'http://' + printerIp + '/StarWebPRNT/SendMessage' });
                    // print
                    trader.sendMessage({ request: request });
                };
                Printer = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [prettyprinter_1.PrettyPrinter])
                ], Printer);
                return Printer;
            }());
            exports_1("Printer", Printer);
        }
    }
});
