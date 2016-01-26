import {Component} from 'angular2/core';
import {BoatCount} from './boat';
import {BoatService} from "./boat.service";
import {ConfigService} from "./config.service";

@Component({
    selector: 'boatCount',
    templateUrl: "boatCount.component.html",
    styleUrls: ["boatCount.component.css"]
})
export class BoatCountComponent {

    private boatCounts:BoatCount[];
    private subscription:any;

    constructor(private boatService:BoatService, private configService:ConfigService) {
    }

    ngOnInit() {
        this.subscription = this.configService.isConfigured().subscribe(config =>
            this.boatService.getBoatCount().subscribe(boatCounts => this.boatCounts = boatCounts)
        );
    }

    getNextDayNumber():string {
        return this.boatService.getNextDayNumber();
    }
}