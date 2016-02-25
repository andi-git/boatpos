System.register(['angular2/core', "angular2/src/facade/lang", "./config.service", "./prettyprinter"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, lang_1, config_service_1, prettyprinter_1;
    var Printer;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (lang_1_1) {
                lang_1 = lang_1_1;
            },
            function (config_service_1_1) {
                config_service_1 = config_service_1_1;
            },
            function (prettyprinter_1_1) {
                prettyprinter_1 = prettyprinter_1_1;
            }],
        execute: function() {
            Printer = (function () {
                function Printer(configService, pp) {
                    this.configService = configService;
                    this.pp = pp;
                }
                Printer.prototype.printDepart = function (rental) {
                    var _this = this;
                    if (lang_1.isPresent(rental)) {
                        //noinspection TypeScriptUnresolvedFunction
                        var builder = new StarWebPrintBuilder();
                        var request = builder.createInitializationElement();
                        request = this.addLogo(builder, request);
                        request = this.addBoat(builder, request, rental);
                        request = this.add5MinuteInfo(builder, request);
                        this.printPaper(builder, request);
                        setTimeout(function () { return _this.printNumberForCommitment(rental); }, 3000);
                    }
                };
                Printer.prototype.printNumberForCommitment = function (rental) {
                    console.log("rental: " + rental);
                    if (lang_1.isPresent(rental) && lang_1.isPresent(rental.commitments)) {
                        var needPaper = false;
                        rental.commitments.forEach(function (commitment) {
                            if (commitment.paper) {
                                needPaper = true;
                            }
                        });
                        console.log("need paper: " + needPaper);
                        if (needPaper === true) {
                            var dayIdString = this.pp.pp3Pos(rental.dayId);
                            //noinspection TypeScriptUnresolvedFunction
                            var builder = new StarWebPrintBuilder();
                            var request = builder.createInitializationElement();
                            request = this.blankLine(builder, request);
                            request = this.printLogo(builder, request, this.convertFromNumberToLogoName(dayIdString.charAt(0)), 'left');
                            request = this.printLogo(builder, request, this.convertFromNumberToLogoName(dayIdString.charAt(1)), 'left');
                            request = this.printLogo(builder, request, this.convertFromNumberToLogoName(dayIdString.charAt(2)), 'left');
                            request = this.blankLine(builder, request);
                            request = this.blankLine(builder, request);
                            this.printPaper(builder, request);
                        }
                    }
                };
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
                    request = this.printLine(builder, request, 1, 1, 'center', false, false, 'Christiane Ahammer');
                    request = this.printLine(builder, request, 1, 1, 'center', false, false, '1220 Wien, Wagramertraße 48a');
                    request = this.printLine(builder, request, 1, 1, 'center', false, false, 'tel: +43 1 2633530');
                    request = this.printLine(builder, request, 1, 1, 'center', false, false, 'mail: office@eppel-boote.at');
                    request = this.printLine(builder, request, 1, 1, 'center', false, false, 'web: www.eppel-boote.at');
                    return request;
                };
                Printer.prototype.addBoat = function (builder, request, rental) {
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
                    if (lang_1.isPresent(rental.pricePaidBefore)) {
                        request = this.printText(builder, request, 1, 2, 'left', false, true, 'Aktion');
                        request = this.printLine(builder, request, 1, 2, 'left', true, false, ': ' + rental.promotionBefore.name);
                        request = this.printText(builder, request, 1, 2, 'left', false, true, 'Bezahlt');
                        request = this.printLine(builder, request, 1, 2, 'left', true, false, ': ' + this.pp.ppPrice(rental.pricePaidBefore, '€ '));
                    }
                    return request;
                };
                Printer.prototype.mapBoatToLogoName = function (boat) {
                    if (boat.shortName === "E") {
                        return "20";
                    }
                    else if (boat.shortName === "T2") {
                        return "21";
                    }
                    else if (boat.shortName === "T2") {
                        return "22";
                    }
                    else if (boat.shortName === "T4") {
                        return "23";
                    }
                    else if (boat.shortName === "TR") {
                        return "24";
                    }
                    else if (boat.shortName === "L") {
                        return "25";
                    }
                    else if (boat.shortName === "P") {
                        return "26";
                    }
                };
                Printer.prototype.add5MinuteInfo = function (builder, request) {
                    request = this.blankLine(builder, request);
                    request = this.printLine(builder, request, 1, 1, 'center', false, false, 'Hinweis: Es werden (für das Ein-/Aussteigen)');
                    request = this.printLine(builder, request, 1, 1, 'center', false, false, '5 Minuten auf Fahrzeit gutgeschrieben!');
                    return request;
                };
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
                Printer.prototype.printPaper = function (builder, request) {
                    // cut
                    request += builder.createCutPaperElement({ feed: true });
                    //noinspection TypeScriptUnresolvedFunction
                    var trader = new StarWebPrintTrader({ url: 'http://' + this.configService.getPrinterUrl() + '/StarWebPRNT/SendMessage' });
                    // print
                    trader.sendMessage({ request: request });
                };
                Printer = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [config_service_1.ConfigService, prettyprinter_1.PrettyPrinter])
                ], Printer);
                return Printer;
            })();
            exports_1("Printer", Printer);
        }
    }
});
