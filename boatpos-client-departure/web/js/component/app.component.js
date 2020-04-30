System.register(["angular2/core", "./../service/boat.service", "./../service/config.service", "angular2/http", "./rental/boats.component", "./../service/commitment.service", "./../service/promotion.service", "./info.component", "./../service/info.service", "./../service/rental.service", "./../service/keybinding.service", "./../modalHandler", "./../prettyprinter", "./rental/statistik.component", "./../printer", "./../service/journal.service", "./version.component", "../service/error.service", "lib/angular2-modal"], function(exports_1, context_1) {
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
    var core_1, boat_service_1, config_service_1, http_1, boats_component_1, commitment_service_1, promotion_service_1, info_component_1, info_service_1, rental_service_1, keybinding_service_1, modalHandler_1, prettyprinter_1, statistik_component_1, printer_1, journal_service_1, version_component_1, error_service_1, angular2_modal_1;
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
            function (commitment_service_1_1) {
                commitment_service_1 = commitment_service_1_1;
            },
            function (promotion_service_1_1) {
                promotion_service_1 = promotion_service_1_1;
            },
            function (info_component_1_1) {
                info_component_1 = info_component_1_1;
            },
            function (info_service_1_1) {
                info_service_1 = info_service_1_1;
            },
            function (rental_service_1_1) {
                rental_service_1 = rental_service_1_1;
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
            function (version_component_1_1) {
                version_component_1 = version_component_1_1;
            },
            function (error_service_1_1) {
                error_service_1 = error_service_1_1;
            },
            function (angular2_modal_1_1) {
                angular2_modal_1 = angular2_modal_1_1;
            }],
        execute: function() {
            core_1.enableProdMode();
            AppComponent = (function () {
                function AppComponent() {
                }
                AppComponent.prototype.ngOnInit = function () {
                    return undefined;
                };
                AppComponent = __decorate([
                    core_1.Component({
                        selector: 'my-app',
                        templateUrl: "html/component/app.component.html",
                        styleUrls: ["css/component/app.component.css"],
                        directives: [boats_component_1.BoatsComponent, info_component_1.InfoComponent, statistik_component_1.StatistikComponent, version_component_1.VersionComponent],
                        providers: [boat_service_1.BoatService, commitment_service_1.CommitmentService, promotion_service_1.PromotionService, config_service_1.ConfigService, info_service_1.InfoService, rental_service_1.RentalService, http_1.HTTP_PROVIDERS, angular2_modal_1.Modal, keybinding_service_1.KeyBindingService, modalHandler_1.ModalHandler, prettyprinter_1.PrettyPrinter, printer_1.Printer, journal_service_1.JournalService, error_service_1.ErrorService]
                    }), 
                    __metadata('design:paramtypes', [])
                ], AppComponent);
                return AppComponent;
            }());
            exports_1("AppComponent", AppComponent);
        }
    }
});
