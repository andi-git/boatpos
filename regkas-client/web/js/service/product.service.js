System.register(["angular2/core", "angular2/http", "rxjs/add/operator/map", "./config.service", "../model/product", "../model/productGroup", "angular2/src/facade/lang"], function(exports_1, context_1) {
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
    var core_1, http_1, config_service_1, product_1, productGroup_1, lang_1;
    var ProductService;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (_1) {},
            function (config_service_1_1) {
                config_service_1 = config_service_1_1;
            },
            function (product_1_1) {
                product_1 = product_1_1;
            },
            function (productGroup_1_1) {
                productGroup_1 = productGroup_1_1;
            },
            function (lang_1_1) {
                lang_1 = lang_1_1;
            }],
        execute: function() {
            ProductService = (function () {
                // constructors do dependency injection in Angular2
                function ProductService(http, configService) {
                    var _this = this;
                    this.http = http;
                    this.configService = configService;
                    if (this.configService.isAlreadyConfigured() === true) {
                        this.loadProductGroups().subscribe(function (productGroups) { return _this.productGroupsCache = productGroups; });
                    }
                    else {
                        this.configService.isConfigured().subscribe(function (config) {
                            _this.loadProductGroups().subscribe(function (productGroups) { return _this.productGroupsCache = productGroups; });
                        });
                    }
                }
                ProductService.prototype.loadProductGroups = function () {
                    // call the rest-service
                    return this.http.get(this.configService.getBackendUrl() + 'rest/productgroup', { headers: this.configService.getDefaultHeader() })
                        .map(function (res) { return res.json(); })
                        .map(function (productGroupBeans) {
                        var result = [];
                        if (productGroupBeans) {
                            productGroupBeans.forEach(function (productGroupBean) {
                                var products = [];
                                if (lang_1.isPresent(productGroupBean.products)) {
                                    productGroupBean.products.forEach(function (p) { return products.push(new product_1.Product(p.id, p.name, p.price, p.priority, p.pictureUrl, p.pictureUrlThumb, p.keyBinding, productGroupBean.name)); });
                                }
                                result.push(new productGroup_1.ProductGroup(productGroupBean.id, productGroupBean.name, productGroupBean.price, productGroupBean.priority, productGroupBean.pictureUrl, productGroupBean.pictureUrlThumb, productGroupBean.keyBinding, products));
                            });
                        }
                        return result;
                    });
                };
                ProductService.prototype.getProductGroups = function () {
                    return this.productGroupsCache;
                };
                ProductService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [http_1.Http, config_service_1.ConfigService])
                ], ProductService);
                return ProductService;
            }());
            exports_1("ProductService", ProductService);
        }
    }
});
