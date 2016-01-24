System.register(['angular2/platform/browser', './app.component', "./config.service", "angular2/core"], function(exports_1) {
    var browser_1, app_component_1, config_service_1, core_1;
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
            }],
        execute: function() {
            browser_1.bootstrap(app_component_1.AppComponent, [config_service_1.ConfigService,
                core_1.provide(Mousetrap, {
                    useFactory: function () { return new Mousetrap(); }
                })
            ]);
        }
    }
});
