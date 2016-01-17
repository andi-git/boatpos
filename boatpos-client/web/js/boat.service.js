System.register(['./boat', 'angular2/core', 'angular2/http', 'rxjs/add/operator/map'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var boat_1, core_1, http_1;
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
            function (_1) {}],
        execute: function() {
            BoatService = (function () {
                // constructors do dependency injection in Angular2
                function BoatService(http) {
                    this.http = http;
                }
                BoatService.prototype.getBoats = function () {
                    console.log("getBoats()");
                    // call the rest-service
                    return this.http.get('http://home.com:8180/boatpos-server/rest/boat')
                        .map(function (res) { return res.json(); })
                        .map(function (boats) {
                        var result = [];
                        if (boats) {
                            boats.forEach(function (boat) {
                                result.push(new boat_1.BoatCompact(boat.id, boat.name, boat.shortName, boat.pictureUrlMedium));
                            });
                        }
                        return result;
                    });
                };
                BoatService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [http_1.Http])
                ], BoatService);
                return BoatService;
            })();
            exports_1("BoatService", BoatService);
        }
    }
});
