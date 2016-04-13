System.register(["angular2/core", "../../service/sale.service", "../../prettyprinter"], function(exports_1, context_1) {
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
    var core_1, sale_service_1, prettyprinter_1;
    var ReceiptComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (sale_service_1_1) {
                sale_service_1 = sale_service_1_1;
            },
            function (prettyprinter_1_1) {
                prettyprinter_1 = prettyprinter_1_1;
            }],
        execute: function() {
            ReceiptComponent = (function () {
                function ReceiptComponent(saleService, pp) {
                    this.saleService = saleService;
                    this.pp = pp;
                }
                ReceiptComponent.prototype.getReceiptElements = function () {
                    return this.saleService.getReceiptElements();
                };
                ReceiptComponent.prototype.ppPrice = function (price) {
                    return this.pp.ppPrice(price, "");
                };
                ReceiptComponent.prototype.printSum = function () {
                    return "Summe: " + this.pp.ppPrice(this.saleService.getSum());
                };
                ReceiptComponent.prototype.handlePG = function (s) {
                    return s.replace("PG: ", "");
                };
                ReceiptComponent = __decorate([
                    core_1.Component({
                        selector: 'receipt',
                        templateUrl: "html/component/sale/receipt.component.html",
                        styleUrls: ["css/component/sale/receipt.component.css"]
                    }), 
                    __metadata('design:paramtypes', [sale_service_1.SaleService, prettyprinter_1.PrettyPrinter])
                ], ReceiptComponent);
                return ReceiptComponent;
            }());
            exports_1("ReceiptComponent", ReceiptComponent);
        }
    }
});
