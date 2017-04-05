import {Component, OnInit, enableProdMode} from "angular2/core";
import {BoatService} from "./../service/boat.service";
import {ConfigService} from "./../service/config.service";
import {HTTP_PROVIDERS} from "angular2/http";
import {BoatsComponent} from "./rental/boats.component";
import {CommitmentsComponent} from "./rental/commitments.component";
import {CommitmentService} from "./../service/commitment.service";
import {PromotionService} from "./../service/promotion.service";
import {PromotionsBeforeComponent} from "./rental/promotionsBefore.component";
import {BoatCountComponent} from "./rental/boatCount.component";
import {InfoComponent} from "./info.component";
import {InfoService} from "./../service/info.service";
import {MenuComponent} from "./menu.component";
import {ActionComponent} from "./rental/action.component";
import {RentalService} from "./../service/rental.service";
//noinspection TypeScriptCheckImport
import {Modal} from "lib/angular2-modal";
import {KeyBindingService} from "./../service/keybinding.service";
import {ModalHandler} from "./../modalHandler";
import {PrettyPrinter} from "./../prettyprinter";
import {StatistikComponent} from "./rental/statistik.component";
import {Printer} from "./../printer";
import {JournalService} from "./../service/journal.service";
import {ModeService, Mode} from "./../service/mode.service";
import {RentalsComponent} from "./rentals/rentals.component";
import {StatsComponent} from "./stats/stats.component";
import {VersionComponent} from "./version.component";
import {ConfigComponent} from "./config/config.component";
import {ErrorService} from "../service/error.service";
import {SignatureDeviceComponent} from "./signatureDevice.component";

enableProdMode();

@Component({
    selector: 'my-app',
    templateUrl: "html/component/app.component.html",
    styleUrls: ["css/component/app.component.css"],
    directives: [BoatsComponent, CommitmentsComponent, PromotionsBeforeComponent, BoatCountComponent, InfoComponent, MenuComponent, ActionComponent, StatistikComponent, RentalsComponent, StatsComponent, VersionComponent, ConfigComponent, SignatureDeviceComponent],
    providers: [BoatService, CommitmentService, PromotionService, ConfigService, InfoService, RentalService, HTTP_PROVIDERS, Modal, KeyBindingService, ModalHandler, PrettyPrinter, Printer, JournalService, ModeService, ErrorService]
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
