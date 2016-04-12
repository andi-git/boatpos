System.register(['angular2/core', 'angular2/http', 'rxjs/add/operator/map', 'rxjs/add/operator/toPromise', "angular2/src/facade/lang"], function(exports_1, context_1) {
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
    var core_1, http_1, lang_1;
    var ConfigService;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (_1) {},
            function (_2) {},
            function (lang_1_1) {
                lang_1 = lang_1_1;
            }],
        execute: function() {
            ConfigService = (function () {
                function ConfigService(http) {
                    var _this = this;
                    this.http = http;
                    this.configured = new core_1.EventEmitter();
                    // load the config and fire an event when the config is loaded
                    console.log("load config");
                    this.http.get('config.json')
                        .map(function (res) {
                        return res.json();
                    })
                        .subscribe(function (config) {
                        console.log("config loaded, fire event");
                        _this.backendUrl = config.backendUrl;
                        _this.printerUrl = config.printerUrl;
                        _this.username = config.username;
                        _this.password = config.password;
                        _this.cashbox = config.cashbox;
                        console.log("++++++++");
                        _this.configured.emit(config);
                    });
                }
                ConfigService.prototype.isAlreadyConfigured = function () {
                    return lang_1.isPresent(this.backendUrl) &&
                        lang_1.isPresent(this.printerUrl) &&
                        lang_1.isPresent(this.username) &&
                        lang_1.isPresent(this.password);
                };
                ConfigService.prototype.isConfigured = function () {
                    return this.configured;
                };
                ConfigService.prototype.getBackendUrl = function () {
                    return this.backendUrl;
                };
                ConfigService.prototype.getPrinterUrl = function () {
                    return this.printerUrl;
                };
                ConfigService.prototype.getDefaultHeader = function () {
                    var headers = new http_1.Headers();
                    headers.append("Content-Type", "application/json");
                    headers.append("username", this.username);
                    headers.append("password", this.password);
                    headers.append("cashbox", this.cashbox);
                    return headers;
                };
                ConfigService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [http_1.Http])
                ], ConfigService);
                return ConfigService;
            }());
            exports_1("ConfigService", ConfigService);
        }
    }
});
