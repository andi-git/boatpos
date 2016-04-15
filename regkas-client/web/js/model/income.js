System.register(["angular2/src/facade/lang"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var lang_1;
    var Income, IncomeProductGroup;
    return {
        setters:[
            function (lang_1_1) {
                lang_1 = lang_1_1;
            }],
        execute: function() {
            Income = (function () {
                function Income(start, end, totalIncome, incomeProductGroups) {
                    var _this = this;
                    this.incomeProductGroups = [];
                    this.start = start;
                    this.end = end;
                    this.totalIncome = totalIncome;
                    if (lang_1.isPresent(incomeProductGroups)) {
                        // this.products.push(products);
                        incomeProductGroups.forEach(function (p) { return _this.incomeProductGroups.push(p); });
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
        }
    }
});
