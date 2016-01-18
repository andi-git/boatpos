import {Component} from 'angular2/core';
import {Boat} from './boat';
import {BoatService} from "./boat.service";
import {ConfigService} from "./config.service";

@Component({
    selector: 'boats',
    templateUrl: "boats.component.html",
    styleUrls: ["boats.component.css"]
})
export class BoatsComponent {

    private boats:Boat[];
    private selectedBoat:Boat;
    private subscription: any;

    constructor(private boatService:BoatService, private configService:ConfigService) {
    }

    getBoats() {
        this.boatService.getBoats().subscribe(boats => this.boats = boats);
    }

    ngOnInit() {
        this.subscription = this.configService.isConfigured().subscribe(config => this.getBoats());
    }

    onSelect(boat:Boat) {
        this.selectedBoat = boat;
    }
}