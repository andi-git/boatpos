import {Component} from "angular2/core";
import {Boat} from "../../model/boat";
import {BoatService} from "../../service/boat.service";
import {InfoService} from "../../service/info.service";
import {CommitmentService} from "../../service/commitment.service";
import {PromotionService} from "../../service/promotion.service";
import {Commitment} from "../../model/commitment";
import {PromotionBefore} from "../../model/promotion";
import {Departure} from "../../model/departure";
import {RentalService} from "../../service/rental.service";
//noinspection TypeScriptCheckImport
import {Modal, ModalConfig, ICustomModal, ModalDialogInstance} from "lib/angular2-modal";
import {ModalInfoContext, ModalDelete} from "./modalInfo";
import {isPresent} from "angular2/src/facade/lang";
import {KeyBindingService} from "../../service/keybinding.service";
import {ModalHandler} from "../../modalHandler";
import {ModalDeletedContext, ModalDeleted} from "./modalDeleted";
import {PrettyPrinter} from "../../prettyprinter";
import {ModalPromotionPay, ModalPromotionPayContext} from "./modalPromotionPay";
import {ModalArrival, ModalArrivalContext} from "./modalArrival";
import {ConfigService} from "../../service/config.service";
import {Printer} from "../../printer";
import {JournalService} from "../../service/journal.service";
import {ErrorService} from "../../service/error.service";

@Component({
    selector: 'action',
    templateUrl: "html/component/rental/action.component.html",
    styleUrls: ["css/component/rental/action.component.css"],
})
export class ActionComponent {

    private rentalNumber:number;

    private lastModalResult:string;

    constructor(private boatService:BoatService,
                private commitmentService:CommitmentService,
                private promotionService:PromotionService,
                private infoService:InfoService,
                private errorService:ErrorService,
                private rentalService:RentalService,
                private journalService:JournalService,
                private keyBinding:KeyBindingService,
                private modalHandler:ModalHandler,
                private pp:PrettyPrinter,
                private printer:Printer,
                private config:ConfigService) {
        let map:{[key:string]:((e:ExtendedKeyboardEvent, combo:string) => any)} = {
            'K': () => {
                this.cancel();
            },
            'L': () => {
                this.departBySelected();
            },
            'M': () => {
                this.deleteRental();
            },
            'N': () => {
                this.info();
            },
            'O': () => {
                this.arrive();
            },
            'T': () => {
                this.depart(this.boatService.getBoatByShortName('E'), [this.commitmentService.getCommitmentByName('Ausweis')], null);
            },
            'U': () => {
                this.depart(this.boatService.getBoatByShortName('T2'), [this.commitmentService.getCommitmentByName('Ausweis')], null);
            },
            'V': () => {
                this.depart(this.boatService.getBoatByShortName('T4'), [this.commitmentService.getCommitmentByName('Ausweis')], null);
            },
            'W': () => {
                this.journalService.incomeCurrentDay().subscribe((journalReport) => this.printer.printJournal(journalReport, this.config.getPrinterIp()));
            }
        };
        for (let i = 0; i <= 9; i++) {
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

    private depart(boat:Boat, commitments:Array<Commitment>, promotionBefore:PromotionBefore) {
        if (isPresent(boat)) {
            this.rentalService.depart(new Departure(boat, commitments, promotionBefore)).subscribe(
                (rental) => {
                    // check if a promotion is selected or not
                    if (isPresent(rental.priceCalculatedBefore) && rental.priceCalculatedBefore > 0) {
                        this.modalHandler.open(ModalPromotionPay, new ModalPromotionPayContext(rental, this.rentalService, this.pp, this.keyBinding)).then((resultPromise) => {
                            //noinspection TypeScriptUnresolvedVariable
                            return resultPromise.result.then((result) => {
                                rental = result;
                                this.boatService.updateStats();
                                this.resetUi();
                                this.keyBinding.focusMain();
                                if (isPresent(rental.pricePaidBefore) && rental.pricePaidBefore > 0) {
                                    this.printer.printDepart(rental, this.config.getPrinterIp());
                                    this.infoService.event().emit("Nr " + rental.dayId + " " + rental.boat.name + " " + this.createStringForCommitments(rental.commitments) + this.createStringForPromotion(rental.promotionBefore) + " wurde vermietet.");
                                } else {
                                    this.infoService.event().emit("Vermietung mit Nummer " + rental.dayId + " wurde abgebrochen (gelöscht).");
                                }
                            }, () => {
                                this.lastModalResult = 'Rejected!';
                                this.boatService.updateStats();
                                this.resetUi();
                                this.keyBinding.focusMain();
                                this.errorService.event().emit("Vermietung abgebrochen, Aktion wurde nicht bezahlt.");
                            });
                        });
                    } else {
                        this.printer.printDepart(rental, this.config.getPrinterIp());
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

    private departBySelected() {
        this.depart(this.boatService.getSelectedBoat(), this.commitmentService.getSelectedCommitmens(), this.promotionService.getSelectedPromotionsBefore());
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
                    let deletedInfo = "Keine Vermietung mit Nummer  " + this.rentalNumber + " gefunden.";
                    this.showDialogDeleted(deletedInfo);
                    this.boatService.updateStats();
                    this.resetUi();
                    this.errorService.event().emit(deletedInfo);
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

    //noinspection JSMethodCanBeStatic
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
            this.modalHandler.open(ModalArrival, new ModalArrivalContext(this.rentalNumber, this.rentalService, this.promotionService, this.keyBinding, this.printer, this.pp, this.config.getPrinterIp())).then((resultPromise) => {
                //noinspection TypeScriptUnresolvedVariable
                return resultPromise.result.then((result) => {
                    this.lastModalResult = result;
                    this.infoService.event().emit("Vermietung mit Nummer " + this.rentalNumber + " ist abgeschlossen.");
                    this.resetUi();
                    this.keyBinding.focusMain();
                }, () => {
                    this.lastModalResult = 'Rejected!';
                    this.errorService.event().emit("Abrechnung der Nummer " + this.rentalNumber + " wurde abgebrochen.");
                    this.resetUi();
                    this.keyBinding.focusMain();
                });
            });
        }
    }
}
