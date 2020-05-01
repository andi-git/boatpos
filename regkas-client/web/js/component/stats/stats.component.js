System.register(["angular2/core", "../../service/journal.service", "../../printer", "../../service/info.service", "../../service/config.service", "../../model/datePicker", "./modalIncome", "../../prettyprinter", "../../modalHandler", "../../service/sale.service", "./modalCheckPrint", "../../service/error.service"], function(exports_1, context_1) {
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
    var core_1, journal_service_1, printer_1, info_service_1, config_service_1, datePicker_1, modalIncome_1, prettyprinter_1, modalHandler_1, sale_service_1, modalCheckPrint_1, error_service_1;
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
            },
            function (sale_service_1_1) {
                sale_service_1 = sale_service_1_1;
            },
            function (modalCheckPrint_1_1) {
                modalCheckPrint_1 = modalCheckPrint_1_1;
            },
            function (error_service_1_1) {
                error_service_1 = error_service_1_1;
            }],
        execute: function() {
            StatsComponent = (function () {
                function StatsComponent(journalService, printer, infoService, errorService, config, info, pp, modalHandler, saleService) {
                    this.journalService = journalService;
                    this.printer = printer;
                    this.infoService = infoService;
                    this.errorService = errorService;
                    this.config = config;
                    this.info = info;
                    this.pp = pp;
                    this.modalHandler = modalHandler;
                    this.saleService = saleService;
                    this.datePickerIncome = new datePicker_1.DatePicker();
                    this.datePickerDep = new datePicker_1.DatePicker();
                    this.startbelegMustBePrinted = false;
                    console.log("constructor of StatsComponent");
                    this.checkIfStartBelegMustBePrinted();
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
                StatsComponent.prototype.depRKV2012 = function () {
                    this.info.event().emit("DatenErfassungsProtokoll RKV 2012 wird erstellt.");
                    window.open(this.config.addQueryParamCredentials(this.config.getBackendUrl() + "rest/journal/dep/latest?"));
                };
                StatsComponent.prototype.depRKSV2017 = function () {
                    this.info.event().emit("DatenErfassungsProtokoll RKSV 2017 wird erstellt.");
                    window.open(this.config.addQueryParamCredentials(this.config.getBackendUrl() + "rest/journal/dep/rksv/latest?"));
                };
                StatsComponent.prototype.printStartBeleg = function () {
                    this.saleService.startBeleg();
                    this.startbelegMustBePrinted = false;
                };
                StatsComponent.prototype.printSchlussBeleg = function () {
                    var _this = this;
                    this.modalHandler.open(modalCheckPrint_1.ModalCheckPrint, new modalCheckPrint_1.ModalCheckPrintContext('Schlussbeleg')).then(function (resultPromise) {
                        resultPromise.result.then(function (result) {
                            return resultPromise.result.then(function (result) {
                                _this.saleService.schlussBeleg();
                            }, function () {
                                _this.errorService.event().emit('Erstellen des Schluss-Belegs wurde abgebrochen!');
                            });
                        });
                    });
                };
                StatsComponent.prototype.checkIfStartBelegMustBePrinted = function () {
                    var _this = this;
                    this.saleService.checkIfStarbelegMustBePrinted().subscribe(function (check) {
                        _this.startbelegMustBePrinted = check;
                    });
                };
                StatsComponent.prototype.printNullBeleg = function () {
                    this.saleService.nullBeleg();
                };
                StatsComponent.prototype.printTagesBeleg = function () {
                    var _this = this;
                    this.modalHandler.open(modalCheckPrint_1.ModalCheckPrint, new modalCheckPrint_1.ModalCheckPrintContext('Tagesbeleg')).then(function (resultPromise) {
                        resultPromise.result.then(function (result) {
                            return resultPromise.result.then(function (result) {
                                _this.saleService.tagesBeleg();
                            }, function () {
                                _this.errorService.event().emit('Erstellen des Tages-Belegs wurde abgebrochen!');
                            });
                        });
                    });
                };
                StatsComponent.prototype.printMonatsBeleg = function () {
                    var _this = this;
                    this.modalHandler.open(modalCheckPrint_1.ModalCheckPrint, new modalCheckPrint_1.ModalCheckPrintContext('Monatsbeleg')).then(function (resultPromise) {
                        resultPromise.result.then(function (result) {
                            return resultPromise.result.then(function (result) {
                                _this.saleService.monatsBeleg();
                            }, function () {
                                _this.errorService.event().emit('Erstellen des Monats-Belegs wurde abgebrochen!');
                            });
                        });
                    });
                };
                StatsComponent.prototype.printJahresBeleg = function () {
                    var _this = this;
                    this.modalHandler.open(modalCheckPrint_1.ModalCheckPrint, new modalCheckPrint_1.ModalCheckPrintContext('Jahresbeleg')).then(function (resultPromise) {
                        resultPromise.result.then(function (result) {
                            return resultPromise.result.then(function (result) {
                                _this.saleService.jahresBeleg();
                            }, function () {
                                _this.errorService.event().emit('Erstellen des Jahres-Belegs wurde abgebrochen');
                            });
                        });
                    });
                };
                StatsComponent.prototype.printReceipt = function () {
                    if (this.receiptId != null && this.receiptId != "") {
                        this.saleService.printReceipt(this.receiptId);
                        this.infoService.event().emit("Rechnung für " + this.receiptId + " wurde gedruckt.");
                        this.deleteReceiptId();
                    }
                };
                StatsComponent.prototype.deleteReceiptId = function () {
                    this.receiptId = null;
                    this.infoService.event().emit("Aktion abgebrochen, Rechnungsnummer zurückgesetzt.");
                };
                StatsComponent = __decorate([
                    core_1.Component({
                        selector: 'stats',
                        templateUrl: "html/component/stats/stats.component.html",
                        styleUrls: ["css/component/stats/stats.component.css"]
                    }), 
                    __metadata('design:paramtypes', [journal_service_1.JournalService, printer_1.Printer, info_service_1.InfoService, error_service_1.ErrorService, config_service_1.ConfigService, info_service_1.InfoService, prettyprinter_1.PrettyPrinter, modalHandler_1.ModalHandler, sale_service_1.SaleService])
                ], StatsComponent);
                return StatsComponent;
            }());
            exports_1("StatsComponent", StatsComponent);
        }
    }
});
