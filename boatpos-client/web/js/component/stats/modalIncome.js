System.register(["angular2/core", "angular2/common", "lib/angular2-modal", "angular2/src/facade/lang"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, common_1, angular2_modal_1, lang_1;
    var ModalIncomeContext, ModalIncome;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (common_1_1) {
                common_1 = common_1_1;
            },
            function (angular2_modal_1_1) {
                angular2_modal_1 = angular2_modal_1_1;
            },
            function (lang_1_1) {
                lang_1 = lang_1_1;
            }],
        execute: function() {
            ModalIncomeContext = (function () {
                function ModalIncomeContext(journalService, pp, printer, config, year, month, day) {
                    this.journalService = journalService;
                    this.pp = pp;
                    this.printer = printer;
                    this.config = config;
                    this.year = year;
                    this.month = month;
                    this.day = day;
                }
                return ModalIncomeContext;
            })();
            exports_1("ModalIncomeContext", ModalIncomeContext);
            // this class is ugly because of a bug in angluar2-beta-0: https://github.com/angular/angular/issues/4330
            ModalIncome = (function () {
                function ModalIncome(dialog, modelContentData) {
                    var _this = this;
                    this.loaded = false;
                    this.dialog = dialog;
                    this.journalService = modelContentData.journalService;
                    // let resolvedProviders = Injector.resolve([provide(JournalService, {useValue: this.journalService})]);
                    this.printer = modelContentData.printer;
                    this.pp = modelContentData.pp;
                    this.config = modelContentData.config;
                    this.year = modelContentData.year;
                    this.month = modelContentData.month;
                    this.day = modelContentData.day;
                    this.journalService.income(this.year, this.month, this.day).subscribe(function (journalReport) {
                        console.log("report");
                        _this.report = journalReport;
                        _this.loaded = true;
                    });
                }
                ModalIncome.prototype.ppDate = function () {
                    if (lang_1.isPresent(this.report)) {
                        return this.pp.printDate(this.report.start) + " - " + this.pp.printDate(this.report.end);
                    }
                };
                ModalIncome.prototype.getCountForBoat = function (name) {
                    var result = 0;
                    if (lang_1.isPresent(this.report)) {
                        this.report.journalReportItems.forEach(function (item) {
                            if (item.boatName == name) {
                                result = item.count;
                            }
                        });
                    }
                    return result;
                };
                ModalIncome.prototype.getCountSum = function () {
                    var result = 0;
                    if (lang_1.isPresent(this.report)) {
                        this.report.journalReportItems.forEach(function (item) {
                            result += item.count;
                        });
                    }
                    return result;
                };
                ModalIncome.prototype.getCashBeforeTaxForBoat = function (name) {
                    var result = 0;
                    if (lang_1.isPresent(this.report)) {
                        this.report.journalReportItems.forEach(function (item) {
                            if (item.boatName == name) {
                                result = item.pricePaidAfterCashBeforeTax + item.pricePaidBeforeCashBeforeTax;
                            }
                        });
                    }
                    return result;
                };
                ModalIncome.prototype.getCashBeforeTax = function () {
                    var result = 0;
                    if (lang_1.isPresent(this.report)) {
                        this.report.journalReportItems.forEach(function (item) {
                            result += item.pricePaidAfterCashBeforeTax + item.pricePaidBeforeCashBeforeTax;
                        });
                    }
                    return result;
                };
                ModalIncome.prototype.getCashTaxForBoat = function (name) {
                    var result = 0;
                    if (lang_1.isPresent(this.report)) {
                        this.report.journalReportItems.forEach(function (item) {
                            if (item.boatName == name) {
                                result = item.pricePaidAfterCashTax + item.pricePaidBeforeCashTax;
                            }
                        });
                    }
                    return result;
                };
                ModalIncome.prototype.getCashTax = function () {
                    var result = 0;
                    if (lang_1.isPresent(this.report)) {
                        this.report.journalReportItems.forEach(function (item) {
                            result += item.pricePaidAfterCashTax + item.pricePaidBeforeCashTax;
                        });
                    }
                    return result;
                };
                ModalIncome.prototype.getCashAfterTaxForBoat = function (name) {
                    var result = 0;
                    if (lang_1.isPresent(this.report)) {
                        this.report.journalReportItems.forEach(function (item) {
                            if (item.boatName == name) {
                                result = item.pricePaidAfterCash + item.pricePaidBeforeCash;
                            }
                        });
                    }
                    return result;
                };
                ModalIncome.prototype.getCashAfterTax = function () {
                    var result = 0;
                    if (lang_1.isPresent(this.report)) {
                        this.report.journalReportItems.forEach(function (item) {
                            result += item.pricePaidAfterCash + item.pricePaidBeforeCash;
                        });
                    }
                    return result;
                };
                ModalIncome.prototype.getCardBeforeTaxForBoat = function (name) {
                    var result = 0;
                    if (lang_1.isPresent(this.report)) {
                        this.report.journalReportItems.forEach(function (item) {
                            if (item.boatName == name) {
                                result = item.pricePaidAfterCardBeforeTax + item.pricePaidBeforeCardBeforeTax;
                            }
                        });
                    }
                    return result;
                };
                ModalIncome.prototype.getCardBeforeTax = function () {
                    var result = 0;
                    if (lang_1.isPresent(this.report)) {
                        this.report.journalReportItems.forEach(function (item) {
                            result += item.pricePaidAfterCardBeforeTax + item.pricePaidBeforeCardBeforeTax;
                        });
                    }
                    return result;
                };
                ModalIncome.prototype.getCardTaxForBoat = function (name) {
                    var result = 0;
                    if (lang_1.isPresent(this.report)) {
                        this.report.journalReportItems.forEach(function (item) {
                            if (item.boatName == name) {
                                result = item.pricePaidAfterCardTax + item.pricePaidBeforeCardTax;
                            }
                        });
                    }
                    return result;
                };
                ModalIncome.prototype.getCardTax = function () {
                    var result = 0;
                    if (lang_1.isPresent(this.report)) {
                        this.report.journalReportItems.forEach(function (item) {
                            result += item.pricePaidAfterCardTax + item.pricePaidBeforeCardTax;
                        });
                    }
                    return result;
                };
                ModalIncome.prototype.getCardAfterTaxForBoat = function (name) {
                    var result = 0;
                    if (lang_1.isPresent(this.report)) {
                        this.report.journalReportItems.forEach(function (item) {
                            if (item.boatName == name) {
                                result = item.pricePaidAfterCard + item.pricePaidBeforeCard;
                            }
                        });
                    }
                    return result;
                };
                ModalIncome.prototype.getCardAfterTax = function () {
                    var result = 0;
                    if (lang_1.isPresent(this.report)) {
                        this.report.journalReportItems.forEach(function (item) {
                            result += item.pricePaidAfterCard + item.pricePaidBeforeCard;
                        });
                    }
                    return result;
                };
                ModalIncome.prototype.getSumBeforeTaxForBoat = function (name) {
                    return this.getCashBeforeTaxForBoat(name) + this.getCardBeforeTaxForBoat(name);
                };
                ModalIncome.prototype.getSumBeforeTax = function () {
                    return this.getCashBeforeTax() + this.getCardBeforeTax();
                };
                ModalIncome.prototype.getSumTaxForBoat = function (name) {
                    return this.getCashTaxForBoat(name) + this.getCardTaxForBoat(name);
                };
                ModalIncome.prototype.getSumTax = function () {
                    return this.getCashTax() + this.getCardTax();
                };
                ModalIncome.prototype.getSumAfterTaxForBoat = function (name) {
                    return this.getCashAfterTaxForBoat(name) + this.getCardAfterTaxForBoat(name);
                };
                ModalIncome.prototype.getSumAfterTax = function () {
                    return this.getCashAfterTax() + this.getCardAfterTax();
                };
                ModalIncome.prototype.close = function ($event) {
                    $event.stopPropagation();
                    //noinspection TypeScriptUnresolvedFunction
                    this.dialog.close(true);
                };
                ModalIncome.prototype.print = function ($event) {
                    this.printer.printJournal(this.report, this.config.getPrinterIp());
                };
                ModalIncome.prototype.cancel = function () {
                    //noinspection TypeScriptUnresolvedFunction
                    this.dialog.dismiss();
                };
                ModalIncome = __decorate([
                    core_1.Component({
                        selector: 'modal-content',
                        directives: [common_1.NgIf, common_1.NgFor],
                        template: "<div class=\"modal-header\">\n        <h2 class=\"header header-main\">Einnahmen: {{ppDate()}}</h2>\n        </div>\n        <div class=\"modal-body\">\n            <table class=\"table-no-style\">\n                <tr>\n                    <td class=\"button-ok\">Boot</td>\n                    <td class=\"button-ok\">Anzahl</td>\n                    <td class=\"button-ok\">Bar</td>\n                    <td class=\"button-ok\">Karte</td>\n                    <td class=\"button-ok\">Summe</td>\n                </tr>\n                <tr>\n                    <td valign=\"top\">E-Boot</td>\n                    <td valign=\"top\">{{getCountForBoat('E-Boot')}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getCashBeforeTaxForBoat('E-Boot'))}}<br/>{{pp.ppPrice(getCashTaxForBoat('E-Boot'))}}<br/>{{pp.ppPrice(getCashAfterTaxForBoat('E-Boot'))}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getCardBeforeTaxForBoat('E-Boot'))}}<br/>{{pp.ppPrice(getCardTaxForBoat('E-Boot'))}}<br/>{{pp.ppPrice(getCardAfterTaxForBoat('E-Boot'))}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getSumBeforeTaxForBoat('E-Boot'))}}<br/>{{pp.ppPrice(getSumTaxForBoat('E-Boot'))}}<br/>{{pp.ppPrice(getSumAfterTaxForBoat('E-Boot'))}}</td>\n                </tr>\n                <tr>\n                    <td valign=\"top\">Tretboot klein</td>\n                    <td valign=\"top\">{{getCountForBoat('Tretboot klein')}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getCashBeforeTaxForBoat('Tretboot klein'))}}<br/>{{pp.ppPrice(getCashTaxForBoat('Tretboot klein'))}}<br/>{{pp.ppPrice(getCashAfterTaxForBoat('Tretboot klein'))}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getCardBeforeTaxForBoat('Tretboot klein'))}}<br/>{{pp.ppPrice(getCardTaxForBoat('Tretboot klein'))}}<br/>{{pp.ppPrice(getCardAfterTaxForBoat('Tretboot klein'))}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getSumBeforeTaxForBoat('Tretboot klein'))}}<br/>{{pp.ppPrice(getSumTaxForBoat('Tretboot klein'))}}<br/>{{pp.ppPrice(getSumAfterTaxForBoat('Tretboot klein'))}}</td>\n                </tr>\n                <tr>\n                    <td valign=\"top\">Tretboot gro\u00DF</td>\n                    <td valign=\"top\">{{getCountForBoat('Tretboot gro\u00DF')}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getCashBeforeTaxForBoat('Tretboot gro\u00DF'))}}<br/>{{pp.ppPrice(getCashTaxForBoat('Tretboot gro\u00DF'))}}<br/>{{pp.ppPrice(getCashAfterTaxForBoat('Tretboot gro\u00DF'))}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getCardBeforeTaxForBoat('Tretboot gro\u00DF'))}}<br/>{{pp.ppPrice(getCardTaxForBoat('Tretboot gro\u00DF'))}}<br/>{{pp.ppPrice(getCardAfterTaxForBoat('Tretboot gro\u00DF'))}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getSumBeforeTaxForBoat('Tretboot gro\u00DF'))}}<br/>{{pp.ppPrice(getSumTaxForBoat('Tretboot gro\u00DF'))}}<br/>{{pp.ppPrice(getSumAfterTaxForBoat('Tretboot gro\u00DF'))}}</td>\n                </tr>\n                <tr>\n                    <td valign=\"top\">Tretboot Rutsche</td>\n                    <td valign=\"top\">{{getCountForBoat('Tretboot Rutsche')}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getCashBeforeTaxForBoat('Tretboot Rutsche'))}}<br/>{{pp.ppPrice(getCashTaxForBoat('Tretboot Rutsche'))}}<br/>{{pp.ppPrice(getCashAfterTaxForBoat('Tretboot Rutsche'))}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getCardBeforeTaxForBoat('Tretboot Rutsche'))}}<br/>{{pp.ppPrice(getCardTaxForBoat('Tretboot Rutsche'))}}<br/>{{pp.ppPrice(getCardAfterTaxForBoat('Tretboot Rutsche'))}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getSumBeforeTaxForBoat('Tretboot Rutsche'))}}<br/>{{pp.ppPrice(getSumTaxForBoat('Tretboot Rutsche'))}}<br/>{{pp.ppPrice(getSumAfterTaxForBoat('Tretboot Rutsche'))}}</td>\n                </tr>\n                <tr>\n                    <td valign=\"top\">Liegeboot</td>\n                    <td valign=\"top\">{{getCountForBoat('Liegeboot')}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getCashBeforeTaxForBoat('Liegeboot'))}}<br/>{{pp.ppPrice(getCashTaxForBoat('Liegeboot'))}}<br/>{{pp.ppPrice(getCashAfterTaxForBoat('Liegeboot'))}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getCardBeforeTaxForBoat('Liegeboot'))}}<br/>{{pp.ppPrice(getCardTaxForBoat('Liegeboot'))}}<br/>{{pp.ppPrice(getCardAfterTaxForBoat('Liegeboot'))}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getSumBeforeTaxForBoat('Liegeboot'))}}<br/>{{pp.ppPrice(getSumTaxForBoat('Liegeboot'))}}<br/>{{pp.ppPrice(getSumAfterTaxForBoat('Liegeboot'))}}</td>\n                </tr>\n                <tr>\n                    <td valign=\"top\" class=\"button-action\">Summe</td>\n                    <td valign=\"top\" class=\"button-action\">{{getCountSum()}}</td>\n                    <td align=\"right\" valign=\"top\" class=\"button-action\">{{pp.ppPrice(getCashBeforeTax())}}<br/>{{pp.ppPrice(getCashTax())}}<br/>{{pp.ppPrice(getCashAfterTax())}}</td>\n                    <td align=\"right\" valign=\"top\" class=\"button-action\">{{pp.ppPrice(getCardBeforeTax())}}<br/>{{pp.ppPrice(getCardTax())}}<br/>{{pp.ppPrice(getCardAfterTax())}}</td>\n                    <td align=\"right\" valign=\"top\" class=\"button-cancel\">{{pp.ppPrice(getSumBeforeTax())}}<br/>{{pp.ppPrice(getSumTax())}}<br/>{{pp.ppPrice(getSumAfterTax())}}</td>\n                </tr>\n            </table>\n        </div>\n        <div class=\"modal-footer\">\n            <button class=\"buttonSmall button-action\" (click)=\"print($event)\">Drucken</button>\n            <button class=\"buttonSmall button-ok\" (click)=\"close($event)\">Schlie\u00DFen</button>\n        </div>",
                    }), 
                    __metadata('design:paramtypes', [(typeof (_a = typeof angular2_modal_1.ModalDialogInstance !== 'undefined' && angular2_modal_1.ModalDialogInstance) === 'function' && _a) || Object, (typeof (_b = typeof angular2_modal_1.ICustomModal !== 'undefined' && angular2_modal_1.ICustomModal) === 'function' && _b) || Object])
                ], ModalIncome);
                return ModalIncome;
                var _a, _b;
            })();
            exports_1("ModalIncome", ModalIncome);
        }
    }
});
