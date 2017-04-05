System.register(["angular2/core", "./../service/boat.service", "./../service/config.service", "angular2/http", "./rental/boats.component", "./rental/commitments.component", "./../service/commitment.service", "./../service/promotion.service", "./rental/promotionsBefore.component", "./rental/boatCount.component", "./info.component", "./../service/info.service", "./menu.component", "./rental/action.component", "./../service/rental.service", "lib/angular2-modal", "./../service/keybinding.service", "./../modalHandler", "./../prettyprinter", "./rental/statistik.component", "./../printer", "./../service/journal.service", "./../service/mode.service", "./rentals/rentals.component", "./stats/stats.component", "./version.component", "./config/config.component", "../service/error.service", "./signatureDevice.component"], function(exports_1, context_1) {
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
    var core_1, boat_service_1, config_service_1, http_1, boats_component_1, commitments_component_1, commitment_service_1, promotion_service_1, promotionsBefore_component_1, boatCount_component_1, info_component_1, info_service_1, menu_component_1, action_component_1, rental_service_1, angular2_modal_1, keybinding_service_1, modalHandler_1, prettyprinter_1, statistik_component_1, printer_1, journal_service_1, mode_service_1, rentals_component_1, stats_component_1, version_component_1, config_component_1, error_service_1, signatureDevice_component_1;
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
            },
            function (keybinding_service_1_1) {
                keybinding_service_1 = keybinding_service_1_1;
            },
            function (modalHandler_1_1) {
                modalHandler_1 = modalHandler_1_1;
            },
            function (prettyprinter_1_1) {
                prettyprinter_1 = prettyprinter_1_1;
            },
            function (statistik_component_1_1) {
                statistik_component_1 = statistik_component_1_1;
            },
            function (printer_1_1) {
                printer_1 = printer_1_1;
            },
            function (journal_service_1_1) {
                journal_service_1 = journal_service_1_1;
            },
            function (mode_service_1_1) {
                mode_service_1 = mode_service_1_1;
            },
            function (rentals_component_1_1) {
                rentals_component_1 = rentals_component_1_1;
            },
            function (stats_component_1_1) {
                stats_component_1 = stats_component_1_1;
            },
            function (version_component_1_1) {
                version_component_1 = version_component_1_1;
            },
            function (config_component_1_1) {
                config_component_1 = config_component_1_1;
            },
            function (error_service_1_1) {
                error_service_1 = error_service_1_1;
            },
            function (signatureDevice_component_1_1) {
                signatureDevice_component_1 = signatureDevice_component_1_1;
            }],
        execute: function() {
            core_1.enableProdMode();
            AppComponent = (function () {
                function AppComponent(modeService) {
                    var _this = this;
                    this.modeService = modeService;
                    this.modeService.event().subscribe(function (mode) {
                        console.log("mode-change - AppComponent: " + mode_service_1.Mode[mode]);
                        _this.mode = mode_service_1.Mode[mode];
                    });
                }
                AppComponent.prototype.ngOnInit = function () {
                    return undefined;
                };
                AppComponent = __decorate([
                    core_1.Component({
                        selector: 'my-app',
                        templateUrl: "html/component/app.component.html",
                        styleUrls: ["css/component/app.component.css"],
                        directives: [boats_component_1.BoatsComponent, commitments_component_1.CommitmentsComponent, promotionsBefore_component_1.PromotionsBeforeComponent, boatCount_component_1.BoatCountComponent, info_component_1.InfoComponent, menu_component_1.MenuComponent, action_component_1.ActionComponent, statistik_component_1.StatistikComponent, rentals_component_1.RentalsComponent, stats_component_1.StatsComponent, version_component_1.VersionComponent, config_component_1.ConfigComponent, signatureDevice_component_1.SignatureDeviceComponent],
                        providers: [boat_service_1.BoatService, commitment_service_1.CommitmentService, promotion_service_1.PromotionService, config_service_1.ConfigService, info_service_1.InfoService, rental_service_1.RentalService, http_1.HTTP_PROVIDERS, angular2_modal_1.Modal, keybinding_service_1.KeyBindingService, modalHandler_1.ModalHandler, prettyprinter_1.PrettyPrinter, printer_1.Printer, journal_service_1.JournalService, mode_service_1.ModeService, error_service_1.ErrorService]
                    }), 
                    __metadata('design:paramtypes', [mode_service_1.ModeService])
                ], AppComponent);
                return AppComponent;
            }());
            exports_1("AppComponent", AppComponent);
        }
    }
});
