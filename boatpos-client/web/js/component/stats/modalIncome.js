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
                ModalIncome.prototype.getCount = function (name) {
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
                ModalIncome.prototype.getCashBeforeTax = function (name) {
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
                ModalIncome.prototype.getCashTax = function (name) {
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
                ModalIncome.prototype.getCashAfterTax = function (name) {
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
                ModalIncome.prototype.getCardBeforeTax = function (name) {
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
                ModalIncome.prototype.getCardTax = function (name) {
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
                ModalIncome.prototype.getCardAfterTax = function (name) {
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
                ModalIncome.prototype.getSumBeforeTax = function (name) {
                    return this.getCashBeforeTax(name) + this.getCardBeforeTax(name);
                };
                ModalIncome.prototype.getSumBeforeTax = function () {
                    return this.getCashBeforeTax() + this.getCardBeforeTax();
                };
                ModalIncome.prototype.getSumTax = function (name) {
                    return this.getCashTax(name) + this.getCardTax(name);
                };
                ModalIncome.prototype.getSumTax = function () {
                    return this.getCashTax() + this.getCardTax();
                };
                ModalIncome.prototype.getSumAfterTax = function (name) {
                    return this.getCashAfterTax(name) + this.getCardAfterTax(name);
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
                    this.printer.printJournal(this.report, this.config.printerIp);
                };
                ModalIncome.prototype.cancel = function () {
                    //noinspection TypeScriptUnresolvedFunction
                    this.dialog.dismiss();
                };
                ModalIncome = __decorate([
                    core_1.Component({
                        selector: 'modal-content',
                        directives: [common_1.NgIf, common_1.NgFor],
                        template: "<div class=\"modal-header\">\n        <h2 class=\"header header-main\">Einnahmen: {{ppDate()}}</h2>\n        </div>\n        <div class=\"modal-body\">\n            <table class=\"table-no-style\">\n                <tr>\n                    <td class=\"button-ok\">Boot</td>\n                    <td class=\"button-ok\">Anzahl</td>\n                    <td class=\"button-ok\">Bar</td>\n                    <td class=\"button-ok\">Karte</td>\n                    <td class=\"button-ok\">Summe</td>\n                </tr>\n                <tr>\n                    <td valign=\"top\">E-Boot</td>\n                    <td valign=\"top\">{{getCount('E-Boot')}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getCashBeforeTax('E-Boot'))}}<br/>{{pp.ppPrice(getCashTax('E-Boot'))}}<br/>{{pp.ppPrice(getCashAfterTax('E-Boot'))}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getCardBeforeTax('E-Boot'))}}<br/>{{pp.ppPrice(getCardTax('E-Boot'))}}<br/>{{pp.ppPrice(getCardAfterTax('E-Boot'))}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getSumBeforeTax('E-Boot'))}}<br/>{{pp.ppPrice(getSumTax('E-Boot'))}}<br/>{{pp.ppPrice(getSumAfterTax('E-Boot'))}}</td>\n                </tr>\n                <tr>\n                    <td valign=\"top\">Tretboot klein</td>\n                    <td valign=\"top\">{{getCount('Tretboot klein')}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getCashBeforeTax('Tretboot klein'))}}<br/>{{pp.ppPrice(getCashTax('Tretboot klein'))}}<br/>{{pp.ppPrice(getCashAfterTax('Tretboot klein'))}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getCardBeforeTax('Tretboot klein'))}}<br/>{{pp.ppPrice(getCardTax('Tretboot klein'))}}<br/>{{pp.ppPrice(getCardAfterTax('Tretboot klein'))}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getSumBeforeTax('Tretboot klein'))}}<br/>{{pp.ppPrice(getSumTax('Tretboot klein'))}}<br/>{{pp.ppPrice(getSumAfterTax('Tretboot klein'))}}</td>\n                </tr>\n                <tr>\n                    <td valign=\"top\">Tretboot gro\u00DF</td>\n                    <td valign=\"top\">{{getCount('Tretboot gro\u00DF')}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getCashBeforeTax('Tretboot gro\u00DF'))}}<br/>{{pp.ppPrice(getCashTax('Tretboot gro\u00DF'))}}<br/>{{pp.ppPrice(getCashAfterTax('Tretboot gro\u00DF'))}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getCardBeforeTax('Tretboot gro\u00DF'))}}<br/>{{pp.ppPrice(getCardTax('Tretboot gro\u00DF'))}}<br/>{{pp.ppPrice(getCardAfterTax('Tretboot gro\u00DF'))}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getSumBeforeTax('Tretboot gro\u00DF'))}}<br/>{{pp.ppPrice(getSumTax('Tretboot gro\u00DF'))}}<br/>{{pp.ppPrice(getSumAfterTax('Tretboot gro\u00DF'))}}</td>\n                </tr>\n                <tr>\n                    <td valign=\"top\">Tretboot Rutsche</td>\n                    <td valign=\"top\">{{getCount('Tretboot Rutsche')}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getCashBeforeTax('Tretboot Rutsche'))}}<br/>{{pp.ppPrice(getCashTax('Tretboot Rutsche'))}}<br/>{{pp.ppPrice(getCashAfterTax('Tretboot Rutsche'))}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getCardBeforeTax('Tretboot Rutsche'))}}<br/>{{pp.ppPrice(getCardTax('Tretboot Rutsche'))}}<br/>{{pp.ppPrice(getCardAfterTax('Tretboot Rutsche'))}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getSumBeforeTax('Tretboot Rutsche'))}}<br/>{{pp.ppPrice(getSumTax('Tretboot Rutsche'))}}<br/>{{pp.ppPrice(getSumAfterTax('Tretboot Rutsche'))}}</td>\n                </tr>\n                <tr>\n                    <td valign=\"top\">Liegeboot</td>\n                    <td valign=\"top\">{{getCount('Liegeboot')}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getCashBeforeTax('Liegeboot'))}}<br/>{{pp.ppPrice(getCashTax('Liegeboot'))}}<br/>{{pp.ppPrice(getCashAfterTax('Liegeboot'))}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getCardBeforeTax('Liegeboot'))}}<br/>{{pp.ppPrice(getCardTax('Liegeboot'))}}<br/>{{pp.ppPrice(getCardAfterTax('Liegeboot'))}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getSumBeforeTax('Liegeboot'))}}<br/>{{pp.ppPrice(getSumTax('Liegeboot'))}}<br/>{{pp.ppPrice(getSumAfterTax('Liegeboot'))}}</td>\n                </tr>\n                <tr>\n                    <td valign=\"top\" class=\"button-action\">Summe</td>\n                    <td valign=\"top\" class=\"button-action\">{{getCountSum()}}</td>\n                    <td align=\"right\" valign=\"top\" class=\"button-action\">{{pp.ppPrice(getCashBeforeTax())}}<br/>{{pp.ppPrice(getCashTax())}}<br/>{{pp.ppPrice(getCashAfterTax())}}</td>\n                    <td align=\"right\" valign=\"top\" class=\"button-action\">{{pp.ppPrice(getCardBeforeTax())}}<br/>{{pp.ppPrice(getCardTax())}}<br/>{{pp.ppPrice(getCardAfterTax())}}</td>\n                    <td align=\"right\" valign=\"top\" class=\"button-cancel\">{{pp.ppPrice(getSumBeforeTax())}}<br/>{{pp.ppPrice(getSumTax())}}<br/>{{pp.ppPrice(getSumAfterTax())}}</td>\n                </tr>\n            </table>\n        </div>\n        <div class=\"modal-footer\">\n            <button class=\"buttonSmall button-action\" (click)=\"print($event)\">Drucken</button>\n            <button class=\"buttonSmall button-ok\" (click)=\"close($event)\">Schlie\u00DFen</button>\n        </div>",
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
