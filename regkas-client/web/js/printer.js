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
            // import {JournalReport} from "./model/journalReport";
            Printer = (function () {
                function Printer(pp) {
                    this.pp = pp;
                }
                Printer.prototype.printBill = function (bill, printerIp) {
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
                    }
                    request += builder.createPeripheralElement({ channel: 1, on: 200, off: 200 });
                    this.printPaper(builder, request, printerIp);
                };
                Printer.prototype.printCompleteBill = function (bill, builder, request) {
                    request = this.addLogo(builder, request);
                    request = this.printLine(builder, request, 2, 2, 'center', true, false, 'Rechnung');
                    request = this.printLine(builder, request, 1, 1, 'center', true, false, 'ID: ' + bill.receiptIdentifier + ", " + 'Kassa: ' + bill.cashBoxId);
                    request = this.printLine(builder, request, 1, 1, 'center', true, false, 'Datum: ' + this.pp.printDate(bill.receiptDateAndTime) + ", " + this.pp.printTime(bill.receiptDateAndTime) + " Uhr");
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
                    request = this.printLogo(builder, request, 10, 'center');
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
                        request = _this.printText(builder, request, 1, 1, 'left', false, false, _this.pp.ppFixLength(' ' + tse.taxPercent + '%', 4, prettyprinter_1.Align.RIGHT));
                        request = _this.printText(builder, request, 1, 1, 'left', false, false, _this.pp.ppFixLength(_this.pp.ppPrice(tse.pricePreTax, ''), 8, prettyprinter_1.Align.RIGHT));
                        request = _this.printText(builder, request, 1, 1, 'left', false, false, _this.pp.ppFixLength(_this.pp.ppPrice(tse.priceTax, ''), 6, prettyprinter_1.Align.RIGHT));
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
                    // TODO revert to 0 after corona
                    request = this.printSumTax(" 5", bill.sumTaxSetNull, builder, request);
                    request = this.printSumTax("19", bill.sumTaxSetBesonders, builder, request);
                    return request;
                };
                Printer.prototype.printSumTax = function (taxPercent, sum, builder, request) {
                    if (lang_1.isPresent(sum) && sum > 0) {
                        request = this.printLine(builder, request, 1, 1, 'left', false, false, this.pp.ppFixLength(taxPercent + '% MWST: ' + this.pp.ppPrice(sum), 40, prettyprinter_1.Align.RIGHT));
                    }
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
                Printer.prototype.printIncome = function (income, printerIp) {
                    console.log("print income on " + printerIp);
                    if (lang_1.isPresent(income)) {
                        console.log("print journal between " + this.pp.printDate(income.start) + " and " + this.pp.printDate(income.end));
                        // noinspection TypeScriptUnresolvedFunction
                        var builder = new StarWebPrintBuilder();
                        var request = builder.createInitializationElement();
                        request = this.addLogo(builder, request);
                        request = this.printCompleteIncome(income, builder, request);
                        this.printPaper(builder, request, printerIp);
                    }
                };
                Printer.prototype.printCompleteIncome = function (income, builder, request) {
                    var _this = this;
                    request = this.printLine(builder, request, 2, 2, "center", true, false, "Einnahmen Buffet");
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
                    request = this.blankLine(builder, request);
                    request = this.printLine(builder, request, 1, 1, "left", true, false, this.pp.ppFixLength("Cash:", 26, prettyprinter_1.Align.LEFT) + this.pp.ppFixLength(this.pp.ppPrice(income.paymentCash), 16, prettyprinter_1.Align.RIGHT));
                    request = this.printLine(builder, request, 1, 1, "left", true, false, this.pp.ppFixLength("Card:", 26, prettyprinter_1.Align.LEFT) + this.pp.ppFixLength(this.pp.ppPrice(income.paymentCard), 16, prettyprinter_1.Align.RIGHT));
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
                    request = this.printLogo(builder, request, 13, 'center');
                    return request;
                };
                Printer.prototype.printTest = function (printerIp) {
                    console.log("test printer on " + printerIp);
                    //noinspection TypeScriptUnresolvedFunction
                    var builder = new StarWebPrintBuilder();
                    var request = builder.createInitializationElement();
                    request += builder.createTextElement({
                        codepage: 'utf8',
                        data: 'Drucker für die Registrierkassa funktioniert!\n\n'
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
