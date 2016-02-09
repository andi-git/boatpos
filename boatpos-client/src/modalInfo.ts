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
import {PrettyPrinter} from "./prettyprinter";

export class ModalInfoContext {
    constructor(public rentalNumber:number, public rentalService:RentalService, public keyBinding:KeyBindingService, public pp:PrettyPrinter) {
    }
}

@Component({
    selector: 'modal-content',
    directives: [NgIf],
    template: `<div class="modal-header">
        <h2 class="header header-main">Information über Nummer {{rentalNumber}}</h2>
        </div>
        <div class="modal-body" *ngIf="!noRental">
            <p><span class="text-grey">Boot:</span> {{getBoatName()}}</p>
            <p><span class="text-grey">Einsatz:</span> {{getCommitments()}}</p>
            <p><span class="text-grey">Abfahrt:</span> {{printDeparture()}}</p>
            <p><span class="text-grey">Ankunft:</span> {{printArrival()}}</p>
            <p><span class="text-grey">Fahrzeit:</span> {{timeOfTravel}} Minuten</p>
            <p><span class="text-grey">Preis bezahlt:</span> {{printPricePaid()}}</p>
            <p><span class="text-grey">Preis bevor:</span> {{printPricePaidBefore()}}</p>
            <p><span class="text-grey">Preis danach:</span> {{printPricePaidAfter()}}</p>
            <p><span class="text-grey">Preis berechnet bevor:</span> {{printPriceCalculatedBefore()}}</p>
            <p><span class="text-grey">Preis berechnet danach:</span> {{printPriceCalculatedAfter()}}</p>
            <p><span class="text-grey">Aktion bevor:</span> {{printPromotionBefore()}}</p>
            <p><span class="text-grey">Aktion danach:</span> {{printPromotionAfter()}}</p>
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
    private keyBinding:KeyBindingService;
    private rentalService:RentalService;
    private pp:PrettyPrinter;
    private rentalNumber:number;
    private noRental:string;
    private rental:Rental;

    constructor(dialog:ModalDialogInstance, modelContentData:ICustomModal) {
        this.dialog = dialog;
        this.keyBinding = (<ModalInfoContext>modelContentData).keyBinding;
        this.rentalService = (<ModalInfoContext>modelContentData).rentalService;
        this.rentalNumber = (<ModalInfoContext>modelContentData).rentalNumber;
        this.pp = (<ModalInfoContext>modelContentData).pp;
        let map:{[key: string] : ((e:ExtendedKeyboardEvent, combo:string) => any)} = {
            'K': () => {
                this.cancel();
            },
            'M': () => {
                if (isPresent(this.rental) && this.rental.deleted === true) {
                    this.undoDelete()
                } else {
                    this.delete();
                }
            },
            'O': () => {
                this.cancel();
            }
        };
        this.keyBinding.addBindingForDialogInfo(map);
        this.keyBinding.focusDialogInfo();

        this.rentalService.getRental(this.rentalNumber).subscribe((rental:Rental) => {
                this.rental = <Rental>rental;
            },
            () => {
                this.noRental = "Keine Vermietung mit Nummer " + this.rentalNumber + " gefunden!";
            });

    }

    getBoatName():string {
        return isPresent(this.rental) ? this.rental.boat.name : "";
    }

    getCommitments():string {
        let commitments:string = "";
        if (isPresent(this.rental)) {
            let first:boolean = true;
            this.rental.commitments.forEach((commitment) => {
                if (!first) {
                    commitments += ",";
                }
                commitments += commitment.name;
                first = false;
            });
        }
        return commitments;
    }

    getDeletedOrEmpty():string {
        return (isPresent(this.rental) && this.rental.deleted === true) ? "gelöscht" : "";
    }

    getDeletedJaNein():string {
        return (isPresent(this.rental) && this.rental.deleted === true) ? "Ja" : "Nein";
    }

    printDeparture():string {
        return isPresent(this.rental) ? this.printDate(this.rental.departure) : "";
    }

    printArrival():string {
        let result:string = "keine Ankunftszeit vorhanden";
        if (isPresent(this.rental) && isPresent(this.rental.arrival) && this.rental.arrival.getUTCFullYear() > 1970) {
            result = this.printDate(this.rental.arrival);
        }
        return result;
    }

    printDate(date:Date):string {
        let dateString:string = "";
        if (isPresent(date) && date.getUTCFullYear() > 1970) {
            return this.pp.pp2Pos(date.getUTCHours()) + ":" + this.pp.pp2Pos(date.getUTCMinutes()) + " Uhr";
        }
        return dateString;
    }

    printPricePaid():string {
        if (isPresent(this.rental)) {
            let summandA:number = 0;
            if (!isNaN(this.rental.pricePaidBefore)) {
                summandA = this.rental.pricePaidBefore;
            }
            let summandB:number = 0;
            if (!isNaN(this.rental.pricePaidAfter)) {
                summandB = this.rental.pricePaidAfter;
            }
            return this.pp.ppPrice(summandA + summandB);
        } else {
            return "";
        }
    }

    printPriceCalculatedBefore():string {
        return isPresent(this.rental) ? this.pp.ppPrice(this.rental.priceCalculatedBefore) : "";
    }

    printPriceCalculatedAfter():string {
        return isPresent(this.rental) ? this.pp.ppPrice(this.rental.priceCalculatedAfter) : "";
    }

    printPricePaidBefore():string {
        return isPresent(this.rental) ? this.pp.ppPrice(this.rental.pricePaidBefore) : "";
    }

    printPricePaidAfter():string {
        return isPresent(this.rental) ? this.pp.ppPrice(this.rental.pricePaidAfter) : "";
    }

    printPromotionBefore():string {
        let result:string = "keine Aktion vorhanden";
        if (isPresent(this.rental) && isPresent(this.rental.promotionBefore)) {
            result = this.rental.promotionBefore.name + " (Guthaben: " + this.rental.promotionBefore.timeCredit + " Minuten)";
        }
        return result;
    }

    printPromotionAfter():string {
        let result:string = "keine Aktion vorhanden";
        if (isPresent(this.rental) && isPresent(this.rental.promotionAfter)) {
            result = this.rental.promotionAfter;
        }
        return result;
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
        this.rentalService.deleteRental(this.rentalNumber).subscribe((rental:Rental) => {
            this.rental = rental;
        });
    }

    private undoDelete($event):void {
        this.rentalService.undoDeleteRental(this.rentalNumber).subscribe((rental:Rental) => {
            this.rental = rental;
        });
    }
}
