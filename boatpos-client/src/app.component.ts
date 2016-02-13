import {Component, OnInit} from 'angular2/core';
import {Boat} from './boat';
import {BoatService} from "./boat.service";
import {ConfigService} from "./config.service";
import {Http, Headers, HTTP_PROVIDERS} from 'angular2/http';
import {Config} from "./config";
import {Observable} from "rxjs/Observable";
import {BoatsComponent} from "./boats.component";
import {CommitmentsComponent} from "./commitments.component";
import {CommitmentService} from "./commitment.service";
import {PromotionService} from "./promotion.service";
import {PromotionsBeforeComponent} from "./promotionsBefore.component";
import {BoatCountComponent} from "./boatCount.component";
import {InfoComponent} from "./info.component";
import {InfoService} from "./info.service";
import {MenuComponent} from "./menu.component";
import {ActionComponent} from "./action.component";
import {RentalService} from "./rental.service";
//noinspection TypeScriptCheckImport
import {Modal} from "lib/angular2-modal";
import {KeyBindingService} from "./keybinding.service";
import {ModalHandler} from "./modalHandler";
import {PrettyPrinter} from "./prettyprinter";
import {StatistikComponent} from "./statistik.component";

@Component({
    selector: 'my-app',
    templateUrl: "app.component.html",
    styleUrls: ["app.component.css"],
    directives: [BoatsComponent, CommitmentsComponent, PromotionsBeforeComponent, BoatCountComponent, InfoComponent, MenuComponent, ActionComponent, StatistikComponent],
    providers: [BoatService, CommitmentService, PromotionService, ConfigService, InfoService, RentalService, HTTP_PROVIDERS, Modal, KeyBindingService, ModalHandler, PrettyPrinter]
})
export class AppComponent implements OnInit {

    ngOnInit():any {
        return undefined;
    }
}
