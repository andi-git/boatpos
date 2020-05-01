import {Component, NgZone} from 'angular2/core';
import {Boat, BoatCount} from '../../model/boat';
import {BoatService} from "../../service/boat.service";
import {Commitment} from "../../model/commitment";
import {PromotionBefore} from "../../model/promotion";
import {CommitmentService} from "../../service/commitment.service";
import {Departure} from "../../model/departure";
import {RentalService} from "../../service/rental.service";
import {InfoService} from "../../service/info.service";
import {Printer} from "../../printer";
import {ConfigService} from "../../service/config.service";

@Component({
    selector: 'boats',
    templateUrl: "html/component/rental/boats.component.html",
    styleUrls: ["css/component/rental/boats.component.css"]
})
export class BoatsComponent {

    private selectedBoat: string = null;

    constructor(private boatService: BoatService,
                private commitmentService: CommitmentService,
                private rentalService: RentalService,
                private infoService: InfoService,
                private configService: ConfigService,
                private printer: Printer) {
    }

    getBoats(): Array<Boat> {
        return this.boatService.getBoats();
    }

    click(boatCount: BoatCount) {
        if (this.selectedBoat == boatCount.shortName) {
            this.selectedBoat = null
            this.depart(this.boatService.getBoatByShortName(boatCount.shortName), [], null);
        } else {
            this.selectedBoat = boatCount.shortName
        }
    }

    clickWithIdentityCard(boatCount: BoatCount) {
        if (this.selectedBoat == (boatCount.shortName + "_id")) {
            this.selectedBoat = null
            this.depart(this.boatService.getBoatByShortName(boatCount.shortName), [this.commitmentService.getCommitmentByName('Ausweis')], null);
        } else {
            this.selectedBoat = boatCount.shortName + "_id"
        }
    }

    private depart(boat: Boat, commitments: Array<Commitment>, promotionBefore: PromotionBefore) {
        this.rentalService.depart(new Departure(boat, commitments, promotionBefore)).subscribe(
            (rental) => {
                this.printer.printDepart(rental, this.configService.getPrinterIp());
                this.infoService.event().emit("Nr " + rental.dayId + " " + rental.boat.name + " " + this.createStringForCommitments(rental.commitments) + this.createStringForPromotion(rental.promotionBefore) + " wurde vermietet.");
                this.boatService.updateStats();
            }
        );
    }

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

    getBoatCounts(): Array<BoatCount> {
        console.log("get boat counts")
        return this.boatService.getBoatCounts();
    }
}
