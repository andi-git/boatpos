System.register(['angular2/core', './hero-detail.component', './hero.service', "./boat.service", "./config.service", 'angular2/http'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, hero_detail_component_1, hero_service_1, boat_service_1, config_service_1, http_1;
    var AppComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (hero_detail_component_1_1) {
                hero_detail_component_1 = hero_detail_component_1_1;
            },
            function (hero_service_1_1) {
                hero_service_1 = hero_service_1_1;
            },
            function (boat_service_1_1) {
                boat_service_1 = boat_service_1_1;
            },
            function (config_service_1_1) {
                config_service_1 = config_service_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            }],
        execute: function() {
            AppComponent = (function () {
                function AppComponent(_heroService, _boatService, _configService) {
                    var _this = this;
                    this._heroService = _heroService;
                    this._boatService = _boatService;
                    this._configService = _configService;
                    this.title = 'Tour of Heroes';
                    this._configService.loadConfig().subscribe(function (config) { return _this.backendUrl = config.backendUrl; });
                    this._configService.loadConfig().subscribe(function (config) { return _this.config = config; });
                }
                AppComponent.prototype.getBoats = function () {
                    var _this = this;
                    this._boatService.getBoats().subscribe(function (boats) { return _this.boats = boats; });
                };
                AppComponent.prototype.getHeroes = function () {
                    var _this = this;
                    this._heroService.getHeroes().then(function (heroes) { return _this.heroes = heroes; });
                };
                AppComponent.prototype.ngOnInit = function () {
                    this.getHeroes();
                    this.getBoats();
                };
                AppComponent.prototype.onSelect = function (hero) {
                    this.selectedHero = hero;
                };
                AppComponent = __decorate([
                    core_1.Component({
                        selector: 'my-app',
                        templateUrl: "app.component.html",
                        styleUrls: ["app.component.css"],
                        directives: [hero_detail_component_1.HeroDetailComponent],
                        providers: [hero_service_1.HeroService, boat_service_1.BoatService, config_service_1.ConfigService, http_1.HTTP_PROVIDERS]
                    }), 
                    __metadata('design:paramtypes', [hero_service_1.HeroService, boat_service_1.BoatService, config_service_1.ConfigService])
                ], AppComponent);
                return AppComponent;
            })();
            exports_1("AppComponent", AppComponent);
        }
    }
});
