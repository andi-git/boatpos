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
    var BoatsComponent;
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
            BoatsComponent = (function () {
                function BoatsComponent(boatService, infoService, zone) {
                    this.boatService = boatService;
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
                            var boat = _this.boatService.getBoatByKeyBinding(String.fromCharCode(e.charCode));
                            if (boat != null) {
                                _this.click(boat);
                            }
                        });
                    });
                };
                BoatsComponent.prototype.getBoats = function () {
                    return this.boatService.getBoats();
                };
                BoatsComponent.prototype.click = function (boat) {
                    if (boat.selected) {
                        this.getBoats().forEach(function (boat) { return boat.selected = false; });
                        this.infoService.event().emit("Boot '" + boat.name + "' wurde entfernt.");
                    }
                    else {
                        this.getBoats().forEach(function (boat) { return boat.selected = false; });
                        boat.selected = true;
                        this.infoService.event().emit("Boot '" + boat.name + "' wurde ausgewählt.");
                    }
                };
                BoatsComponent = __decorate([
                    core_1.Component({
                        selector: 'boats',
                        templateUrl: "boats.component.html",
                        styleUrls: ["boats.component.css"]
                    }), 
                    __metadata('design:paramtypes', [boat_service_1.BoatService, info_service_1.InfoService, core_2.NgZone])
                ], BoatsComponent);
                return BoatsComponent;
            })();
            exports_1("BoatsComponent", BoatsComponent);
        }
    }
});
