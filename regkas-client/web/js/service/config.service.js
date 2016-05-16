System.register(["angular2/core", "angular2/http", "rxjs/add/operator/map", "rxjs/add/operator/toPromise", "../model/config", "angular2/src/facade/lang", "../model/ipaddress"], function(exports_1, context_1) {
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
    var core_1, http_1, config_1, lang_1, ipaddress_1;
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
            function (config_1_1) {
                config_1 = config_1_1;
            },
            function (lang_1_1) {
                lang_1 = lang_1_1;
            },
            function (ipaddress_1_1) {
                ipaddress_1 = ipaddress_1_1;
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
                        .subscribe(function (configJson) {
                        console.log("config loaded, fire event");
                        _this.config = new config_1.Config(configJson.backendUrl, configJson.username, configJson.password, configJson.cashBox);
                        _this.http.get(_this.getBackendUrl() + 'rest/printer/ip', { headers: _this.getDefaultHeader() })
                            .map(function (res) { return res.json(); })
                            .map(function (printerBean) {
                            _this.config.printerIp = printerBean.ipAddress;
                            return printerBean.ipAddress;
                        }).subscribe(function (ipAddress) {
                            _this.config.printerIp = ipAddress;
                            _this.configured.emit(_this.config);
                        });
                    });
                }
                ConfigService.prototype.isAlreadyConfigured = function () {
                    return lang_1.isPresent(this.config.backendUrl) &&
                        lang_1.isPresent(this.config.printerIp) &&
                        lang_1.isPresent(this.config.username) &&
                        lang_1.isPresent(this.config.password);
                };
                ConfigService.prototype.isConfigured = function () {
                    return this.configured;
                };
                ConfigService.prototype.getBackendUrl = function () {
                    if (lang_1.isPresent(this.config)) {
                        return this.config.backendUrl;
                    }
                    else {
                        return "n/a";
                    }
                };
                ConfigService.prototype.getPrinterIp = function () {
                    if (lang_1.isPresent(this.config)) {
                        return this.config.printerIp;
                    }
                    else {
                        return "n/a";
                    }
                };
                ConfigService.prototype.getDefaultHeader = function () {
                    var headers = new http_1.Headers();
                    headers.append("Content-Type", "application/json");
                    headers.append("username", this.config.username);
                    headers.append("password", this.config.password);
                    headers.append("cashbox", this.config.cashBox);
                    return headers;
                };
                ConfigService.prototype.addQueryParamCredentials = function (path) {
                    return path +
                        "username=" + this.config.username + "&" +
                        "password=" + this.config.password + "&" +
                        "cashbox=" + this.config.cashBox;
                };
                ConfigService.prototype.savePrinterIp = function (ip) {
                    this.http.post(this.getBackendUrl() + 'rest/printer/ip', JSON.stringify(new ipaddress_1.IpAddress(ip)), { headers: this.getDefaultHeader() })
                        .subscribe(this.config.printerIp = ip);
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
