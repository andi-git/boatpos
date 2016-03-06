System.register(['angular2/core', "./service/mode.service", "./service/info.service"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, mode_service_1, info_service_1, mode_service_2;
    var MenuComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (mode_service_1_1) {
                mode_service_1 = mode_service_1_1;
                mode_service_2 = mode_service_1_1;
            },
            function (info_service_1_1) {
                info_service_1 = info_service_1_1;
            }],
        execute: function() {
            MenuComponent = (function () {
                function MenuComponent(modeService, infoService) {
                    this.modeService = modeService;
                    this.infoService = infoService;
                }
                MenuComponent.prototype.modeRental = function () {
                    this.infoService.event().emit("'Vermietung' wird angezeigt.");
                    this.modeService.setMode(mode_service_2.Mode.RENTAL);
                };
                MenuComponent.prototype.modeRentals = function () {
                    console.log("'Alle Vermietungen' werden angezeigt");
                    this.modeService.setMode(mode_service_2.Mode.RENTALS);
                };
                MenuComponent.prototype.modeStats = function () {
                    console.log("'Statistiken' werden angezeigt");
                    this.modeService.setMode(mode_service_2.Mode.STATS);
                };
                MenuComponent = __decorate([
                    core_1.Component({
                        selector: 'mainmenu',
                        templateUrl: "../html/menu.component.html",
                        styleUrls: ["../css/menu.component.css"]
                    }), 
                    __metadata('design:paramtypes', [mode_service_1.ModeService, info_service_1.InfoService])
                ], MenuComponent);
                return MenuComponent;
            })();
            exports_1("MenuComponent", MenuComponent);
        }
    }
});
