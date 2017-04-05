System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var DatePicker;
    return {
        setters:[],
        execute: function() {
            DatePicker = (function () {
                function DatePicker() {
                    this.days = [];
                    this.months = [];
                    this.years = [];
                    for (var i = 0; i < 31; i++) {
                        this.days[i] = i + 1;
                    }
                    this.months.push("Jänner");
                    this.months.push("Februar");
                    this.months.push("März");
                    this.months.push("April");
                    this.months.push("Mai");
                    this.months.push("Juni");
                    this.months.push("Juli");
                    this.months.push("August");
                    this.months.push("September");
                    this.months.push("Oktober");
                    this.months.push("November");
                    this.months.push("Dezember");
                    for (var i = 0; i < 10; i++) {
                        this.years[i] = i + 2016;
                    }
                    this.reset();
                }
                DatePicker.prototype.dayChange = function (day) {
                    this.currentDay = day;
                };
                DatePicker.prototype.monthChange = function (month) {
                    this.currentMonth = month;
                };
                DatePicker.prototype.yearChange = function (year) {
                    this.currentYear = year;
                };
                DatePicker.prototype.reset = function () {
                    this.currentDay = new Date(Date.now()).getDate();
                    this.currentMonth = this.months[new Date(Date.now()).getMonth()];
                    this.currentYear = new Date(Date.now()).getFullYear();
                };
                DatePicker.prototype.convertMonth = function (month) {
                    for (var i = 0; i < this.months.length; i++) {
                        if (this.months[i] == month) {
                            return i + 1;
                        }
                    }
                    return 0;
                };
                DatePicker.prototype.getCurrentDay = function () {
                    return this.currentDay;
                };
                DatePicker.prototype.getCurrentMonthAsString = function () {
                    return this.currentMonth;
                };
                DatePicker.prototype.getCurrentMonthAsNumber = function () {
                    return this.convertMonth(this.currentMonth);
                };
                DatePicker.prototype.getCurrentYear = function () {
                    return this.currentYear;
                };
                DatePicker.prototype.getDays = function () {
                    return this.days;
                };
                DatePicker.prototype.getMonthsAsString = function () {
                    return this.months;
                };
                DatePicker.prototype.getYears = function () {
                    return this.years;
                };
                DatePicker.prototype.toString = function () {
                    return JSON.stringify(this);
                };
                DatePicker.prototype.setCurrentDay = function (day) {
                    this.currentDay = day;
                };
                DatePicker.prototype.setCurrentMonth = function (month) {
                    this.currentMonth = month;
                };
                DatePicker.prototype.setCurrentYear = function (year) {
                    this.currentYear = year;
                };
                return DatePicker;
            }());
            exports_1("DatePicker", DatePicker);
        }
    }
});
