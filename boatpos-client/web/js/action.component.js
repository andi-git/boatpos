System.register(['angular2/core', "./boat.service", "./info.service", "angular2/core"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, boat_service_1, info_service_1, core_2;
    var ActionComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (boat_service_1_1) {
                boat_service_1 = boat_service_1_1;
            },
            function (info_service_1_1) {
                info_service_1 = info_service_1_1;
            },
            function (core_2_1) {
                core_2 = core_2_1;
            }],
        execute: function() {
            ActionComponent = (function () {
                function ActionComponent(boatService, infoService, zone) {
                    this.boatService = boatService;
                    this.infoService = infoService;
                    this.zone = zone;
                }
                ActionComponent.prototype.ngOnInit = function () {
                };
                ActionComponent.prototype.cancel = function () {
                };
                ActionComponent = __decorate([
                    core_1.Component({
                        selector: 'action',
                        templateUrl: "action.component.html",
                        styleUrls: ["action.component.css"]
                    }), 
                    __metadata('design:paramtypes', [boat_service_1.BoatService, info_service_1.InfoService, core_2.NgZone])
                ], ActionComponent);
                return ActionComponent;
            })();
            exports_1("ActionComponent", ActionComponent);
        }
    }
});
