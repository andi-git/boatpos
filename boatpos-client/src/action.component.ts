import {Component} from 'angular2/core';
import {Boat} from './boat';
import {BoatService} from "./boat.service";
import {InfoService} from "./info.service";
import {NgZone} from "angular2/core";

@Component({
    selector: 'action',
    templateUrl: "action.component.html",
    styleUrls: ["action.component.css"]
})
export class ActionComponent {

    private rentalNumber:number;

    constructor(private boatService:BoatService, private infoService:InfoService, private zone:NgZone) {
    }

    ngOnInit() {
    }

    cancel() {

    }
}