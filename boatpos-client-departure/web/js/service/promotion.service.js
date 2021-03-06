System.register(["../model/promotion", "angular2/core", "angular2/http", "rxjs/add/operator/map", "./config.service"], function(exports_1, context_1) {
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
    var promotion_1, core_1, http_1, config_service_1;
    var PromotionService;
    return {
        setters:[
            function (promotion_1_1) {
                promotion_1 = promotion_1_1;
            },
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (_1) {},
            function (config_service_1_1) {
                config_service_1 = config_service_1_1;
            }],
        execute: function() {
            PromotionService = (function () {
                function PromotionService(http, configService) {
                    var _this = this;
                    this.http = http;
                    this.configService = configService;
                    // when configuration is finished, load and cache promotions
                    this.configService.isConfigured().subscribe(function (config) {
                        console.log("constructor of PromotionService");
                        _this.loadPromotionsBefore().subscribe(function (promotionsBefore) { return _this.promotionsBeforeCache = promotionsBefore; });
                        _this.loadPromotionHolliKnolli().subscribe(function (promotionHolliKnolli) { return _this.promotionHolliKnolliCache = promotionHolliKnolli; });
                    });
                }
                PromotionService.prototype.getPromotionsBefore = function () {
                    return this.promotionsBeforeCache;
                };
                PromotionService.prototype.loadPromotionsBefore = function () {
                    return this.http.get(this.configService.getBackendUrl() + 'rest/promotion/before/enabled', { headers: this.configService.getDefaultHeader() })
                        .map(function (res) { return res.json(); })
                        .map(function (promotions) {
                        var result = [];
                        if (promotions) {
                            promotions.forEach(function (promotion) {
                                result.push(new promotion_1.PromotionBefore(promotion.id, promotion.name, promotion.timeCredit, promotion.enabled, promotion.priority, promotion.keyBinding, promotion.pictureUrl, promotion.pictureUrlThumb));
                            });
                        }
                        return result;
                    });
                };
                PromotionService.prototype.loadPromotionHolliKnolli = function () {
                    return this.http.get(this.configService.getBackendUrl() + 'rest/promotion/after/name/HolliKnolli', { headers: this.configService.getDefaultHeader() })
                        .map(function (res) { return res.json(); })
                        .map(function (holliKnolli) {
                        return new promotion_1.PromotionAfter(holliKnolli.id, holliKnolli.name, holliKnolli.enabled, holliKnolli.priority, holliKnolli.keyBinding, holliKnolli.pictureUrl, holliKnolli.pictureUrlThumb);
                    });
                };
                PromotionService.prototype.getPromotionBeforeByKeyBinding = function (keyBinding) {
                    var promotionBefore = null;
                    this.getPromotionsBefore().forEach(function (p) {
                        if (p.keyBinding == keyBinding) {
                            promotionBefore = p;
                        }
                    });
                    return promotionBefore;
                };
                PromotionService.prototype.resetSelected = function () {
                    this.getPromotionsBefore().forEach(function (pb) {
                        pb.selected = false;
                    });
                };
                PromotionService.prototype.getSelectedPromotionsBefore = function () {
                    var selectedPromotionBefore;
                    this.getPromotionsBefore().forEach(function (promotionBefore) {
                        if (promotionBefore.selected === true) {
                            selectedPromotionBefore = promotionBefore;
                        }
                    });
                    return selectedPromotionBefore;
                };
                PromotionService.prototype.getHolliKnolli = function () {
                    return this.promotionHolliKnolliCache;
                };
                PromotionService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [http_1.Http, config_service_1.ConfigService])
                ], PromotionService);
                return PromotionService;
            }());
            exports_1("PromotionService", PromotionService);
        }
    }
});
