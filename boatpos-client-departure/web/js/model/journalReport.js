System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var JournalReport, JournalReportItem;
    return {
        setters:[],
        execute: function() {
            JournalReport = (function () {
                function JournalReport() {
                    this.journalReportItems = [];
                }
                JournalReport.prototype.add = function (journalReportItem) {
                    this.journalReportItems.push(journalReportItem);
                };
                JournalReport.prototype.toString = function () {
                    return JSON.stringify(this);
                };
                return JournalReport;
            }());
            exports_1("JournalReport", JournalReport);
            JournalReportItem = (function () {
                function JournalReportItem(boatName, pricePaidBeforeCash, pricePaidBeforeCashBeforeTax, pricePaidBeforeCashTax, pricePaidBeforeCard, pricePaidBeforeCardBeforeTax, pricePaidBeforeCardTax, pricePaidAfterCash, pricePaidAfterCashBeforeTax, pricePaidAfterCashTax, pricePaidAfterCard, pricePaidAfterCardBeforeTax, pricePaidAfterCardTax, count) {
                    this.boatName = boatName;
                    this.pricePaidBeforeCash = pricePaidBeforeCash;
                    this.pricePaidBeforeCashBeforeTax = pricePaidBeforeCashBeforeTax;
                    this.pricePaidBeforeCashTax = pricePaidBeforeCashTax;
                    this.pricePaidBeforeCard = pricePaidBeforeCard;
                    this.pricePaidBeforeCardBeforeTax = pricePaidBeforeCardBeforeTax;
                    this.pricePaidBeforeCardTax = pricePaidBeforeCardTax;
                    this.pricePaidAfterCash = pricePaidAfterCash;
                    this.pricePaidAfterCashBeforeTax = pricePaidAfterCashBeforeTax;
                    this.pricePaidAfterCashTax = pricePaidAfterCashTax;
                    this.pricePaidAfterCard = pricePaidAfterCard;
                    this.pricePaidAfterCardBeforeTax = pricePaidAfterCardBeforeTax;
                    this.pricePaidAfterCardTax = pricePaidAfterCardTax;
                    this.count = count;
                }
                JournalReportItem.prototype.toString = function () {
                    return JSON.stringify(this);
                };
                return JournalReportItem;
            }());
            exports_1("JournalReportItem", JournalReportItem);
        }
    }
});
