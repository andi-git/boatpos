import {Component} from 'angular2/core';
import {Boat} from './boat';
import {BoatService} from "./boat.service";
import {InfoService} from "./info.service";
import {NgZone} from "angular2/core";

@Component({
    selector: 'boats',
    templateUrl: "boats.component.html",
    styleUrls: ["boats.component.css"]
})
export class BoatsComponent {

    constructor(private boatService:BoatService, private infoService:InfoService, private zone:NgZone) {
    }

    ngOnInit() {
        // bind the key-inputs
        Mousetrap.bind(['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'], (e) => {
            // run the action within zone.run to update/render the view
            this.zone.run(() => {
                // get the boat behind the binding and call onSelect
                var boat = this.boatService.getBoatByKeyBinding(String.fromCharCode(e.charCode));
                if (boat != null) {
                    this.click(boat);
                }
            });
        });
    }

    getBoats():Array<Boat> {
        return this.boatService.getBoats();
    }

    click(boat:Boat) {
        if (boat.selected) {
            this.boatService.resetSelected();
            this.infoService.event().emit("Boot '" + boat.name + "' wurde entfernt.");
        } else {
            this.boatService.resetSelected();
            boat.selected = true;
            this.infoService.event().emit("Boot '" + boat.name + "' wurde ausgewählt.");
        }
    }
}