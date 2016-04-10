System.register(['angular2/core', "../../service/boat.service", "../../service/config.service", "../../prettyprinter"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, boat_service_1, config_service_1, prettyprinter_1;
    var StatistikComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (boat_service_1_1) {
                boat_service_1 = boat_service_1_1;
            },
            function (config_service_1_1) {
                config_service_1 = config_service_1_1;
            },
            function (prettyprinter_1_1) {
                prettyprinter_1 = prettyprinter_1_1;
            }],
        execute: function() {
            StatistikComponent = (function () {
                function StatistikComponent(boatService, configService, pp) {
                    this.boatService = boatService;
                    this.configService = configService;
                    this.pp = pp;
                    this.dateString = "Datum: ";
                    this.timeString = "Uhrzeit: ";
                    this.updateDate();
                    this.updateTime();
                }
                StatistikComponent.prototype.getNextDayNumber = function () {
                    return this.boatService.getNextDayNumber();
                };
                StatistikComponent.prototype.updateDate = function () {
                    var date = new Date();
                    this.dateString = "Datum: " +
                        this.pp.pp2Pos(date.getDate()) + ". " +
                        this.pp.pp2Pos(date.getMonth() + 1) + ". " +
                        date.getFullYear();
                };
                StatistikComponent.prototype.updateTime = function () {
                    var _this = this;
                    var date = new Date();
                    this.timeString = "Uhrzeit: " +
                        this.pp.pp2Pos(date.getHours()) + ":" +
                        this.pp.pp2Pos(date.getMinutes());
                    setTimeout(function () { return _this.updateTime(); }, 60000);
                };
                StatistikComponent = __decorate([
                    core_1.Component({
                        selector: 'statistik',
                        templateUrl: "html/component/rental/statistik.component.html",
                        styleUrls: ["css/component/rental/statistik.component.css"]
                    }), 
                    __metadata('design:paramtypes', [boat_service_1.BoatService, config_service_1.ConfigService, prettyprinter_1.PrettyPrinter])
                ], StatistikComponent);
                return StatistikComponent;
            })();
            exports_1("StatistikComponent", StatistikComponent);
        }
    }
});
