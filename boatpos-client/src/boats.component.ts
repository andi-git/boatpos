import {Component} from 'angular2/core';
import {Boat} from './boat';
import {BoatService} from "./boat.service";
import {ConfigService} from "./config.service";
import {InfoService} from "./info.service";
import {NgZone} from "angular2/core";

@Component({
    selector: 'boats',
    templateUrl: "boats.component.html",
    styleUrls: ["boats.component.css"]
})
export class BoatsComponent {

    private boats:Boat[];
    private subscription:any;
    private zone:NgZone;

    constructor(private boatService:BoatService, private configService:ConfigService, private infoService:InfoService, private zone:NgZone) {
    }

    ngOnInit() {
        // bind the key-inputs
        Mousetrap.bind(['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'], (e) => {
            // run the action within zone.run to update/render the view
            this.zone.run(() => {
                // get the boat behind the binding and call onSelect
                this.onSelect(this.getBoatByKeyBinding(String.fromCharCode(e.charCode)));
            });
        });
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

    getBoatByKeyBinding(keyBinding:string):Boat {
        let boat:Boat = null;
        this.boats.forEach((b) => {
            if (b.keyBinding == keyBinding) {
                boat = b;
            }
        });
        return boat;
    }
}