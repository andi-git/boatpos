import {Component, OnInit} from 'angular2/core';
import {Hero} from './hero';
import {HeroDetailComponent} from './hero-detail.component';
import {HeroService} from './hero.service';
import {BoatCompact} from './boat';
import {BoatService} from "./boat.service";
import {ConfigService} from "./config.service";
import {Http, Headers, HTTP_PROVIDERS} from 'angular2/http';
import {Config} from "./config";
import {Observable} from "rxjs/Observable";

@Component({
    selector: 'my-app',
    templateUrl: "app.component.html",
    styleUrls: ["app.component.css"],
    directives: [HeroDetailComponent],
    providers: [HeroService, BoatService, ConfigService, HTTP_PROVIDERS]
})
export class AppComponent implements OnInit {
    public title = 'Tour of Heroes';
    public heroes:Hero[];
    public selectedHero:Hero;

    public boats:BoatCompact[];
    private config:Config;
    private backendUrl:String;

    constructor(private _heroService:HeroService, private _boatService:BoatService, private _configService:ConfigService) {
        this._configService.loadConfig().subscribe(config => this.backendUrl = config.backendUrl)
        this._configService.loadConfig().subscribe(config => this.config = config)
    }

    getBoats() {
        this._boatService.getBoats().subscribe(boats => this.boats = boats);
    }

    getHeroes() {
        this._heroService.getHeroes().then(heroes => this.heroes = heroes);
    }

    ngOnInit() {
        this.getHeroes();
        this.getBoats();
    }

    onSelect(hero:Hero) {
        this.selectedHero = hero;
    }
}
