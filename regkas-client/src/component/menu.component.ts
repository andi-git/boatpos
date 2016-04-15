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

    private modeRegkas() {
        this.infoService.event().emit("'Registrierkassa' wird angezeigt.");
        this.modeService.event().emit(Mode.REGKAS);
    }

    private modeStats() {
        this.infoService.event().emit("'Statistiken' wird angezeigt.");
        this.modeService.event().emit(Mode.STATS);
    }
}