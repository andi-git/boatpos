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
            <p><span class="text-grey">Einsatz:</span> {{getCommitments()}} <span [hidden]="!commitmentReturn" class="commitment-return">{{getCommitmentReturnString()}}</span></p>
            <p><span class="text-grey">Datum:</span> {{printDay()}}</p>
            <p><span class="text-grey">Abfahrt:</span> {{printDeparture()}}</p>
            <p><span class="text-grey">Ankunft:</span> {{printArrival()}}</p>
            <p><span class="text-grey">Fahrzeit:</span> {{printTimeOfTravel()}} (verrechnet: {{printTimeOfTravelCalculated()}})</p>
            <p><span class="text-grey">Aktion bevor:</span> {{printPromotionBefore()}}</p>
            <p><span class="text-grey">Preis bereits bezahlt:</span> {{printPricePaidBefore()}}</p>
            <p><span class="text-grey">Aktion danach:</span> {{printPromotionAfter()}}</p>
            <div class="container-money">
                <table>
                    <tr>
                        <td valign="top">
                            <div class="container-price-to-pay">
                                <span class="text-normal">Zu bezahlender Betrag:</span>
                                <input [class]="classInputPrice" [(ngModel)]="price" placeholder="Preis"/>
                            </div>
                        </td>
                        <td>
                            <div class="container-get-money">
                                <span class="text-small">Bezahlter Betrag:</span>
                                <input [class]="classInputGetMoney" [(ngModel)]="getMoney" placeholder="Bezahlt"/>
                            </div>
                            <div class="container-return-money">
                                <span class="text-small">Betrag retour:</span>
                                <input class="input input-return-money" [(ngModel)]="returnMoney" placeholder="Retour"/>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
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
            <button class="buttonSmall button-ok" (click)="payCash()" *ngIf="state === 'ok'">Bar</button>
            <button class="buttonSmall button-ok" (click)="payCard()" *ngIf="state === 'ok'">Karte</button>
            <button class="buttonSmall button-cancel" (click)="close($event)">Schließen</button>
        </div>`,
    styles: [`
        .input-arrival-price {
            font-size: 7em;
            font-weight: 900;
            line-height: 2em;
        }

        .input-arrival-price-selected {
            font-size: 7em;
            font-weight: 900;
            line-height: 2em;
            background-color: #81BEF7;
        }

        .input-get-money {
            font-size: 4em;
            font-weight: 200;
        }

        .input-get-money-selected {
            font-size: 4em;
            font-weight: 200;
            background-color: #81BEF7;
        }

        .input-return-money {
            font-size: 4em;
            font-weight: 200;
        }

        .container-money {
        }

        .container-price-to-pay {
            margin: 0 1em 0 0;
        }

        .container-get-money {
        }

        .container-return-money {
        }

        .text-normal {
            font-size: 2em;
        }

        .text-small {
            font-size: 1.5em;
        }

        .commitment-return {
            font-size: 1em;
            color: black;
            vertical-align: middle;
            text-align: center;
            padding: 0 0.5em 0 0.5em;
            margin: 0 0 0 0;
            background-color: white;
            border-radius: 20px 20px 20px 20px;
            border: 2px solid black;
            background-color: #ff5050;
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
    private commitmentReturn:boolean = false;

    private price:string;
    private originalPrice:string;
    private isOriginalPrice:boolean;
    private getMoney:string;
    private returnMoney:string;
    private classInputPrice:string = "input input-arrival-price-selected";
    private classInputGetMoney:string = "input input-get-money";
    private inputMethod:InputMethod = InputMethod.PriceToPay;

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
            'P': () => {
                this.payCash();
            },
            'Q': () => {
                this.payCard();
            },
            'R': () => {
                this.reset();
            },
            'S': () => {
                this.switchInputMethod();
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
                    // set price
                    this.price = this.pp.ppPrice(this.rental.priceCalculatedAfter, "");
                    this.isOriginalPrice = true;
                    this.originalPrice = this.pp.ppPrice(this.rental.priceCalculatedAfter, "");
                    // add special warning if commitment has to be returned and add the return value for special commitments
                    if (isPresent(this.rental && isPresent(this.rental.commitments))) {
                        this.rental.commitments.forEach((commitment) => {
                            // return commitment
                            if (commitment.paper === true) {
                                this.commitmentReturn = true;
                            }
                            // € 50,-
                            if (commitment.name === "EUR 50,-") {
                                this.getMoney = this.pp.ppPrice(50, "");
                                this.calculateReturnMoney();
                            }
                            // € 100,-
                            if (commitment.name === "EUR 100,-") {
                                this.getMoney = this.pp.ppPrice(100, "");
                                this.calculateReturnMoney();
                            }
                        });
                    }
                }
            },
            () => {
                this.state = "na";
            });
    }

    private addToPrice(s:string) {
        if (this.inputMethod === InputMethod.PriceToPay) {
            if (this.isOriginalPrice === true) {
                this.price = "";
                this.isOriginalPrice = false;
            }
            this.price = (this.price == null ? "" : this.price) + s;
        } else if (this.inputMethod === InputMethod.GetMoney) {
            this.getMoney = (this.getMoney == null ? "" : this.getMoney) + s;
            this.calculateReturnMoney();
        }
    }

    private calculateReturnMoney() {
            if (Number.parseFloat(this.getMoney) - Number.parseFloat(this.price) > 0) {
                this.returnMoney = this.pp.ppPrice(Number.parseFloat(this.getMoney) - Number.parseFloat(this.price), "");
            }
    }

    getBoatName():string {
        return isPresent(this.rental) ? this.rental.boat.name : "";
    }

    getCommitments():string {
        if (isPresent(this.rental)) {
            return this.pp.printCommitments(this.rental.commitments);
        }
        return "";
    }


    printDay():string {
        if (isPresent(this.rental)) {
            return this.pp.printDate(this.rental.day);
        }
    }

    printDeparture():string {
        return isPresent(this.rental) ? this.pp.printTime(this.rental.departure) : "";
    }

    printArrival():string {
        let result:string = "keine Ankunftszeit vorhanden";
        if (isPresent(this.rental) && isPresent(this.rental.arrival) && this.rental.arrival.getUTCFullYear() > 1970) {
            result = this.pp.printTime(this.rental.arrival);
        }
        return result;
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

    getCommitmentReturnString():string {
        if (this.commitmentReturn === true) {
            return "Einsatz retour!";
        } else {
            return "";
        }
    }

    private reset():void {
        this.price = this.originalPrice;
        this.isOriginalPrice = true;
        this.returnMoney = "";
        this.getMoney = "";
    }

    private payCash():void {
        this.pay(new Payment(this.rentalNumber, Number.parseFloat(this.price), "cash"));
    }

    private payCard():void {
        this.pay(new Payment(this.rentalNumber, Number.parseFloat(this.price), "card"));
    }

    private pay(payment:Payment) {
        this.rentalService.payAfter(payment).subscribe((rental:Rental) => {
                this.rental = rental;
                //noinspection TypeScriptUnresolvedFunction
                this.dialog.close("ok");
            },
            () => {
                this.cancel();
            });
    }

    private switchInputMethod() {
        if (this.inputMethod === InputMethod.PriceToPay) {
            this.inputMethod = InputMethod.GetMoney;
            this.classInputPrice = "input input-arrival-price";
            this.classInputGetMoney = "input input-get-money-selected";
        } else {
            this.inputMethod = InputMethod.PriceToPay;
            this.classInputPrice = "input input-arrival-price-selected";
            this.classInputGetMoney = "input input-get-money";
        }
    }
}

enum InputMethod {PriceToPay, GetMoney}