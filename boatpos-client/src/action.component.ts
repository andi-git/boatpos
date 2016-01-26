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
import {PromotionBefore} from "./promotion";
import {Departure} from "./departure";
import {RentalService} from "./rental.service";
import {Rental} from "./rental";

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
                private infoService:InfoService,
                private rentalService:RentalService) {
        new Mousetrap().bind(['K'], () => {
            this.cancel();
        });
        new Mousetrap().bind(['L'], () => {
            this.depart();
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

    private depart() {
        let boat:Boat = this.boatService.getSelectedBoat();
        let commitments:Array<Commitment> = this.commitmentService.getSelectedCommitmens();
        let promotionBefore:PromotionBefore = this.promotionService.getSelectedPromotionsBefore();
        if (boat != null) {
            //let rental:Rental;
            this.rentalService.departe(new Departure(boat, commitments, promotionBefore)).subscribe(
                (rental) => {
                    this.infoService.event().emit("Nr " + rental.dayId + " " + rental.boat.name + " " + this.createStringForCommitments(rental.commitments) + this.createStringForPromotion(rental.promotionBefore) + " wurde vermietet.");
                    this.boatService.updateNextDayNumberString();
                    this.resetUi();
                }
            );
        } else {
            this.infoService.event().emit("Vermietung nicht möglich: es wurde kein Boot augewählt");
        }
    }

    private createStringForPromotion(promotion:PromotionBefore):string {
        return promotion != null ? ("(" + promotion.name + ")") : "";
    }

    private createStringForCommitments(commitments:Array<Commitment>):string {
        let result:string = "";
        if (commitments != null && commitments.length > 0) {
            result += "(";
            let first:boolean = true;
            commitments.forEach((c) => {
                if (first === false) {
                    result += ", "
                }
                if (first === true) {
                    first = false;
                }
                result += c.name;
            });
            result += ") ";
        }
        return result;
    };

    private addToNumber(s:string) {
        this.rentalNumber = Number.parseInt((this.rentalNumber == null ? "" : this.rentalNumber) + s);
        this.infoService.event().emit("Nummer geändert.");
    }
}