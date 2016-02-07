import {Component, NgZone} from 'angular2/core';
import {Boat} from './boat';
import {BoatService} from "./boat.service";
import {InfoService} from "./info.service";
import {KeyBindingService} from "./keybinding.service";

@Component({
    selector: 'boats',
    templateUrl: "boats.component.html",
    styleUrls: ["boats.component.css"]
})
export class BoatsComponent {

    constructor(private boatService:BoatService, private infoService:InfoService, private keyBinding:KeyBindingService, private zone:NgZone) {
    }

    ngOnInit() {
        // bind the key-inputs
        let map:{[key: string] : ((e:ExtendedKeyboardEvent, combo:string) => any)} = {};
        // a...j
        for (var i = 97; i <= 106; i++) {
            map[String.fromCharCode(i)] = (e) => {
                // run the action within zone.run to update/render the view
                this.zone.run(() => {
                    // get the boat behind the binding and call onSelect
                    var boat = this.boatService.getBoatByKeyBinding(String.fromCharCode(e.charCode));
                    if (boat != null) {
                        this.click(boat);
                    }
                });
            };
        }
        this.keyBinding.addBindingForMain(map);
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