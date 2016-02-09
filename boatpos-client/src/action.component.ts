import {Component, Inject, Injector, Renderer, ElementRef, KeyValueDiffers, IterableDiffers, provide, NgZone} from 'angular2/core';
import {Boat} from './boat';
import {BoatService} from "./boat.service";
import {InfoService} from "./info.service";
import {CommitmentService} from "./commitment.service";
import {PromotionService} from "./promotion.service";
import {Commitment} from "./commitment";
import {PromotionBefore} from "./promotion";
import {Departure} from "./departure";
import {RentalService} from "./rental.service";
import {Rental} from "./rental";
//noinspection TypeScriptCheckImport
import {Modal, ModalConfig, ICustomModal, ModalDialogInstance} from "lib/angular2-modal";
import {ModalInfoContext, ModalDelete} from "./modalInfo";
import {isPresent} from "angular2/src/facade/lang";
import {KeyBindingService} from "./keybinding.service";
import {Injectable} from "angular2/core";
import {ModalHandler} from "./modalHandler";
import {ModalDeletedContext} from "./modalDeleted";
import {ModalDeleted} from "./modalDeleted";
import {PrettyPrinter} from "./prettyprinter";
import {ModalPromotionPay} from "./modalPromotionPay";
import {ModalPromotionPayContext} from "./modalPromotionPay";
import {ModalArrival} from "./modalArrival";
import {ModalArrivalContext} from "./modalArrival";

@Component({
    selector: 'action',
    templateUrl: "action.component.html",
    styleUrls: ["action.component.css"],
})
export class ActionComponent {

    private rentalNumber:number;

    private lastModalResult:string;

    constructor(private boatService:BoatService,
                private commitmentService:CommitmentService,
                private promotionService:PromotionService,
                private infoService:InfoService,
                private rentalService:RentalService,
                private keyBinding:KeyBindingService,
                private modalHandler:ModalHandler,
                private pp:PrettyPrinter) {
        let map:{[key: string] : ((e:ExtendedKeyboardEvent, combo:string) => any)} = {
            'K': () => {
                this.cancel();
            },
            'L': () => {
                this.depart();
            },
            'M': () => {
                this.deleteRental();
            },
            'N': () => {
                this.info();
            },
            'O': () => {
                this.arrive();
            }
        };
        for (var i = 0; i <= 9; i++) {
            map[i] = (e) => {
                this.addToNumber(String.fromCharCode(e.charCode));
            };
        }
        this.keyBinding.addBindingForMain(map);
    }

    private cancel() {
        this.rentalNumber = null;
        this.resetUi();
        this.infoService.event().emit("Aktion abgebrochen, Elemente wieder zurückgesetzt.");
    }

    private resetUi() {
        this.rentalNumber = null;
        this.boatService.reset();
        this.commitmentService.resetSelected();
        this.promotionService.resetSelected();
    }

    private depart() {
        let boat:Boat = this.boatService.getSelectedBoat();
        let commitments:Array<Commitment> = this.commitmentService.getSelectedCommitmens();
        let promotionBefore:PromotionBefore = this.promotionService.getSelectedPromotionsBefore();
        if (boat != null) {
            this.rentalService.depart(new Departure(boat, commitments, promotionBefore)).subscribe(
                (rental) => {
                    // check if a promotion is selected or not
                    if (isPresent(rental.priceCalculatedBefore) && rental.priceCalculatedBefore > 0) {
                        this.modalHandler.open(ModalPromotionPay, new ModalPromotionPayContext(rental, this.rentalService, this.pp, this.keyBinding)).then((resultPromise) => {
                            //noinspection TypeScriptUnresolvedVariable
                            return resultPromise.result.then((result) => {
                                this.lastModalResult = result;
                                this.boatService.updateStats();
                                this.resetUi();
                                this.keyBinding.focusMain();
                                if (this.lastModalResult === "paid") {
                                    this.infoService.event().emit("Nr " + rental.dayId + " " + rental.boat.name + " " + this.createStringForCommitments(rental.commitments) + this.createStringForPromotion(rental.promotionBefore) + " wurde vermietet.");
                                } else {
                                    this.infoService.event().emit("Vermietung mit Nummer " + rental.dayId + " wurde abgebrochen (gelöscht).");
                                }
                            }, () => {
                                console.log("--> error");
                                this.lastModalResult = 'Rejected!';
                                this.boatService.updateStats();
                                this.resetUi();
                                this.keyBinding.focusMain();
                                this.infoService.event().emit("Vermietung abgebrochen, Aktion wurde nicht bezahlt.");
                            });
                        });
                    } else {
                        this.infoService.event().emit("Nr " + rental.dayId + " " + rental.boat.name + " " + this.createStringForCommitments(rental.commitments) + this.createStringForPromotion(rental.promotionBefore) + " wurde vermietet.");
                        this.boatService.updateStats();
                        this.resetUi();
                    }
                }
            );
        } else {
            this.infoService.event().emit("Vermietung nicht möglich: es wurde kein Boot augewählt.");
        }
    }

    private deleteRental() {
        if (this.rentalNumber != null) {
            this.rentalService.deleteRental(this.rentalNumber).subscribe(
                (rental) => {
                    let deletedInfo:string = "Vermietung mit Nummer " + this.rentalNumber + " wurde gelöscht.";
                    this.showDialogDeleted(deletedInfo);
                    this.boatService.updateStats();
                    this.resetUi();
                    this.infoService.event().emit(deletedInfo);
                },
                () => {
                    var deletedInfo = "Keine Vermietung mit Nummer  " + this.rentalNumber + " gefunden.";
                    this.showDialogDeleted(deletedInfo);
                    this.boatService.updateStats();
                    this.resetUi();
                    this.infoService.event().emit(deletedInfo);
                }
            );
        } else {
            this.infoService.event().emit("Löschen nicht möglich: keine Nummer eingegeben.");
        }
    }

    private showDialogDeleted(deletedInfo:string) {
        this.modalHandler.open(ModalDeleted, new ModalDeletedContext(deletedInfo, this.keyBinding)).then((resultPromise) => {
            //noinspection TypeScriptUnresolvedVariable
            return resultPromise.result.then((result) => {
                this.lastModalResult = result;
                this.resetUi();
                this.keyBinding.focusMain();
            }, () => {
                this.lastModalResult = 'Rejected!';
                this.resetUi();
                this.keyBinding.focusMain();
            });
        });
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
        this.infoService.event().emit("Nummer eingegeben.");
    }

    private info() {
        if (!isPresent(this.rentalNumber)) {
            this.infoService.event().emit("Information anzeigen nicht möglich: keine Nummer eingegeben.")
        } else {
            this.infoService.event().emit("Information über Nummer " + this.rentalNumber + " wird angezeigt.");
            this.modalHandler.open(ModalDelete, new ModalInfoContext(this.rentalNumber, this.rentalService, this.keyBinding, this.pp)).then((resultPromise) => {
                //noinspection TypeScriptUnresolvedVariable
                return resultPromise.result.then((result) => {
                    this.lastModalResult = result;
                    this.resetUi();
                    this.keyBinding.focusMain();
                }, () => {
                    this.lastModalResult = 'Rejected!';
                    this.resetUi();
                    this.keyBinding.focusMain();
                });
            });
        }
    }

    private arrive() {
        if (!isPresent(this.rentalNumber)) {
            this.infoService.event().emit("Verrechnung nicht möglich: keine Nummer eingegeben.")
        } else {
            this.infoService.event().emit("Verrechnung der Nummer " + this.rentalNumber + ".");
            this.modalHandler.open(ModalArrival, new ModalArrivalContext(this.rentalNumber, this.rentalService, this.keyBinding, this.pp)).then((resultPromise) => {
                //noinspection TypeScriptUnresolvedVariable
                return resultPromise.result.then((result) => {
                    this.lastModalResult = result;
                    this.infoService.event().emit("Vermietung mit Nummer " + this.rentalNumber + " ist abgeschlossen.");
                    this.resetUi();
                    this.keyBinding.focusMain();
                }, () => {
                    this.lastModalResult = 'Rejected!';
                    this.infoService.event().emit("Abrechnung der Nummer " + this.rentalNumber + " wurde abgebrochen.");
                    this.resetUi();
                    this.keyBinding.focusMain();
                });
            });
        }
    }
}
