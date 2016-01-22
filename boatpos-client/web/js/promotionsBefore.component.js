System.register(['angular2/core', "./promotionBefore.service", "./config.service", "./info.service"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, promotionBefore_service_1, config_service_1, info_service_1;
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
            },
            function (info_service_1_1) {
                info_service_1 = info_service_1_1;
            }],
        execute: function() {
            PromotionsBeforeComponent = (function () {
                function PromotionsBeforeComponent(promotionBeforeService, configService, infoService) {
                    this.promotionBeforeService = promotionBeforeService;
                    this.configService = configService;
                    this.infoService = infoService;
                }
                PromotionsBeforeComponent.prototype.ngOnInit = function () {
                    var _this = this;
                    this.subscription = this.configService.isConfigured().subscribe(function (config) { return _this.promotionBeforeService.getPromotionsBefore().subscribe(function (promotionsBefore) { return _this.promotionsBefore = promotionsBefore; }); });
                };
                PromotionsBeforeComponent.prototype.onSelect = function (promotionBefore) {
                    if (promotionBefore.selected) {
                        this.promotionsBefore.forEach(function (pb) { return pb.selected = false; });
                        this.infoService.event().emit("Aktion '" + promotionBefore.name + "' wurde entfernt.");
                    }
                    else {
                        this.promotionsBefore.forEach(function (pb) { return pb.selected = false; });
                        promotionBefore.selected = true;
                        this.infoService.event().emit("Aktion '" + promotionBefore.name + "' wurde ausgewählt.");
                    }
                };
                PromotionsBeforeComponent = __decorate([
                    core_1.Component({
                        selector: 'promotionsBefore',
                        templateUrl: "promotionsBefore.component.html",
                        styleUrls: ["promotionsBefore.component.css"]
                    }), 
                    __metadata('design:paramtypes', [promotionBefore_service_1.PromotionBeforeService, config_service_1.ConfigService, info_service_1.InfoService])
                ], PromotionsBeforeComponent);
                return PromotionsBeforeComponent;
            })();
            exports_1("PromotionsBeforeComponent", PromotionsBeforeComponent);
        }
    }
});
