import {Component} from 'angular2/core';
import {ModeService} from "./service/mode.service";
import {InfoService} from "./service/info.service";
import {Mode} from "./service/mode.service";

@Component({
    selector: 'mainmenu',
    templateUrl: "../html/menu.component.html",
    styleUrls: ["../css/menu.component.css"]
})
export class MenuComponent {

    constructor(private modeService:ModeService, private infoService:InfoService) {

    }

    private modeRental() {
        this.infoService.event().emit("'Vermietung' wird angezeigt.");
        this.modeService.setMode(Mode.RENTAL);
    }

    private modeRentals() {
        console.log("'Alle Vermietungen' werden angezeigt");
        this.modeService.setMode(Mode.RENTALS);
    }

    private modeStats() {
        console.log("'Statistiken' werden angezeigt");
        this.modeService.setMode(Mode.STATS);
    }
}