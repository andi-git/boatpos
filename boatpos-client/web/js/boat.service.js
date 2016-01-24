System.register(['./boat', 'angular2/core', 'angular2/http', 'rxjs/add/operator/map', "./config.service"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var boat_1, core_1, http_1, config_service_1;
    var BoatService;
    return {
        setters:[
            function (boat_1_1) {
                boat_1 = boat_1_1;
            },
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (_1) {},
            function (config_service_1_1) {
                config_service_1 = config_service_1_1;
            }],
        execute: function() {
            BoatService = (function () {
                // constructors do dependency injection in Angular2
                function BoatService(http, configService) {
                    var _this = this;
                    this.http = http;
                    this.configService = configService;
                    // when configuration is finished, load and cache boats
                    this.configService.isConfigured().subscribe(function (config) {
                        console.log("constructor of BoatService");
                        _this.loadBoats().subscribe(function (boats) { return _this.boatsCache = boats; });
                    });
                }
                BoatService.prototype.loadBoats = function () {
                    // call the rest-service
                    return this.http.get(this.configService.getBackendUrl() + 'rest/boat/enabled')
                        .map(function (res) { return res.json(); })
                        .map(function (boats) {
                        var result = [];
                        if (boats) {
                            boats.forEach(function (boat) {
                                result.push(new boat_1.Boat(boat.id, boat.name, boat.shortName, boat.enabled, boat.priority, boat.pictureUrlSmall, boat.pictureUrlMedium, boat.keyBinding));
                            });
                        }
                        return result;
                    });
                };
                BoatService.prototype.getBoatCount = function () {
                    return this.http.get(this.configService.getBackendUrl() + 'rest/boat/count')
                        .map(function (res) {
                        return res.json();
                    })
                        .map(function (boatCounts) {
                        var result = [];
                        if (boatCounts) {
                            boatCounts.forEach(function (boatCount) {
                                result.push(new boat_1.BoatCount(boatCount.id, boatCount.name, boatCount.shortName, boatCount.count, boatCount.max));
                            });
                        }
                        return result;
                    });
                };
                BoatService.prototype.getBoats = function () {
                    return this.boatsCache;
                };
                BoatService.prototype.getBoatByKeyBinding = function (keyBinding) {
                    var boat = null;
                    this.getBoats().forEach(function (b) {
                        if (b.keyBinding == keyBinding) {
                            boat = b;
                        }
                    });
                    return boat;
                };
                BoatService.prototype.resetSelected = function () {
                    this.getBoats().forEach(function (boat) { return boat.selected = false; });
                };
                BoatService.prototype.getSelectedBoat = function () {
                    var boatSelected = null;
                    this.getBoats().forEach(function (boat) {
                        console.log("check if " + boat.name + " is selected: " + boat.selected);
                        if (boat.selected === true) {
                            console.log("ok, " + boat.name + " is selected...");
                            boatSelected = boat;
                        }
                    });
                    return boatSelected;
                };
                BoatService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [http_1.Http, config_service_1.ConfigService])
                ], BoatService);
                return BoatService;
            })();
            exports_1("BoatService", BoatService);
        }
    }
});
