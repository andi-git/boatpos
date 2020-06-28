System.register(["angular2/src/facade/lang"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var lang_1;
    var Income, IncomeProductGroup, TaxElement;
    return {
        setters:[
            function (lang_1_1) {
                lang_1 = lang_1_1;
            }],
        execute: function() {
            Income = (function () {
                function Income(start, end, totalIncome, paymentCash, paymentCard, incomeProductGroups, taxElements) {
                    var _this = this;
                    this.incomeProductGroups = [];
                    this.taxElements = [];
                    this.start = start;
                    this.end = end;
                    this.totalIncome = totalIncome;
                    this.paymentCash = paymentCash;
                    this.paymentCard = paymentCard;
                    if (lang_1.isPresent(incomeProductGroups)) {
                        incomeProductGroups.forEach(function (p) { return _this.incomeProductGroups.push(p); });
                    }
                    if (lang_1.isPresent(taxElements)) {
                        taxElements.forEach(function (t) { return _this.taxElements.push(t); });
                    }
                }
                Income.prototype.toString = function () {
                    return JSON.stringify(this);
                };
                return Income;
            }());
            exports_1("Income", Income);
            IncomeProductGroup = (function () {
                function IncomeProductGroup(name, income, taxPercent, priority) {
                    this.name = name;
                    this.income = income;
                    this.taxPercent = taxPercent;
                    this.priority = priority;
                }
                IncomeProductGroup.prototype.toString = function () {
                    return JSON.stringify(this);
                };
                return IncomeProductGroup;
            }());
            exports_1("IncomeProductGroup", IncomeProductGroup);
            TaxElement = (function () {
                function TaxElement(taxPercent, priority, price, priceBeforeTax, priceTax) {
                    this.taxPercent = taxPercent;
                    this.priority = priority;
                    this.price = price;
                    this.priceBeforeTax = priceBeforeTax;
                    this.priceTax = priceTax;
                }
                TaxElement.prototype.toString = function () {
                    return JSON.stringify(this);
                };
                return TaxElement;
            }());
            exports_1("TaxElement", TaxElement);
        }
    }
});
