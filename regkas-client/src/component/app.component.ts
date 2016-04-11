import {Component, OnInit} from "angular2/core";
import {ConfigService} from "./../service/config.service";
import {HTTP_PROVIDERS} from "angular2/http";
import {InfoComponent} from "./info.component";
import {InfoService} from "./../service/info.service";
import {MenuComponent} from "./menu.component";
//noinspection TypeScriptCheckImport
import {Modal} from "lib/angular2-modal";
import {ModalHandler} from "./../modalHandler";
import {PrettyPrinter} from "./../prettyprinter";
import {Printer} from "./../printer";
import {ModeService, Mode} from "./../service/mode.service";
import {VersionComponent} from "./version.component";
import {KeyBindingService} from "../service/keybinding.service";

@Component({
    selector: 'my-app',
    templateUrl: "html/component/app.component.html",
    styleUrls: ["css/component/app.component.css"],
    directives: [InfoComponent, MenuComponent, VersionComponent],
    providers: [ConfigService, InfoService, HTTP_PROVIDERS, Modal, KeyBindingService, ModalHandler, PrettyPrinter, Printer, ModeService]
})
export class AppComponent implements OnInit {

    private modeService:ModeService;

    mode:String;

    constructor(modeService:ModeService) {
        this.modeService = modeService;
        this.modeService.event().subscribe((mode) => {
            console.log("mode-change - AppComponent: " + Mode[mode]);
            this.mode = Mode[mode];
        });
    }

    ngOnInit():any {
        return undefined;
    }
}
