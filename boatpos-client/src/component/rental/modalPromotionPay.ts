import {Component, provide, ElementRef, Injector} from 'angular2/core';
import {NgIf} from 'angular2/common';
//noinspection TypeScriptCheckImport
import {Modal, ModalConfig, ICustomModal, ModalDialogInstance, ICustomModalComponent} from "lib/angular2-modal";
import {KeyBindingService} from "service/keybinding.service";
import {Rental} from "model/rental";
import {RentalService} from "service/rental.service";
import {PrettyPrinter} from "prettyprinter";
import {Payment} from "../../model/payment";

export class ModalPromotionPayContext {
    constructor(public rental:Rental, public rentalService:RentalService, public pp:PrettyPrinter, public keyBinding:KeyBindingService) {
    }
}

@Component({
    selector: 'modal-content',
    directives: [NgIf],
    template: `<div class="modal-header">
        <h2 class="header header-main">Aktion '{{getPromotionBefore()}}' bezahlen!</h2>
        </div>
        <div class="modal-body">
            <p><span class="text-grey">Preis für</span> {{getPromotionBefore()}} <span class="text-grey">von</span> {{getPriceCalculatedBefore()}} <span class="text-grey">erhalten?</span></p>
        <div class="modal-footer">
            <button class="buttonSmall button-cancel" (click)="deleteRental()">Löschen</button>
            <button class="buttonSmall button-ok" (click)="payCash()">Bar</button>
            <button class="buttonSmall button-ok" (click)="payCard()">Karte</button>
        </div>`,
})
export class ModalPromotionPay implements ICustomModalComponent {

    private dialog:ModalDialogInstance;
    private rental:Rental;
    private rentalService:RentalService;
    private pp:PrettyPrinter;
    private keyBinding:KeyBindingService;
    public error:string;

    constructor(dialog:ModalDialogInstance, context:ICustomModal) {
        this.error = null;
        this.dialog = dialog;
        this.rental = (<ModalPromotionPayContext>context).rental;
        this.rentalService = (<ModalPromotionPayContext>context).rentalService;
        this.pp = (<ModalPromotionPayContext>context).pp;
        this.keyBinding = (<ModalPromotionPayContext>context).keyBinding;
        let map:{[key: string] : ((e:ExtendedKeyboardEvent, combo:string) => any)} = {
            'P': () => {
                this.payCash();
            },
            'Q': () => {
                this.payCard();
            },
            'K': () => {
                this.deleteRental();
            }
        };
        this.keyBinding.addBindingForDialogPromotionPay(map);
        this.keyBinding.focusDialogPromotionPay();
    }

    deleteRental() {
        this.rentalService.deleteRental(this.rental.dayId).subscribe((rental:Rental) => {
                this.rental = rental;
                this.closeOk();
            },
            () => {
                this.error = "Fehler beim Löschen von Vermietung mit Nummer " + this.rental.dayId + "!";
                this.cancel();
            });
    }

    private payCash():void {
        this.pay(new Payment(this.rental.dayId, this.rental.priceCalculatedBefore, "cash"));
    }

    private payCard():void {
        this.pay(new Payment(this.rental.dayId, this.rental.priceCalculatedBefore, "card"));
    }

    private pay(payment:Payment) {
        this.rentalService.payBefore(payment).subscribe((rental:Rental) => {
                this.rental = rental;
                this.closeOk(rental);
            },
            () => {
                this.error = "Fehler beim Zahlen der Aktion von Vermietung mit Nummer " + this.rental.dayId + "!";
                this.cancel();
            });
    }

    getPriceCalculatedBefore():string {
        return this.pp.ppPrice(this.rental.priceCalculatedBefore, "€ ");
    }

    getPromotionBefore():string {
        return this.rental.promotionBefore.name;
    }

    close($event) {
        $event.stopPropagation();
        //noinspection TypeScriptUnresolvedFunction
        this.dialog.close(true);
    }

    closeOk(result:any) {
        //noinspection TypeScriptUnresolvedFunction
        this.dialog.close(result);
    }

    cancel() {
        //noinspection TypeScriptUnresolvedFunction
        this.dialog.dismiss();
    }
}