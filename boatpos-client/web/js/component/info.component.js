System.register(['angular2/core', "../service/info.service", "../service/error.service"], function(exports_1, context_1) {
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
    var core_1, info_service_1, error_service_1;
    var InfoComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (info_service_1_1) {
                info_service_1 = info_service_1_1;
            },
            function (error_service_1_1) {
                error_service_1 = error_service_1_1;
            }],
        execute: function() {
            InfoComponent = (function () {
                function InfoComponent(infoService, errorService) {
                    var _this = this;
                    this.infoService = infoService;
                    this.errorService = errorService;
                    this.info = "Info: ";
                    this.containerClass = "info-container";
                    infoService.event().subscribe(function (info) {
                        _this.info = "Info: " + info;
                        _this.containerClass = "info-container";
                        console.log("info: " + _this.info);
                    });
                    errorService.event().subscribe(function (error) {
                        _this.info = "Fehler: " + error;
                        _this.containerClass = "error-container";
                        console.log("info: " + _this.info);
                    });
                }
                InfoComponent = __decorate([
                    core_1.Component({
                        selector: 'info',
                        templateUrl: "html/component/info.component.html",
                        styleUrls: ["css/component/info.component.css"]
                    }), 
                    __metadata('design:paramtypes', [info_service_1.InfoService, error_service_1.ErrorService])
                ], InfoComponent);
                return InfoComponent;
            }());
            exports_1("InfoComponent", InfoComponent);
        }
    }
});
