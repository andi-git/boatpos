System.register(["angular2/core", "../../service/journal.service", "../../printer", "../../service/info.service", "../../service/config.service", "../../model/datePicker", "./modalIncome", "../../prettyprinter", "../../modalHandler"], function(exports_1, context_1) {
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
    var core_1, journal_service_1, printer_1, info_service_1, config_service_1, datePicker_1, modalIncome_1, prettyprinter_1, modalHandler_1;
    var StatsComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
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
            },
            function (datePicker_1_1) {
                datePicker_1 = datePicker_1_1;
            },
            function (modalIncome_1_1) {
                modalIncome_1 = modalIncome_1_1;
            },
            function (prettyprinter_1_1) {
                prettyprinter_1 = prettyprinter_1_1;
            },
            function (modalHandler_1_1) {
                modalHandler_1 = modalHandler_1_1;
            }],
        execute: function() {
            StatsComponent = (function () {
                function StatsComponent(journalService, printer, infoService, config, info, pp, modalHandler) {
                    this.journalService = journalService;
                    this.printer = printer;
                    this.infoService = infoService;
                    this.config = config;
                    this.info = info;
                    this.pp = pp;
                    this.modalHandler = modalHandler;
                    this.datePickerIncome = new datePicker_1.DatePicker();
                    this.datePickerDep = new datePicker_1.DatePicker();
                    console.log("constructor of StatsComponent");
                }
                StatsComponent.prototype.dayIncomeChange = function (day) {
                    this.datePickerIncome.setCurrentDay(day);
                };
                StatsComponent.prototype.monthIncomeChange = function (month) {
                    this.datePickerIncome.setCurrentMonth(month);
                };
                StatsComponent.prototype.yearIncomeChange = function (year) {
                    this.datePickerIncome.setCurrentYear(year);
                };
                StatsComponent.prototype.dayDepChange = function (day) {
                    this.datePickerDep.setCurrentDay(day);
                };
                StatsComponent.prototype.monthDepChange = function (month) {
                    this.datePickerDep.setCurrentMonth(month);
                };
                StatsComponent.prototype.yearDepChange = function (year) {
                    this.datePickerDep.setCurrentYear(year);
                };
                StatsComponent.prototype.incomeDay = function () {
                    this.info.event().emit("Einnahmen für " + this.datePickerIncome.getCurrentDay() + ". " + this.datePickerIncome.getCurrentMonthAsString() + " " + this.datePickerIncome.getCurrentYear() + " werden angezeigt.");
                    this.modalHandler.open(modalIncome_1.ModalIncome, new modalIncome_1.ModalIncomeContext(this.journalService, this.pp, this.printer, this.config, this.datePickerIncome.getCurrentYear(), this.datePickerIncome.getCurrentMonthAsNumber(), this.datePickerIncome.getCurrentDay())).then(function (resultPromise) {
                    });
                };
                StatsComponent.prototype.incomeMonth = function () {
                    this.info.event().emit("Einnahmen für " + this.datePickerIncome.getCurrentMonthAsString() + " " + this.datePickerIncome.getCurrentYear() + " werden angezeigt.");
                    this.modalHandler.open(modalIncome_1.ModalIncome, new modalIncome_1.ModalIncomeContext(this.journalService, this.pp, this.printer, this.config, this.datePickerIncome.getCurrentYear(), this.datePickerIncome.getCurrentMonthAsNumber())).then(function (resultPromise) {
                    });
                };
                StatsComponent.prototype.incomeYear = function () {
                    this.info.event().emit("Einnahmen für " + this.datePickerIncome.getCurrentYear() + " werden angezeigt.");
                    this.modalHandler.open(modalIncome_1.ModalIncome, new modalIncome_1.ModalIncomeContext(this.journalService, this.pp, this.printer, this.config, this.datePickerIncome.getCurrentYear())).then(function (resultPromise) {
                    });
                };
                StatsComponent.prototype.depDay = function () {
                    this.info.event().emit("DatenErfassungsProtokoll für " + this.datePickerDep.getCurrentDay() + ". " + this.datePickerDep.getCurrentMonthAsString() + " " + this.datePickerDep.getCurrentYear() + " wird erstellt.");
                    window.open(this.config.addQueryParamCredentials(this.config.getBackendUrl() + "rest/journal/dep/"
                        + this.datePickerDep.getCurrentYear() + "/"
                        + this.datePickerDep.getCurrentMonthAsNumber() + "/"
                        + this.datePickerDep.getCurrentDay() + "?"));
                };
                StatsComponent.prototype.depMonth = function () {
                    this.info.event().emit("DatenErfassungsProtokoll für " + this.datePickerDep.getCurrentMonthAsString() + " " + this.datePickerDep.getCurrentYear() + " wird erstellt.");
                    window.open(this.config.addQueryParamCredentials(this.config.getBackendUrl() + "rest/journal/dep/"
                        + this.datePickerDep.getCurrentYear() + "/"
                        + this.datePickerDep.getCurrentMonthAsNumber() + "?"));
                };
                StatsComponent.prototype.depYear = function () {
                    this.info.event().emit("DatenErfassungsProtokoll für " + this.datePickerDep.getCurrentYear() + " wird erstellt.");
                    window.open(this.config.addQueryParamCredentials(this.config.getBackendUrl() + "rest/journal/dep/"
                        + this.datePickerDep.getCurrentYear() + "?"));
                };
                StatsComponent = __decorate([
                    core_1.Component({
                        selector: 'stats',
                        templateUrl: "html/component/stats/stats.component.html",
                        styleUrls: ["css/component/stats/stats.component.css"]
                    }), 
                    __metadata('design:paramtypes', [journal_service_1.JournalService, printer_1.Printer, info_service_1.InfoService, config_service_1.ConfigService, info_service_1.InfoService, prettyprinter_1.PrettyPrinter, modalHandler_1.ModalHandler])
                ], StatsComponent);
                return StatsComponent;
            }());
            exports_1("StatsComponent", StatsComponent);
        }
    }
});
