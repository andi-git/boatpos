import {Component, OnInit} from 'angular2/core';
import {Boat} from './boat';
import {BoatService} from "./boat.service";
import {ConfigService} from "./config.service";
import {Http, Headers, HTTP_PROVIDERS} from 'angular2/http';
import {Config} from "./config";
import {Observable} from "rxjs/Observable";
import {BoatsComponent} from "./boats.component";

@Component({
    selector: 'my-app',
    templateUrl: "app.component.html",
    styleUrls: ["app.component.css"],
    directives: [BoatsComponent],
    providers: [BoatService, ConfigService, HTTP_PROVIDERS]
})
export class AppComponent implements OnInit {

    ngOnInit():any {
        return undefined;
    }
}
