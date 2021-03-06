import {Component} from 'angular2/core';
import {ModeService} from "./../service/mode.service";
import {InfoService} from "./../service/info.service";
import {Mode} from "./../service/mode.service";
import {ConfigService} from "./../service/config.service";

@Component({
    selector: 'mainmenu',
    templateUrl: "html/component/menu.component.html",
    styleUrls: ["css/component/menu.component.css"]
})
export class MenuComponent {

    constructor(private modeService:ModeService, private infoService:InfoService, private configService:ConfigService) {
    }

    private modeRental() {
        this.infoService.event().emit("'Vermietung' wird angezeigt.");
        this.modeService.event().emit(Mode.RENTAL);
    }

    private modeRentals() {
        this.infoService.event().emit("'Alle Vermietungen' werden angezeigt.");
        this.modeService.event().emit(Mode.RENTALS);
    }

    private modeStats() {
        this.infoService.event().emit("'Statistiken' werden angezeigt.");
        this.modeService.event().emit(Mode.STATS);
    }

    private modeConfig() {
        this.infoService.event().emit("'Konfiguration' wird angezeigt.");
        this.modeService.event().emit(Mode.CONFIG);
    }

    private reload() {
        location.reload();
    }

    private close() {
        window.close();
    }
}