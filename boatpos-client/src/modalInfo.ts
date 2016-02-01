import {Component, provide, ElementRef, Injector} from 'angular2/core';
import {NgIf} from 'angular2/common';
//noinspection TypeScriptCheckImport
import {Modal, ModalConfig, ICustomModal, ModalDialogInstance, ICustomModalComponent} from "lib/angular2-modal";
import {RentalService} from "./rental.service";
import {Rental} from "./rental";
import {isPresent} from "angular2/src/facade/lang";
import {Boat} from "./boat";
import {PromotionBefore} from "./promotion";
import {PromotionAfter} from "./promotion";
import {Commitment} from "./commitment";

export class ModalInfoContent {
    constructor(public rentalNumber:number, public rentalService:RentalService) {
    }
}

@Component({
    selector: 'modal-content',
    directives: [NgIf],
    template: `<div class="modal-header">
        <h2 class="modal-title">Information über Nummer {{content.rentalNumber}}</h2>
        </div>
        <div class="modal-body" *ngIf="!noRental">
            <p>Boot: {{boatName}}</p>
            <p>Abfahrt: {{departure}}</p>
            <p>Ankunft: {{arrival}}</p>
            <p>Preis bevor: {{pricePaidBefore}}</p>
            <p>Preis danach: {{pricePaidAfter}}</p>
            <p>Aktion bevor: {{promotionBefore}}</p>
            <p>Aktion danach: {{promotionAfter}}</p>
            <p>Einsatz: {{commitments}}</p>
        </div>
        <div class="modal-body" *ngIf="noRental">{{noRental}}</div>
        <div class="modal-footer">
            <button class="btn btn-primary" (click)="delete($event)">Löschen</button>
            <button class="btn btn-primary" (click)="close($event)">Schließen</button>
        </div>`,
})
export class ModalDelete implements ICustomModalComponent {

    private dialog:ModalDialogInstance;
    private content:ModalInfoContent;
    private noRental:string;
    private boatName:string;
    private departure:Date;
    private arrival:Date;
    private pricePaidBefore:number;
    private pricePaidAfter:number;
    private promotionBefore:string;
    private promotionAfter:string;
    private commitments:string;

    constructor(dialog:ModalDialogInstance, modelContentData:ICustomModal) {
        this.dialog = dialog;
        this.content = <ModalInfoContent>modelContentData;
        this.content.rentalService.getRental(this.content.rentalNumber).subscribe((rental:Rental) => {
                this.boatName = rental.boat.name;
                this.departure = rental.departure;
                this.arrival = rental.arrival;
                this.pricePaidBefore = rental.pricePaidBefore;
                this.pricePaidBefore = rental.pricePaidAfter;
                if (isPresent(rental.promotionBefore)) {
                    this.promotionBefore = rental.promotionBefore.name;
                }
                if (isPresent(rental.promotionAfter)) {
                    this.promotionAfter = rental.promotionAfter.name;
                }
                this.commitments = "";
                rental.commitments.forEach((commitment) => {
                    this.commitments += commitment.name;
                    this.commitments += ", ";
                });
            },
            () => {
                this.noRental = "Keine Vermietung mit Nummer " + this.content.rentalNumber + " gefunden!";
            });

    }

    close($event) {
        $event.stopPropagation();
        //noinspection TypeScriptUnresolvedFunction
        this.dialog.close(true);
    }

    cancel() {
        //noinspection TypeScriptUnresolvedFunction
        this.dialog.dismiss();
    }
}
