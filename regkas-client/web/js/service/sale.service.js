System.register(["angular2/core", "./info.service"], function(exports_1, context_1) {
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
    var core_1, info_service_1;
    var SaleService;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (info_service_1_1) {
                info_service_1 = info_service_1_1;
            }],
        execute: function() {
            SaleService = (function () {
                function SaleService(infoService) {
                    this.infoService = infoService;
                    this.receiptElements = [];
                }
                SaleService.prototype.getNumberInput = function () {
                    return this.numberInput;
                };
                SaleService.prototype.setNumberInput = function (numberInput) {
                    if ('<' === numberInput) {
                        this.numberInput.substr(0, this.numberInput.length - 1);
                    }
                    else {
                        this.numberInput = (this.numberInput == null ? "" : this.numberInput) + numberInput;
                    }
                    this.infoService.event().emit("Nummer eingegeben.");
                };
                SaleService.prototype.cancelLastElement = function () {
                    this.receiptElements.pop();
                    this.infoService.event().emit("Letztes Element gelöscht.");
                };
                SaleService.prototype.cancelAllElements = function () {
                    this.receiptElements = [];
                    this.infoService.event().emit("Alle Elemente gelöscht.");
                };
                SaleService.prototype.bill = function () {
                };
                SaleService.prototype.getReceiptElements = function () {
                    return this.receiptElements;
                };
                SaleService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [info_service_1.InfoService])
                ], SaleService);
                return SaleService;
            }());
            exports_1("SaleService", SaleService);
        }
    }
});
