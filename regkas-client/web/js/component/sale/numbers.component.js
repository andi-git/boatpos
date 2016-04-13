System.register(["angular2/core", "../../service/keybinding.service", "../../service/sale.service"], function(exports_1, context_1) {
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
    var core_1, keybinding_service_1, sale_service_1;
    var NumbersComponent;
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
            }],
        execute: function() {
            NumbersComponent = (function () {
                function NumbersComponent(saleService, keyBinding) {
                    var _this = this;
                    this.saleService = saleService;
                    this.keyBinding = keyBinding;
                    var map = {
                        '.': function () {
                            _this.numberClick('.');
                        },
                        '<': function () {
                            _this.numberClick('<');
                        }
                    };
                    for (var i = 0; i <= 9; i++) {
                        map[i] = function (e) {
                            _this.numberClick(String.fromCharCode(e.charCode));
                        };
                    }
                    this.keyBinding.addBindingForMain(map);
                }
                NumbersComponent.prototype.numberClick = function (s) {
                    this.saleService.setNumberInput(s);
                };
                NumbersComponent.prototype.getNumberInput = function () {
                    return this.saleService.getNumberInput();
                };
                NumbersComponent.prototype.deleteNumberInput = function () {
                    this.saleService.deleteNumberInput();
                };
                NumbersComponent = __decorate([
                    core_1.Component({
                        selector: 'numbers',
                        templateUrl: "html/component/sale/numbers.component.html",
                        styleUrls: ["css/component/sale/numbers.component.css"]
                    }), 
                    __metadata('design:paramtypes', [sale_service_1.SaleService, keybinding_service_1.KeyBindingService])
                ], NumbersComponent);
                return NumbersComponent;
            }());
            exports_1("NumbersComponent", NumbersComponent);
        }
    }
});
