System.register(["angular2/core", "../../service/keybinding.service", "../../service/sale.service", "../../service/product.service"], function(exports_1, context_1) {
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
    var core_1, keybinding_service_1, sale_service_1, product_service_1;
    var ProductComponent;
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
            function (product_service_1_1) {
                product_service_1 = product_service_1_1;
            }],
        execute: function() {
            ProductComponent = (function () {
                function ProductComponent(productService, saleService, keyBinding) {
                    this.productService = productService;
                    this.saleService = saleService;
                    this.keyBinding = keyBinding;
                }
                ProductComponent.prototype.getProductGroups = function () {
                    var productGroups = this.productService.getProductGroups();
                    return productGroups;
                };
                ProductComponent.prototype.getGenericProduct = function (productGroup) {
                    for (var i = 0; i < productGroup.products.length; i++) {
                        if (productGroup.products[i].generic === true) {
                            return productGroup.products[i];
                        }
                    }
                    return null;
                };
                ProductComponent.prototype.chooseProduct = function (product) {
                    this.saleService.chooseProduct(product);
                };
                ProductComponent.prototype.handlePG = function (s) {
                    return s.replace("PG: ", "");
                };
                ProductComponent = __decorate([
                    core_1.Component({
                        selector: 'products',
                        templateUrl: "html/component/sale/products.component.html",
                        styleUrls: ["css/component/sale/products.component.css"]
                    }), 
                    __metadata('design:paramtypes', [product_service_1.ProductService, sale_service_1.SaleService, keybinding_service_1.KeyBindingService])
                ], ProductComponent);
                return ProductComponent;
            }());
            exports_1("ProductComponent", ProductComponent);
        }
    }
});
