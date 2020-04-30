import {Component, OnInit, enableProdMode} from "angular2/core";
import {BoatService} from "./../service/boat.service";
import {ConfigService} from "./../service/config.service";
import {HTTP_PROVIDERS} from "angular2/http";
import {BoatsComponent} from "./rental/boats.component";
import {CommitmentService} from "./../service/commitment.service";
import {PromotionService} from "./../service/promotion.service";
import {InfoComponent} from "./info.component";
import {InfoService} from "./../service/info.service";
import {RentalService} from "./../service/rental.service";
import {KeyBindingService} from "./../service/keybinding.service";
import {ModalHandler} from "./../modalHandler";
import {PrettyPrinter} from "./../prettyprinter";
import {StatistikComponent} from "./rental/statistik.component";
import {Printer} from "./../printer";
import {JournalService} from "./../service/journal.service";
import {VersionComponent} from "./version.component";
import {ErrorService} from "../service/error.service";
//noinspection TypeScriptCheckImport
import {Modal} from "lib/angular2-modal";

enableProdMode();

@Component({
    selector: 'my-app',
    templateUrl: "html/component/app.component.html",
    styleUrls: ["css/component/app.component.css"],
    directives: [BoatsComponent, InfoComponent, StatistikComponent, VersionComponent],
    providers: [BoatService, CommitmentService, PromotionService, ConfigService, InfoService, RentalService, HTTP_PROVIDERS, Modal, KeyBindingService, ModalHandler, PrettyPrinter, Printer, JournalService, ErrorService]
})
export class AppComponent implements OnInit {

    constructor() {
    }

    ngOnInit():any {
        return undefined;
    }
}
