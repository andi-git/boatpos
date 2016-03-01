System.register([], function(exports_1) {
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
            })();
            exports_1("JournalReport", JournalReport);
            JournalReportItem = (function () {
                function JournalReportItem(boatName, pricePaidBeforeCash, pricePaidBeforeCard, pricePaidAfterCash, pricePaidAfterCard, count) {
                    this.boatName = boatName;
                    this.pricePaidBeforeCash = pricePaidBeforeCash;
                    this.pricePaidBeforeCard = pricePaidBeforeCard;
                    this.pricePaidAfterCash = pricePaidAfterCash;
                    this.pricePaidAfterCard = pricePaidAfterCard;
                    this.count = count;
                }
                JournalReportItem.prototype.toString = function () {
                    return JSON.stringify(this);
                };
                return JournalReportItem;
            })();
            exports_1("JournalReportItem", JournalReportItem);
        }
    }
});
