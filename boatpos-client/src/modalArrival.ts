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
import {Payment} from "./payment";

export class ModalArrivalContext {
    constructor(public rentalNumber:number, public rentalService:RentalService, public keyBinding:KeyBindingService, public pp:PrettyPrinter) {
    }
}

@Component({
    selector: 'modal-content',
    directives: [NgIf],
    template: `<div class="modal-header">
        <h2 class="header header-main">Verrechnung Nummer {{rentalNumber}}</h2>
        </div>
        <div class="modal-body" *ngIf="state === 'ok'">
            <p><span class="text-grey">Boot:</span> {{getBoatName()}}</p>
            <p><span class="text-grey">Einsatz:</span> {{getCommitments()}}</p>
            <p><span class="text-grey">Abfahrt:</span> {{printDeparture()}}</p>
            <p><span class="text-grey">Ankunft:</span> {{printArrival()}}</p>
            <p><span class="text-grey">Fahrzeit:</span> {{printTimeOfTravel()}} (verrechnet: {{printTimeOfTravelCalculated()}})</p>
            <p><span class="text-grey">Aktion bevor:</span> {{printPromotionBefore()}}</p>
            <p><span class="text-grey">Preis bereits bezahlt:</span> {{printPricePaidBefore()}}</p>
            <p><span class="text-grey">Aktion danach:</span> {{printPromotionAfter()}}</p>
            <p><span class="text-grey">Zu bezahlender Betrag:</span></p>
            <input class="input input-arrival-price" [(ngModel)]="price" placeholder="Preis"/>
        </div>
        <div class="modal-body" *ngIf="state === 'na'">
            <p>Keine Vermietung mit Nummer {{rentalNumber}} vorhanden!</p>
        </div>
        <div class="modal-body" *ngIf="state === 'del'">
            <p>Keine Vermietung mit Nummer {{rentalNumber}} vorhanden!</p>
        </div>
        <div class="modal-body" *ngIf="state === 'alf'">
            <p>Vermietung mit Nummer {{rentalNumber}} wurde bereits abgerechnet!</p>
        </div>
        <div class="modal-footer">
            <button class="buttonSmall button-action" (click)="reset()" *ngIf="state === 'ok'">Zurücksetzen</button>
            <button class="buttonSmall button-ok" (click)="pay()" *ngIf="state === 'ok'">Bezahlen</button>
            <button class="buttonSmall button-cancel" (click)="close($event)">Schließen</button>
        </div>`,
    styles: [`
        .input-arrival-price {
            font-size: 7em;
            font-weight: 900;
        }
        `],
})
export class ModalArrival implements ICustomModalComponent {

    private dialog:ModalDialogInstance;
    private keyBinding:KeyBindingService;
    private rentalService:RentalService;
    private pp:PrettyPrinter;
    private rentalNumber:number;
    private state:string;
    private rental:Rental;
    private price:string;
    private originalPrice:string;
    private isOriginalPrice:boolean;

    constructor(dialog:ModalDialogInstance, modelContentData:ICustomModal) {
        this.dialog = dialog;
        this.keyBinding = (<ModalArrivalContext>modelContentData).keyBinding;
        this.rentalService = (<ModalArrivalContext>modelContentData).rentalService;
        this.rentalNumber = (<ModalArrivalContext>modelContentData).rentalNumber;
        this.pp = (<ModalArrivalContext>modelContentData).pp;
        let map:{[key: string] : ((e:ExtendedKeyboardEvent, combo:string) => any)} = {
            'K': () => {
                this.cancel();
            },
            'M': () => {
                this.reset();
            },
            'O': () => {
                this.pay();
            },
            '.': () => {
                this.addToPrice('.');
            }
        };
        for (var i = 0; i <= 9; i++) {
            map[i] = (e) => {
                this.addToPrice(String.fromCharCode(e.charCode));
            };
        }
        this.keyBinding.addBindingForDialogInfo(map);
        this.keyBinding.focusDialogInfo();
        this.arrive();
    }

    private arrive() {
        this.rentalService.arrive(this.rentalNumber).subscribe((rental:Rental) => {
                this.rental = <Rental>rental;
                if (this.rental.deleted === true) {
                    this.state = "del";
                } else if (this.rental.finished === true) {
                    this.state = "alf";
                } else {
                    this.state = "ok";
                    this.price = this.pp.ppPrice(this.rental.priceCalculatedAfter, "");
                    this.isOriginalPrice = true;
                    this.originalPrice = this.pp.ppPrice(this.rental.priceCalculatedAfter, "");
                }
            },
            () => {
                this.state = "na";
            });
    }

    private addToPrice(s:string) {
        if (this.isOriginalPrice === true) {
            this.price = "";
            this.isOriginalPrice = false;
        }
        this.price = (this.price == null ? "" : this.price) + s;
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

    printPricePaidBefore():string {
        return isPresent(this.rental) ? this.pp.ppPrice(this.rental.pricePaidBefore, "") : "";
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
            result = this.rental.promotionAfter.name;
        }
        return result;
    }

    printTimeOfTravel():string {
        return (isPresent(this.rental)) ? this.rental.timeOfTravel + " Minuten" : "";
    }

    printTimeOfTravelCalculated():string {
        return (isPresent(this.rental)) ? this.rental.timeOfTravelCalculated + " Minuten" : "";
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

    private reset():void {
        this.price = this.originalPrice;
        this.isOriginalPrice = true;
    }

    private pay():void {
        let payment:Payment = new Payment(this.rentalNumber, Number.parseFloat(this.price));
        this.rentalService.payAfter(payment).subscribe((rental:Rental) => {
                this.rental = rental;
                //noinspection TypeScriptUnresolvedFunction
                this.dialog.close("ok");
            },
            () => {
                this.cancel();
            });
    }
}
