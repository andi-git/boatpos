System.register(['angular2/core', "../service/config.service"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, config_service_1;
    var VersionComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (config_service_1_1) {
                config_service_1 = config_service_1_1;
            }],
        execute: function() {
            VersionComponent = (function () {
                function VersionComponent(configService) {
                    var _this = this;
                    this.configService = configService;
                    // when configuration is finished, load and cache boats
                    this.configService.isConfigured().subscribe(function (config) {
                        _this.backend = _this.configService.getBackendUrl();
                        _this.printer = _this.configService.getPrinterUrl();
                    });
                }
                VersionComponent = __decorate([
                    core_1.Component({
                        selector: 'version',
                        templateUrl: "../../../html/component/version.component.html",
                        styleUrls: ["../../../css/component/version.component.css"]
                    }), 
                    __metadata('design:paramtypes', [config_service_1.ConfigService])
                ], VersionComponent);
                return VersionComponent;
            })();
            exports_1("VersionComponent", VersionComponent);
        }
    }
});
