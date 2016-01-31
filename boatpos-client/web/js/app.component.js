System.register(['angular2/core', "./boat.service", "./config.service", 'angular2/http', "./boats.component", "./commitments.component", "./commitment.service", "./promotion.service", "./promotionsBefore.component", "./boatCount.component", "./info.component", "./info.service", "./menu.component", "./action.component", "./rental.service", "lib/angular2-modal"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, boat_service_1, config_service_1, http_1, boats_component_1, commitments_component_1, commitment_service_1, promotion_service_1, promotionsBefore_component_1, boatCount_component_1, info_component_1, info_service_1, menu_component_1, action_component_1, rental_service_1, angular2_modal_1;
    var AppComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (boat_service_1_1) {
                boat_service_1 = boat_service_1_1;
            },
            function (config_service_1_1) {
                config_service_1 = config_service_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (boats_component_1_1) {
                boats_component_1 = boats_component_1_1;
            },
            function (commitments_component_1_1) {
                commitments_component_1 = commitments_component_1_1;
            },
            function (commitment_service_1_1) {
                commitment_service_1 = commitment_service_1_1;
            },
            function (promotion_service_1_1) {
                promotion_service_1 = promotion_service_1_1;
            },
            function (promotionsBefore_component_1_1) {
                promotionsBefore_component_1 = promotionsBefore_component_1_1;
            },
            function (boatCount_component_1_1) {
                boatCount_component_1 = boatCount_component_1_1;
            },
            function (info_component_1_1) {
                info_component_1 = info_component_1_1;
            },
            function (info_service_1_1) {
                info_service_1 = info_service_1_1;
            },
            function (menu_component_1_1) {
                menu_component_1 = menu_component_1_1;
            },
            function (action_component_1_1) {
                action_component_1 = action_component_1_1;
            },
            function (rental_service_1_1) {
                rental_service_1 = rental_service_1_1;
            },
            function (angular2_modal_1_1) {
                angular2_modal_1 = angular2_modal_1_1;
            }],
        execute: function() {
            AppComponent = (function () {
                function AppComponent() {
                }
                AppComponent.prototype.ngOnInit = function () {
                    return undefined;
                };
                AppComponent = __decorate([
                    core_1.Component({
                        selector: 'my-app',
                        templateUrl: "app.component.html",
                        styleUrls: ["app.component.css"],
                        directives: [boats_component_1.BoatsComponent, commitments_component_1.CommitmentsComponent, promotionsBefore_component_1.PromotionsBeforeComponent, boatCount_component_1.BoatCountComponent, info_component_1.InfoComponent, menu_component_1.MenuComponent, action_component_1.ActionComponent],
                        providers: [boat_service_1.BoatService, commitment_service_1.CommitmentService, promotion_service_1.PromotionService, config_service_1.ConfigService, info_service_1.InfoService, rental_service_1.RentalService, http_1.HTTP_PROVIDERS, angular2_modal_1.Modal]
                    }), 
                    __metadata('design:paramtypes', [])
                ], AppComponent);
                return AppComponent;
            })();
            exports_1("AppComponent", AppComponent);
        }
    }
});
