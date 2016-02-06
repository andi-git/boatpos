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
import {KeyBindingService} from "./keybinding.service";

export class ModalInfoContent {
    constructor(public rentalNumber:number, public rentalService:RentalService, public keyBinding:KeyBindingService) {
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
            <p><span class="text-grey">Fahrzeit:</span> {{timeOfTravel}} Minuten</p>
            <p><span class="text-grey">Preis bevor:</span> {{pricePaidBefore}}</p>
            <p><span class="text-grey">Preis danach:</span> {{pricePaidAfter}}</p>
            <p><span class="text-grey">Preis berechnet:</span> € {{priceCalculated}}</p>
            <p><span class="text-grey">Aktion bevor:</span> {{promotionBefore}}</p>
            <p><span class="text-grey">Aktion danach:</span> {{promotionAfter}}</p>
            <p><span class="text-grey">Gelöscht:</span> {{getDeletedJaNein()}}</p>
        </div>
        <div class="modal-body" *ngIf="noRental">
            <p>{{noRental}}</p>
        </div>
        <div class="modal-footer">
            <button class="buttonSmall button-action" (click)="delete($event)" *ngIf="!getDeletedOrEmpty()">Löschen</button>
            <button class="buttonSmall button-action" (click)="undoDelete($event)" *ngIf="getDeletedOrEmpty()">Wiederherstellen</button>
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
    private priceCalculated:number;
    private promotionBefore:string;
    private promotionAfter:string;
    private commitments:string;
    private deleted:boolean;
    private timeOfTravel:number;

    constructor(dialog:ModalDialogInstance, modelContentData:ICustomModal) {
        this.dialog = dialog;
        this.content = <ModalInfoContent>modelContentData;
        let map = {
            'K': () => {
                this.cancel();
            },
            'M': () => {
                if (this.deleted === true) {
                    this.undoDelete()
                } else {
                    this.delete();
                }
            },
            'O': () => {
                this.cancel();
            }
        };
        this.content.keyBinding.addBindingForDialogInfo(map);

        this.content.rentalService.getRental(this.content.rentalNumber).subscribe((rental:Rental) => {
                this.setContentFromService(rental);
            },
            () => {
                this.noRental = "Keine Vermietung mit Nummer " + this.content.rentalNumber + " gefunden!";
            });

    }

    private setContentFromService(rental:Rental) {
        this.boatName = rental.boat.name;
        this.departure = rental.departure;
        this.arrival = rental.arrival;
        this.pricePaidBefore = rental.pricePaidBefore;
        this.pricePaidBefore = rental.pricePaidAfter;
        this.priceCalculated = rental.priceCalculatedAfter;
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
        this.deleted = rental.deleted;
        this.timeOfTravel = rental.timeOfTravel;
    }

    getDeletedOrEmpty():string {
        if (this.deleted === true) {
            return "gelöscht";
        }
        return "";
    }

    getDeletedJaNein():string {
        if (this.deleted === true) {
            return "Ja";
        }
        return "Nein";
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

    private delete($event):void {
        this.content.rentalService.deleteRental(this.content.rentalNumber).subscribe((rental:Rental) => {
            this.setContentFromService(rental);
        });
    }

    private undoDelete($event):void {
        this.content.rentalService.undoDeleteRental(this.content.rentalNumber).subscribe((rental:Rental) => {
            this.setContentFromService(rental);
        });
    }
}
