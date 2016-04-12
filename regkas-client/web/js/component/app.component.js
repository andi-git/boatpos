System.register(["angular2/core", "./../service/config.service", "angular2/http", "./info.component", "./../service/info.service", "./menu.component", "lib/angular2-modal", "./../modalHandler", "./../prettyprinter", "./../printer", "./../service/mode.service", "./version.component", "../service/keybinding.service", "./sale/numbers.component", "../service/sale.service", "./sale/actions.component", "../service/product.service", "./sale/products.component"], function(exports_1, context_1) {
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
    var core_1, config_service_1, http_1, info_component_1, info_service_1, menu_component_1, angular2_modal_1, modalHandler_1, prettyprinter_1, printer_1, mode_service_1, version_component_1, keybinding_service_1, numbers_component_1, sale_service_1, actions_component_1, product_service_1, products_component_1;
    var AppComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (config_service_1_1) {
                config_service_1 = config_service_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
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
            function (angular2_modal_1_1) {
                angular2_modal_1 = angular2_modal_1_1;
            },
            function (modalHandler_1_1) {
                modalHandler_1 = modalHandler_1_1;
            },
            function (prettyprinter_1_1) {
                prettyprinter_1 = prettyprinter_1_1;
            },
            function (printer_1_1) {
                printer_1 = printer_1_1;
            },
            function (mode_service_1_1) {
                mode_service_1 = mode_service_1_1;
            },
            function (version_component_1_1) {
                version_component_1 = version_component_1_1;
            },
            function (keybinding_service_1_1) {
                keybinding_service_1 = keybinding_service_1_1;
            },
            function (numbers_component_1_1) {
                numbers_component_1 = numbers_component_1_1;
            },
            function (sale_service_1_1) {
                sale_service_1 = sale_service_1_1;
            },
            function (actions_component_1_1) {
                actions_component_1 = actions_component_1_1;
            },
            function (product_service_1_1) {
                product_service_1 = product_service_1_1;
            },
            function (products_component_1_1) {
                products_component_1 = products_component_1_1;
            }],
        execute: function() {
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
                        directives: [info_component_1.InfoComponent, menu_component_1.MenuComponent, version_component_1.VersionComponent, numbers_component_1.NumbersComponent, actions_component_1.ActionsComponent, products_component_1.ProductComponent],
                        providers: [info_service_1.InfoService, http_1.HTTP_PROVIDERS, angular2_modal_1.Modal, keybinding_service_1.KeyBindingService, modalHandler_1.ModalHandler, prettyprinter_1.PrettyPrinter, printer_1.Printer, mode_service_1.ModeService, sale_service_1.SaleService, product_service_1.ProductService, config_service_1.ConfigService]
                    }), 
                    __metadata('design:paramtypes', [mode_service_1.ModeService])
                ], AppComponent);
                return AppComponent;
            }());
            exports_1("AppComponent", AppComponent);
        }
    }
});
