System.register(['angular2/core', "./boat.service", "./config.service", "./info.service", "angular2/core"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, boat_service_1, config_service_1, info_service_1, core_2;
    var BoatsComponent;
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
            function (info_service_1_1) {
                info_service_1 = info_service_1_1;
            },
            function (core_2_1) {
                core_2 = core_2_1;
            }],
        execute: function() {
            BoatsComponent = (function () {
                function BoatsComponent(boatService, configService, infoService, zone) {
                    this.boatService = boatService;
                    this.configService = configService;
                    this.infoService = infoService;
                    this.zone = zone;
                }
                BoatsComponent.prototype.ngOnInit = function () {
                    var _this = this;
                    // bind the key-inputs
                    Mousetrap.bind(['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'], function (e) {
                        // run the action within zone.run to update/render the view
                        _this.zone.run(function () {
                            // get the boat behind the binding and call onSelect
                            _this.onSelect(_this.getBoatByKeyBinding(String.fromCharCode(e.charCode)));
                        });
                    });
                    this.subscription = this.configService.isConfigured().subscribe(function (config) {
                        return _this.boatService.getBoats().subscribe(function (boats) { return _this.boats = boats; });
                    });
                };
                BoatsComponent.prototype.onSelect = function (boat) {
                    if (boat.selected) {
                        this.boats.forEach(function (boat) { return boat.selected = false; });
                        this.infoService.event().emit("Boot '" + boat.name + "' wurde entfernt.");
                    }
                    else {
                        this.boats.forEach(function (boat) { return boat.selected = false; });
                        boat.selected = true;
                        this.infoService.event().emit("Boot '" + boat.name + "' wurde ausgewählt.");
                    }
                };
                BoatsComponent.prototype.getBoatByKeyBinding = function (keyBinding) {
                    var boat = null;
                    this.boats.forEach(function (b) {
                        if (b.keyBinding == keyBinding) {
                            boat = b;
                        }
                    });
                    return boat;
                };
                BoatsComponent = __decorate([
                    core_1.Component({
                        selector: 'boats',
                        templateUrl: "boats.component.html",
                        styleUrls: ["boats.component.css"]
                    }), 
                    __metadata('design:paramtypes', [boat_service_1.BoatService, config_service_1.ConfigService, info_service_1.InfoService, core_2.NgZone])
                ], BoatsComponent);
                return BoatsComponent;
            })();
            exports_1("BoatsComponent", BoatsComponent);
        }
    }
});
