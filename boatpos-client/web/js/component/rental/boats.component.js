System.register(['angular2/core', "../../service/boat.service", "../../service/info.service", "../../service/keybinding.service"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, boat_service_1, info_service_1, keybinding_service_1;
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
            function (keybinding_service_1_1) {
                keybinding_service_1 = keybinding_service_1_1;
            }],
        execute: function() {
            BoatsComponent = (function () {
                function BoatsComponent(boatService, infoService, keyBinding, zone) {
                    this.boatService = boatService;
                    this.infoService = infoService;
                    this.keyBinding = keyBinding;
                    this.zone = zone;
                }
                BoatsComponent.prototype.ngOnInit = function () {
                    var _this = this;
                    // bind the key-inputs
                    var map = {};
                    // a...j
                    for (var i = 97; i <= 106; i++) {
                        map[String.fromCharCode(i)] = function (e) {
                            // run the action within zone.run to update/render the view
                            _this.zone.run(function () {
                                // get the boat behind the binding and call onSelect
                                var boat = _this.boatService.getBoatByKeyBinding(String.fromCharCode(e.charCode));
                                if (boat != null) {
                                    _this.click(boat);
                                }
                            });
                        };
                    }
                    this.keyBinding.addBindingForMain(map);
                };
                BoatsComponent.prototype.getBoats = function () {
                    return this.boatService.getBoats();
                };
                BoatsComponent.prototype.click = function (boat) {
                    if (boat.selected) {
                        this.boatService.resetSelected();
                        this.infoService.event().emit("Boot '" + boat.name + "' wurde entfernt.");
                    }
                    else {
                        this.boatService.resetSelected();
                        boat.selected = true;
                        this.infoService.event().emit("Boot '" + boat.name + "' wurde ausgewählt.");
                    }
                };
                BoatsComponent = __decorate([
                    core_1.Component({
                        selector: 'boats',
                        templateUrl: "../../../html/component/rental/boats.component.html",
                        styleUrls: ["../../../css/component/rental/boats.component.css"]
                    }), 
                    __metadata('design:paramtypes', [boat_service_1.BoatService, info_service_1.InfoService, keybinding_service_1.KeyBindingService, core_1.NgZone])
                ], BoatsComponent);
                return BoatsComponent;
            })();
            exports_1("BoatsComponent", BoatsComponent);
        }
    }
});
