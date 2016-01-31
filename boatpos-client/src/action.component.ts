import {Component, Inject, Injector, Renderer, ElementRef} from 'angular2/core';
import {Boat} from './boat';
import {BoatService} from "./boat.service";
import {InfoService} from "./info.service";
import {NgZone} from "angular2/core";
import {CommitmentService} from "./commitment.service";
import {PromotionService} from "./promotion.service";
import {Commitment} from "./commitment";
import {PromotionBefore} from "./promotion";
import {Departure} from "./departure";
import {RentalService} from "./rental.service";
import {Rental} from "./rental";
//noinspection TypeScriptCheckImport
import {Modal, ModalConfig, ICustomModal, YesNoModalContent, ModalDialogInstance, YesNoModal} from "lib/angular2-modal";
import {provide} from "angular2/core";
import {KeyValueDiffers} from "angular2/core";
import {IterableDiffers} from "angular2/core";

@Component({
    selector: 'action',
    templateUrl: "action.component.html",
    styleUrls: ["action.component.css"],
})
export class ActionComponent {

    private rentalNumber:number;

    private modalConfig:ModalConfig;

    private modalData:ICustomModal;

    private lastModalResult: string;

    constructor(private boatService:BoatService,
                private commitmentService:CommitmentService,
                private promotionService:PromotionService,
                private infoService:InfoService,
                private rentalService:RentalService,
                private modal:Modal,
                private elementRef:ElementRef,
                //private injector:Injector,
                private _renderer:Renderer) {
        new Mousetrap().bind(['K'], () => {
            this.cancel();
        });
        new Mousetrap().bind(['L'], () => {
            this.depart();
        });
        new Mousetrap().bind(['M'], () => {
            this.deleteRental();
        });
        new Mousetrap().bind(['N'], () => {
            this.info();
        });
        new Mousetrap().bind(['1', '2', '3', '4', '5', '6', '7', '8', '9', '0'], (e) => {
            this.addToNumber(String.fromCharCode(e.charCode));
        });
        this.modalConfig = new ModalConfig("lg", false, 27);
        this.modalData = new YesNoModalContent('Simple Large modal', 'Press ESC or click OK / outside area to close.', true);
    }

    private cancel() {
        this.rentalNumber = null;
        this.resetUi();
        this.infoService.event().emit("Aktion abgebrochen, Elemente wieder zurückgesetzt.");
    }

    private resetUi() {
        this.rentalNumber = null;
        this.boatService.resetSelected();
        this.commitmentService.resetSelected();
        this.promotionService.resetSelected();
    }

    private depart() {
        let boat:Boat = this.boatService.getSelectedBoat();
        let commitments:Array<Commitment> = this.commitmentService.getSelectedCommitmens();
        let promotionBefore:PromotionBefore = this.promotionService.getSelectedPromotionsBefore();
        if (boat != null) {
            this.rentalService.departe(new Departure(boat, commitments, promotionBefore)).subscribe(
                (rental) => {
                    this.infoService.event().emit("Nr " + rental.dayId + " " + rental.boat.name + " " + this.createStringForCommitments(rental.commitments) + this.createStringForPromotion(rental.promotionBefore) + " wurde vermietet.");
                    this.boatService.updateStats();
                    this.resetUi();
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
                    this.boatService.updateStats();
                    this.infoService.event().emit("Nummer " + this.rentalNumber + " wurde gelöscht.");
                    this.resetUi();
                }
            );
        } else {
            this.infoService.event().emit("Löschen nicht möglich: es wurde keine Nummer eingegeben.");
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

    private info() {
        console.log("info");
        let dialog:  Promise<ModalDialogInstance>;
        let component = YesNoModal;
        let bindings = Injector.resolve([
            provide(ICustomModal, {useValue: this.modalData}),
            //provide(IterableDiffers, {useValue: this.injector.get(IterableDiffers)}),
            //provide(KeyValueDiffers, {useValue: this.injector.get(KeyValueDiffers)}),
            provide(Renderer, {useValue: this._renderer})
        ]);

        //noinspection TypeScriptUnresolvedFunction
        dialog = this.modal.open(
            <any>component,
            bindings,
            this.modalConfig);

        dialog.then((resultPromise) => {
            return resultPromise.result.then((result) => {
                this.lastModalResult = result;
            }, () => {
                this.lastModalResult = 'Rejected!'
                console.log(this.lastModalResult)
            });
        });
    }
}