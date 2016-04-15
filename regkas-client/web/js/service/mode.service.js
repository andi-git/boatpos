System.register(['angular2/core', "./config.service"], function(exports_1, context_1) {
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
    var core_1, config_service_1;
    var ModeService, Mode;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (config_service_1_1) {
                config_service_1 = config_service_1_1;
            }],
        execute: function() {
            ModeService = (function () {
                // inject here the services to have a constructor-call on these components
                function ModeService(configService) {
                    var _this = this;
                    this.configService = configService;
                    this.defaultMode = Mode.REGKAS;
                    this.modeChangeEvent = new core_1.EventEmitter();
                    this.configService.isConfigured().subscribe(function (config) {
                        console.log("constructor of ModeService");
                        _this.event().emit(_this.defaultMode);
                    });
                }
                ModeService.prototype.event = function () {
                    return this.modeChangeEvent;
                };
                ModeService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [config_service_1.ConfigService])
                ], ModeService);
                return ModeService;
            }());
            exports_1("ModeService", ModeService);
            (function (Mode) {
                Mode[Mode["REGKAS"] = 0] = "REGKAS";
                Mode[Mode["STATS"] = 1] = "STATS";
            })(Mode || (Mode = {}));
            exports_1("Mode", Mode);
        }
    }
});
