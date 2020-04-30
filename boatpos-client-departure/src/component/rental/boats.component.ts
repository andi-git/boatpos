import {Component, NgZone} from 'angular2/core';
import {Boat, BoatCount} from '../../model/boat';
import {BoatService} from "../../service/boat.service";
import {InfoService} from "../../service/info.service";
import {KeyBindingService} from "../../service/keybinding.service";
import {Commitment} from "../../model/commitment";
import {PromotionBefore} from "../../model/promotion";
import {Departure} from "../../model/departure";
import {Printer} from "../../printer";
import {RentalService} from "../../service/rental.service";
import {ConfigService} from "../../service/config.service";
import {CommitmentService} from "../../service/commitment.service";

@Component({
    selector: 'boats',
    templateUrl: "html/component/rental/boats.component.html",
    styleUrls: ["css/component/rental/boats.component.css"]
})
export class BoatsComponent {

    constructor(private boatService: BoatService,
                private rentalService: RentalService,
                private commitmentService: CommitmentService,
                private infoService: InfoService,
                private printer: Printer,
                private keyBinding: KeyBindingService,
                private zone: NgZone,
                private config: ConfigService) {
    }

    ngOnInit() {
    }

    getBoats(): Array<Boat> {
        return this.boatService.getBoats();
    }

    click(boatCount: BoatCount) {
        console.info("depart boat " + boatCount.shortName)
        // this.depart(this.boatService.getBoatByShortName(boatShort), [], null);
    }

    clickWithIdentityCard(boatCount: BoatCount) {
        console.info("depart boat with idCard " + boatCount.shortName)
        // this.depart(this.boatService.getBoatByShortName(boatShort), [this.commitmentService.getCommitmentByName('Ausweis')], null);
    }

    // private depart(boat: Boat, commitments: Array<Commitment>, promotionBefore: PromotionBefore) {
    //     this.rentalService.depart(new Departure(boat, commitments, promotionBefore)).subscribe(
    //         (rental) => {
    //             this.printer.printDepart(rental, this.config.getPrinterIp());
    //             this.infoService.event().emit("Nr " + rental.dayId + " " + rental.boat.name + " " + this.createStringForCommitments(rental.commitments) + this.createStringForPromotion(rental.promotionBefore) + " wurde vermietet.");
    //             this.boatService.updateStats();
    //             this.resetUi();
    //         }
    //     );
    // }

    //noinspection JSMethodCanBeStatic
    private createStringForPromotion(promotion: PromotionBefore): string {
        return promotion != null ? ("(" + promotion.name + ")") : "";
    }

    private createStringForCommitments(commitments: Array<Commitment>): string {
        let result: string = "";
        if (commitments != null && commitments.length > 0) {
            result += "(";
            let first: boolean = true;
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
    }

    private resetUi() {
        this.boatService.reset();
    }

    getBoatCounts():Array<BoatCount> {
        console.log("get boat counts")
        console.log("  --> " + this.boatService.getBoatCounts())
        return this.boatService.getBoatCounts();
    }

    boatCount(shortName: string): number {
        return this.boatService.getBoatCounts().filter(boatCount => boatCount.shortName == shortName)[0].count
    }

    boatMax(shortName: string): number {
        return this.boatService.getBoatCounts().filter(boatCount => boatCount.shortName == shortName)[0].max
    }

}
