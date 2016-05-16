System.register(["angular2/core", "../../service/mode.service", "../../service/journal.service", "../../printer", "../../service/info.service", "../../service/config.service"], function(exports_1, context_1) {
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
    var core_1, mode_service_1, journal_service_1, printer_1, info_service_1, config_service_1;
    var StatsComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (mode_service_1_1) {
                mode_service_1 = mode_service_1_1;
            },
            function (journal_service_1_1) {
                journal_service_1 = journal_service_1_1;
            },
            function (printer_1_1) {
                printer_1 = printer_1_1;
            },
            function (info_service_1_1) {
                info_service_1 = info_service_1_1;
            },
            function (config_service_1_1) {
                config_service_1 = config_service_1_1;
            }],
        execute: function() {
            StatsComponent = (function () {
                function StatsComponent(modeService, journalService, printer, infoService, config) {
                    this.modeService = modeService;
                    this.journalService = journalService;
                    this.printer = printer;
                    this.infoService = infoService;
                    this.config = config;
                    this.days = [];
                    this.months = [];
                    this.years = [];
                    console.log("constructor of StatsComponent");
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
                    this.resetIncome();
                }
                StatsComponent.prototype.dayChange = function (day) {
                    this.currentDay = day;
                };
                StatsComponent.prototype.monthChange = function (month) {
                    this.currentMonth = month;
                };
                StatsComponent.prototype.yearChange = function (year) {
                    this.currentYear = year;
                };
                StatsComponent.prototype.incomeDay = function () {
                    var _this = this;
                    this.infoService.event().emit("Einnahmen werden gedruckt.");
                    this.journalService.income(this.currentYear, this.convertMonth(this.currentMonth), this.currentDay).subscribe(function (income) { return _this.printer.printIncome(income, _this.config.getPrinterIp()); });
                };
                StatsComponent.prototype.incomeMonth = function () {
                    var _this = this;
                    this.infoService.event().emit("Einnahmen werden gedruckt.");
                    this.journalService.income(this.currentYear, this.convertMonth(this.currentMonth)).subscribe(function (income) { return _this.printer.printIncome(income, _this.config.getPrinterIp()); });
                };
                StatsComponent.prototype.incomeYear = function () {
                    var _this = this;
                    this.infoService.event().emit("Einnahmen werden gedruckt.");
                    this.journalService.income(this.currentYear).subscribe(function (income) { return _this.printer.printIncome(income, _this.config.getPrinterIp()); });
                };
                StatsComponent.prototype.convertMonth = function (month) {
                    for (var i = 0; i < this.months.length; i++) {
                        if (this.months[i] == month) {
                            return i + 1;
                        }
                    }
                    return 0;
                };
                StatsComponent.prototype.resetIncome = function () {
                    this.currentDay = new Date(Date.now()).getDate();
                    this.currentMonth = this.months[new Date(Date.now()).getMonth()];
                    this.currentYear = new Date(Date.now()).getFullYear();
                };
                StatsComponent.prototype.depDay = function () {
                    window.open(this.config.addQueryParamCredentials(this.config.getBackendUrl() + "rest/journal/dep/"
                        + this.currentYear + "/"
                        + this.convertMonth(this.currentMonth) + "/"
                        + this.currentDay + "?"));
                };
                StatsComponent.prototype.depMonth = function () {
                    window.open(this.config.addQueryParamCredentials(this.config.getBackendUrl() + "rest/journal/dep/"
                        + this.currentYear + "/"
                        + this.convertMonth(this.currentMonth) + "?"));
                };
                StatsComponent.prototype.depYear = function () {
                    window.open(this.config.addQueryParamCredentials(this.config.getBackendUrl() + "rest/journal/dep/"
                        + this.currentYear + "?"));
                };
                StatsComponent.prototype.resetDep = function () {
                    this.currentDay = new Date(Date.now()).getDate();
                    this.currentMonth = this.months[new Date(Date.now()).getMonth()];
                    this.currentYear = new Date(Date.now()).getFullYear();
                };
                StatsComponent = __decorate([
                    core_1.Component({
                        selector: 'stats',
                        templateUrl: "html/component/stats/stats.component.html",
                        styleUrls: ["css/component/stats/stats.component.css"]
                    }), 
                    __metadata('design:paramtypes', [mode_service_1.ModeService, journal_service_1.JournalService, printer_1.Printer, info_service_1.InfoService, config_service_1.ConfigService])
                ], StatsComponent);
                return StatsComponent;
            }());
            exports_1("StatsComponent", StatsComponent);
        }
    }
});
