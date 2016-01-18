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
    private title = 'Tour of Heroes';
    private heroes:Hero[];
    private selectedHero:Hero;

    private boats:BoatCompact[];
    private subscription: any;

    constructor(private heroService:HeroService, private boatService:BoatService, private configService:ConfigService) {
    }

    getBoats() {
        this.boatService.getBoats().subscribe(boats => this.boats = boats);
    }

    getHeroes() {
        this.heroService.getHeroes().then(heroes => this.heroes = heroes);
    }

    ngOnInit() {
        this.getHeroes();
        this.subscription = this.configService.isConfigured().subscribe(config => this.getBoats());
    }

    onSelect(hero:Hero) {
        this.selectedHero = hero;
    }
}
