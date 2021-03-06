System.register(['angular2/platform/browser', './component/app.component', "./service/config.service", "angular2/core", "lib/angular2-modal"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var browser_1, app_component_1, config_service_1, core_1, angular2_modal_1;
    return {
        setters:[
            function (browser_1_1) {
                browser_1 = browser_1_1;
            },
            function (app_component_1_1) {
                app_component_1 = app_component_1_1;
            },
            function (config_service_1_1) {
                config_service_1 = config_service_1_1;
            },
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (angular2_modal_1_1) {
                angular2_modal_1 = angular2_modal_1_1;
            }],
        execute: function() {
            browser_1.bootstrap(app_component_1.AppComponent, [config_service_1.ConfigService,
                core_1.provide(Mousetrap, {
                    useFactory: function () { return new Mousetrap(); }
                }),
                core_1.provide(angular2_modal_1.ModalConfig, {
                    useValue: new angular2_modal_1.ModalConfig('lg', true, 81)
                })
            ]);
        }
    }
});
