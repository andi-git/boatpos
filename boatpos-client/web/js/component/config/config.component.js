System.register(["angular2/core", "../../service/config.service", "../../printer"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, config_service_1, printer_1;
    var ConfigComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (config_service_1_1) {
                config_service_1 = config_service_1_1;
            },
            function (printer_1_1) {
                printer_1 = printer_1_1;
            }],
        execute: function() {
            ConfigComponent = (function () {
                function ConfigComponent(config, printer) {
                    this.config = config;
                    this.printer = printer;
                    this.possibleIps = [];
                    console.log("constructor of ConfigComponent");
                    this.possibleIps.push("192.168.8.10");
                }
                ConfigComponent.prototype.printerIp = function () {
                    return this.config.getPrinterIp();
                };
                ConfigComponent.prototype.getPossibleIps = function () {
                    return this.possibleIps;
                };
                ConfigComponent.prototype.testIp = function (ip) {
                    console.log("test ip " + ip);
                    this.printer.printTest(ip);
                };
                ConfigComponent.prototype.useIp = function (ip) {
                    console.log("use ip " + ip);
                    this.config.savePrinterIp(ip);
                };
                ConfigComponent = __decorate([
                    core_1.Component({
                        selector: 'config',
                        templateUrl: "html/component/config/config.component.html",
                        styleUrls: ["css/component/config/config.component.css"]
                    }), 
                    __metadata('design:paramtypes', [config_service_1.ConfigService, printer_1.Printer])
                ], ConfigComponent);
                return ConfigComponent;
            })();
            exports_1("ConfigComponent", ConfigComponent);
        }
    }
});
