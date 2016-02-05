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
        <h2 class="header header-main">Information über Nummer {{content.rentalNumber}}</h2>
        </div>
        <div class="modal-body" *ngIf="!noRental">
            <p><span class="text-grey">Boot:</span> {{boatName}}</p>
            <p><span class="text-grey">Einsatz:</span> {{commitments}}</p>
            <p><span class="text-grey">Abfahrt:</span> {{printDeparture()}}</p>
            <p><span class="text-grey">Ankunft:</span> {{printArrival()}}</p>
            <p><span class="text-grey">Preis bevor:</span> {{pricePaidBefore}}</p>
            <p><span class="text-grey">Preis danach:</span> {{pricePaidAfter}}</p>
            <p><span class="text-grey">Aktion bevor:</span> {{promotionBefore}}</p>
            <p><span class="text-grey">Aktion danach:</span> {{promotionAfter}}</p>
        </div>
        <div class="modal-body" *ngIf="noRental">
            <p>{{noRental}}</p>
        </div>
        <div class="modal-footer">
            <button class="buttonSmall button-action" (click)="delete($event)">Löschen</button>
            <button class="buttonSmall button-ok" (click)="close($event)">Schließen</button>
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
                let first:boolean = true;
                this.commitments = "";
                rental.commitments.forEach((commitment) => {
                    if (!first) {
                        this.commitments += ",";
                        first = false;
                    }
                    this.commitments += commitment.name;
                });
            },
            () => {
                this.noRental = "Keine Vermietung mit Nummer " + this.content.rentalNumber + " gefunden!";
            });

    }

    printDeparture():string {
        return this.printDate(this.departure);
    }

    printArrival():string {
        return this.printDate(this.arrival);
    }

    printDate(date:Date):string {
        let dateString:string = "";
        if (isPresent(date) && date.getUTCFullYear() > 1970) {
            return date.getUTCHours() + ":" + date.getUTCMinutes() + " Uhr";
        }
        return dateString;
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
