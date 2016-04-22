System.register(["angular2/core", "../../service/keybinding.service", "../../service/sale.service", "../../service/journal.service", "../../printer", "../../service/config.service"], function(exports_1, context_1) {
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
    var core_1, keybinding_service_1, sale_service_1, journal_service_1, printer_1, config_service_1;
    var ActionsComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (keybinding_service_1_1) {
                keybinding_service_1 = keybinding_service_1_1;
            },
            function (sale_service_1_1) {
                sale_service_1 = sale_service_1_1;
            },
            function (journal_service_1_1) {
                journal_service_1 = journal_service_1_1;
            },
            function (printer_1_1) {
                printer_1 = printer_1_1;
            },
            function (config_service_1_1) {
                config_service_1 = config_service_1_1;
            }],
        execute: function() {
            ActionsComponent = (function () {
                function ActionsComponent(saleService, keyBinding, journalService, printer, config) {
                    var _this = this;
                    this.saleService = saleService;
                    this.keyBinding = keyBinding;
                    this.journalService = journalService;
                    this.printer = printer;
                    this.config = config;
                    var map = {
                        '*': function () {
                            _this.bill();
                        },
                        '-': function () {
                            _this.cancelLastElement();
                        },
                        '_': function () {
                            _this.cancelAllElements();
                        },
                        '~': function () {
                            _this.journalService.incomeCurrentDay().subscribe(function (income) { return _this.printer.printIncome(income, _this.config.getPrinterIp()); });
                        }
                    };
                    this.keyBinding.addBindingForMain(map);
                }
                ActionsComponent.prototype.cancelLastElement = function () {
                    this.saleService.cancelLastElement();
                };
                ActionsComponent.prototype.cancelAllElements = function () {
                    this.saleService.cancelAllElements();
                };
                ActionsComponent.prototype.bill = function () {
                    this.saleService.bill();
                };
                ActionsComponent = __decorate([
                    core_1.Component({
                        selector: 'actions',
                        templateUrl: "html/component/sale/actions.component.html",
                        styleUrls: ["css/component/sale/actions.component.css"]
                    }), 
                    __metadata('design:paramtypes', [sale_service_1.SaleService, keybinding_service_1.KeyBindingService, journal_service_1.JournalService, printer_1.Printer, config_service_1.ConfigService])
                ], ActionsComponent);
                return ActionsComponent;
            }());
            exports_1("ActionsComponent", ActionsComponent);
        }
    }
});
