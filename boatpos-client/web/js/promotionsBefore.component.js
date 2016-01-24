System.register(['angular2/core', "./promotion.service", "./info.service", "angular2/core"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, promotion_service_1, info_service_1, core_2;
    var PromotionsBeforeComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (promotion_service_1_1) {
                promotion_service_1 = promotion_service_1_1;
            },
            function (info_service_1_1) {
                info_service_1 = info_service_1_1;
            },
            function (core_2_1) {
                core_2 = core_2_1;
            }],
        execute: function() {
            PromotionsBeforeComponent = (function () {
                function PromotionsBeforeComponent(promotionService, infoService, zone) {
                    this.promotionService = promotionService;
                    this.infoService = infoService;
                    this.zone = zone;
                }
                PromotionsBeforeComponent.prototype.ngOnInit = function () {
                    var _this = this;
                    Mousetrap.bind(['k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't'], function (e) {
                        _this.zone.run(function () {
                            var promotionBefore = _this.promotionService.getPromotionBeforeByKeyBinding(String.fromCharCode(e.charCode));
                            if (promotionBefore != null) {
                                _this.click(promotionBefore);
                            }
                        });
                    });
                };
                PromotionsBeforeComponent.prototype.getPromotionsBefore = function () {
                    return this.promotionService.getPromotionsBefore();
                };
                PromotionsBeforeComponent.prototype.click = function (promotionBefore) {
                    if (promotionBefore.selected) {
                        this.promotionService.resetSelected();
                        this.infoService.event().emit("Aktion '" + promotionBefore.name + "' wurde entfernt.");
                    }
                    else {
                        this.promotionService.resetSelected();
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
                    __metadata('design:paramtypes', [promotion_service_1.PromotionService, info_service_1.InfoService, core_2.NgZone])
                ], PromotionsBeforeComponent);
                return PromotionsBeforeComponent;
            })();
            exports_1("PromotionsBeforeComponent", PromotionsBeforeComponent);
        }
    }
});
