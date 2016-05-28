System.register(["angular2/core", "angular2/common", "lib/angular2-modal", "angular2/src/facade/lang"], function(exports_1, context_1) {
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
    var core_1, common_1, angular2_modal_1, lang_1;
    var ModalIncomeContext, ModalIncome, IncomeProductGroupResult, IncomeTaxElementResult;
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
            }());
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
                    this.journalService.income(this.year, this.month, this.day).subscribe(function (income) {
                        console.log("report");
                        _this.income = income;
                        _this.loaded = true;
                    });
                }
                ModalIncome.prototype.ppDate = function () {
                    if (lang_1.isPresent(this.income)) {
                        return this.pp.printDate(this.income.start) + " - " + this.pp.printDate(this.income.end);
                    }
                };
                ModalIncome.prototype.getIncomeProductGroup = function (name) {
                    var result = new IncomeProductGroupResult();
                    if (lang_1.isPresent(this.income)) {
                        this.income.incomeProductGroups.forEach(function (productGroup) {
                            if (productGroup.name == name) {
                                result = new IncomeProductGroupResult(productGroup.name, productGroup.income, productGroup.taxPercent);
                            }
                        });
                    }
                    return result;
                };
                ModalIncome.prototype.getTaxElement = function (taxPercent) {
                    var result = new IncomeTaxElementResult();
                    if (lang_1.isPresent(this.income)) {
                        this.income.taxElements.forEach(function (taxElement) {
                            if (taxElement.taxPercent == taxPercent) {
                                result = new IncomeTaxElementResult(taxElement.taxPercent, taxElement.price, taxElement.priceBeforeTax, taxElement.priceTax);
                            }
                        });
                    }
                    return result;
                };
                ModalIncome.prototype.getSumTaxElement = function () {
                    var result = new IncomeTaxElementResult();
                    var sumPrice = 0;
                    var sumPriceBeforeTax = 0;
                    var sumPriceTax = 0;
                    if (lang_1.isPresent(this.income)) {
                        this.income.taxElements.forEach(function (taxElement) {
                            sumPrice = sumPrice + taxElement.price;
                            sumPriceBeforeTax = sumPriceBeforeTax + taxElement.priceBeforeTax;
                            sumPriceTax = sumPriceTax + taxElement.priceTax;
                        });
                    }
                    return new IncomeTaxElementResult("Summe", sumPrice, sumPriceBeforeTax, sumPriceTax);
                };
                ModalIncome.prototype.close = function ($event) {
                    $event.stopPropagation();
                    //noinspection TypeScriptUnresolvedFunction
                    this.dialog.close(true);
                };
                ModalIncome.prototype.print = function ($event) {
                    this.printer.printIncome(this.income, this.config.getPrinterIp());
                };
                ModalIncome.prototype.cancel = function () {
                    //noinspection TypeScriptUnresolvedFunction
                    this.dialog.dismiss();
                };
                ModalIncome = __decorate([
                    core_1.Component({
                        selector: 'modal-content',
                        directives: [common_1.NgIf, common_1.NgFor],
                        template: "<div class=\"modal-header\">\n        <h2 class=\"header header-main\">Einnahmen: {{ppDate()}}</h2>\n        </div>\n        <div class=\"modal-body\">\n            <table class=\"table-no-style\">\n                <tr>\n                    <td class=\"button-ok\">Produktgruppe</td>\n                    <td class=\"button-ok\">Einnahmen</td>\n                    <td class=\"button-ok\">Steuer</td>\n                </tr>\n                <tr>\n                    <td valign=\"top\">Eskimoeis</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getIncomeProductGroup('Eskimoeis').income)}}</td>\n                    <td align=\"right\" valign=\"top\">{{getIncomeProductGroup('Eskimoeis').taxPercent}}%</td>\n                </tr>\n                <tr>\n                    <td valign=\"top\">alkoholfreies Getr\u00E4nk</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getIncomeProductGroup('alkoholfreies Getr\u00E4nk').income)}}</td>\n                    <td align=\"right\" valign=\"top\">{{getIncomeProductGroup('alkoholfreies Getr\u00E4nk').taxPercent}}%</td>\n                </tr>\n                <tr>\n                    <td valign=\"top\">Kaffee</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getIncomeProductGroup('Kaffee').income)}}</td>\n                    <td align=\"right\" valign=\"top\">{{getIncomeProductGroup('Kaffee').taxPercent}}%</td>\n                </tr>\n                <tr>\n                    <td valign=\"top\">Bier</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getIncomeProductGroup('Bier').income)}}</td>\n                    <td align=\"right\" valign=\"top\">{{getIncomeProductGroup('Bier').taxPercent}}%</td>\n                </tr>\n                <tr>\n                    <td valign=\"top\">Wein</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getIncomeProductGroup('Wein').income)}}</td>\n                    <td align=\"right\" valign=\"top\">{{getIncomeProductGroup('Wein').taxPercent}}%</td>\n                </tr>\n                <tr>\n                    <td valign=\"top\">Warme Speisen</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getIncomeProductGroup('Warme Speisen').income)}}</td>\n                    <td align=\"right\" valign=\"top\">{{getIncomeProductGroup('Warme Speisen').taxPercent}}%</td>\n                </tr>\n                <tr>\n                    <td valign=\"top\">Belegtes Geb\u00E4ck</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getIncomeProductGroup('Belegtes Geb\u00E4ck').income)}}</td>\n                    <td align=\"right\" valign=\"top\">{{getIncomeProductGroup('Belegtes Geb\u00E4ck').taxPercent}}%</td>\n                </tr>\n                <tr>\n                    <td valign=\"top\">Kuchen</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getIncomeProductGroup('Kuchen').income)}}</td>\n                    <td align=\"right\" valign=\"top\">{{getIncomeProductGroup('Kuchen').taxPercent}}%</td>\n                </tr>\n                <tr>\n                    <td valign=\"top\">Snack</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getIncomeProductGroup('Snack').income)}}</td>\n                    <td align=\"right\" valign=\"top\">{{getIncomeProductGroup('Snack').taxPercent}}%</td>\n                </tr>\n                <tr>\n                    <td valign=\"top\">Eismischgetr\u00E4nk</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getIncomeProductGroup('Eismischgetr\u00E4nk').income)}}</td>\n                    <td align=\"right\" valign=\"top\">{{getIncomeProductGroup('Eismischgetr\u00E4nk').taxPercent}}%</td>\n                </tr>\n            </table>\n            <table class=\"table-no-style\">\n                <tr>\n                    <td class=\"button-ok\">Steuersatz</td>\n                    <td class=\"button-ok\">Netto</td>\n                    <td class=\"button-ok\">Steuer</td>\n                    <td class=\"button-ok\">Brutto</td>\n                </tr>\n                <tr>\n                    <td valign=\"top\">10%</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getTaxElement(10).priceBeforeTax)}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getTaxElement(10).priceTax)}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getTaxElement(10).price)}}</td>\n                </tr>\n                <tr>\n                    <td valign=\"top\">20%</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getTaxElement(20).priceBeforeTax)}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getTaxElement(20).priceTax)}}</td>\n                    <td align=\"right\" valign=\"top\">{{pp.ppPrice(getTaxElement(20).price)}}</td>\n                </tr>\n                <tr>\n                    <td valign=\"top\" class=\"button-action\">Summe</td>\n                    <td align=\"right\" valign=\"top\" class=\"button-action\">{{pp.ppPrice(getSumTaxElement().priceBeforeTax)}}</td>\n                    <td align=\"right\" valign=\"top\" class=\"button-action\">{{pp.ppPrice(getSumTaxElement().priceTax)}}</td>\n                    <td align=\"right\" valign=\"top\" class=\"button-cancel\">{{pp.ppPrice(getSumTaxElement().price)}}</td>\n                </tr>\n\n            </table>\n        </div>\n        <div class=\"modal-footer\">\n            <button class=\"buttonSmall button-action\" (click)=\"print($event)\">Drucken</button>\n            <button class=\"buttonSmall button-ok\" (click)=\"close($event)\">Schlie\u00DFen</button>\n        </div>",
                    }), 
                    __metadata('design:paramtypes', [(typeof (_a = typeof angular2_modal_1.ModalDialogInstance !== 'undefined' && angular2_modal_1.ModalDialogInstance) === 'function' && _a) || Object, (typeof (_b = typeof angular2_modal_1.ICustomModal !== 'undefined' && angular2_modal_1.ICustomModal) === 'function' && _b) || Object])
                ], ModalIncome);
                return ModalIncome;
                var _a, _b;
            }());
            exports_1("ModalIncome", ModalIncome);
            IncomeProductGroupResult = (function () {
                function IncomeProductGroupResult(name, income, taxPercent) {
                    this.name = name;
                    this.income = income;
                    this.taxPercent = taxPercent;
                }
                return IncomeProductGroupResult;
            }());
            IncomeTaxElementResult = (function () {
                function IncomeTaxElementResult(name, price, priceBeforeTax, priceTax) {
                    this.name = name;
                    this.price = price;
                    this.priceBeforeTax = priceBeforeTax;
                    this.priceTax = priceTax;
                }
                return IncomeTaxElementResult;
            }());
        }
    }
});
