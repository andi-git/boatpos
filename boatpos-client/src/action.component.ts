import {Component} from 'angular2/core';
import {Boat} from './boat';
import {BoatService} from "./boat.service";
import {InfoService} from "./info.service";
import {NgZone} from "angular2/core";
import {CommitmentService} from "./commitment.service";
import {PromotionService} from "./promotion.service";
import {Inject} from "angular2/core";
import {Injector} from "angular2/core";
import {Commitment} from "./commitment";
import {PromotionBefore} from "./promotionBefore";
import {Departe} from "./departe";

@Component({
    selector: 'action',
    templateUrl: "action.component.html",
    styleUrls: ["action.component.css"],
})
export class ActionComponent {

    private rentalNumber:number;

    constructor(private boatService:BoatService,
                private commitmentService:CommitmentService,
                private promotionService:PromotionService,
                private infoService:InfoService) {
        new Mousetrap().bind(['K'], () => {
            this.cancel();
        });
        new Mousetrap().bind(['L'], () => {
            this.departe();
        });
        new Mousetrap().bind(['1', '2', '3', '4', '5', '6', '7', '8', '9', '0'], (e) => {
            this.addToNumber(String.fromCharCode(e.charCode));
        });
    }

    private cancel() {
        this.rentalNumber = null;
        this.resetUi();
        this.infoService.event().emit("Aktion abgebrochen, Elemente wieder zurückgesetzt.");
    }

    private resetUi() {
        this.boatService.resetSelected();
        this.commitmentService.resetSelected();
        this.promotionService.resetSelected();
    }

    private departe() {
        let boat:Boat = this.boatService.getSelectedBoat();
        let commitments:Array<Commitment> = this.commitmentService.getSelectedCommitmens();
        let promotionBefore:PromotionBefore = this.promotionService.getSelectedPromotionsBefore();
        if (boat != null) {
            let departe:Departe = new Departe(boat, commitments, promotionBefore);

            this.resetUi();
            this.infoService.event().emit("'" + boat.name + "' wurde vermietet.");
        } else {
            this.infoService.event().emit("Vermietung nicht möglich: es wurde kein Boot augewählt");
        }
    }

    private addToNumber(s:string) {
        this.rentalNumber = (this.rentalNumber == null ? "" : this.rentalNumber) + s;
        this.infoService.event().emit("Nummer geändert.");
    }
}