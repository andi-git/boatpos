import {Component} from 'angular2/core';
import {Boat} from './boat';
import {BoatService} from "./boat.service";
import {ConfigService} from "./config.service";
import {InfoService} from "./info.service";

@Component({
    selector: 'boats',
    templateUrl: "boats.component.html",
    styleUrls: ["boats.component.css"]
})
export class BoatsComponent {

    private boats:Boat[];
    private subscription:any;

    constructor(private boatService:BoatService, private configService:ConfigService, private infoService:InfoService) {
    }

    ngOnInit() {
        this.subscription = this.configService.isConfigured().subscribe(config =>
            this.boatService.getBoats().subscribe(boats => this.boats = boats)
        );
    }

    onSelect(boat:Boat) {
        if (boat.selected) {
            this.boats.forEach(boat => boat.selected = false);
            this.infoService.event().emit("Boot '" + boat.name + "' wurde entfernt.");
        } else {
            this.boats.forEach(boat => boat.selected = false);
            boat.selected = true;
            this.infoService.event().emit("Boot '" + boat.name + "' wurde ausgewählt.");
        }
    }
}