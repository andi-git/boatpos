import {Component, OnInit} from 'angular2/core';
import {Boat} from './boat';
import {BoatService} from "./boat.service";
import {ConfigService} from "./config.service";
import {Http, Headers, HTTP_PROVIDERS} from 'angular2/http';
import {Config} from "./config";
import {Observable} from "rxjs/Observable";
import {BoatsComponent} from "./boats.component";
import {CommitmentsComponent} from "./commitments.component";
import {CommitmentService} from "./commitment.service";

@Component({
    selector: 'my-app',
    templateUrl: "app.component.html",
    styleUrls: ["app.component.css"],
    directives: [BoatsComponent, CommitmentsComponent],
    providers: [BoatService, CommitmentService, ConfigService, HTTP_PROVIDERS]
})
export class AppComponent implements OnInit {

    ngOnInit():any {
        return undefined;
    }
}
