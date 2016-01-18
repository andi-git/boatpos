System.register(['angular2/core', "./promotionBefore.service", "./config.service"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, promotionBefore_service_1, config_service_1;
    var PromotionsBeforeComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (promotionBefore_service_1_1) {
                promotionBefore_service_1 = promotionBefore_service_1_1;
            },
            function (config_service_1_1) {
                config_service_1 = config_service_1_1;
            }],
        execute: function() {
            PromotionsBeforeComponent = (function () {
                function PromotionsBeforeComponent(promotionBeforeService, configService) {
                    this.promotionBeforeService = promotionBeforeService;
                    this.configService = configService;
                }
                PromotionsBeforeComponent.prototype.getBoats = function () {
                    var _this = this;
                    this.promotionBeforeService.getPromotionsBefore().subscribe(function (promotionsBefore) { return _this.promotionsBefore = promotionsBefore; });
                };
                PromotionsBeforeComponent.prototype.ngOnInit = function () {
                    var _this = this;
                    this.subscription = this.configService.isConfigured().subscribe(function (config) { return _this.getBoats(); });
                };
                PromotionsBeforeComponent.prototype.onSelect = function (promotionBefore) {
                    this.selectedPromotionBefore = promotionBefore;
                };
                PromotionsBeforeComponent = __decorate([
                    core_1.Component({
                        selector: 'promotionsBefore',
                        templateUrl: "promotionsBefore.component.html",
                        styleUrls: ["promotionsBefore.component.css"]
                    }), 
                    __metadata('design:paramtypes', [promotionBefore_service_1.PromotionBeforeService, config_service_1.ConfigService])
                ], PromotionsBeforeComponent);
                return PromotionsBeforeComponent;
            })();
            exports_1("PromotionsBeforeComponent", PromotionsBeforeComponent);
        }
    }
});
