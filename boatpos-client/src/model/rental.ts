import {Boat} from "./boat";
import {Commitment} from "./commitment";
import {PromotionBefore, PromotionAfter} from "./promotion";
import {PrettyPrinter} from "../prettyprinter";
import {isPresent} from "angular2/src/facade/lang";

export class Rental {

    dayId:number;
    day:Date;
    boat:Boat;
    departure:Date;
    arrival:Date;
    pricePaidAfter:number;
    pricePaidBefore:number;
    priceCalculatedAfter:number;
    priceCalculatedBefore:number;
    finished:boolean;
    deleted:boolean;
    coupon:boolean;
    promotionBefore:PromotionBefore;
    promotionAfter:PromotionAfter;
    commitments:Array<Commitment> = [];
    timeOfTravel:number;
    timeOfTravelCalculated:number;

    constructor(dayId:number,
                day:Date,
                boat:Boat,
                departure:Date,
                arrival:Date,
                pricePaidAfter:number,
                pricePaidBefore:number,
                priceCalculatedAfter:number,
                priceCalculatedBefore:number,
                finished:boolean,
                deleted:boolean,
                coupon:boolean,
                promotionBefore:PromotionBefore,
                promotionAfter:PromotionAfter,
                commitments:Array<Commitment>,
                timeOfTravel:number,
                timeOfTravelCalculated:number) {
        this.dayId = dayId;
        this.day = day;
        this.boat = boat;
        this.departure = departure;
        this.arrival = arrival;
        this.pricePaidAfter = pricePaidAfter;
        this.pricePaidBefore = pricePaidBefore;
        this.priceCalculatedAfter = priceCalculatedAfter;
        this.priceCalculatedBefore = priceCalculatedBefore;
        this.finished = finished;
        this.deleted = deleted;
        this.coupon = coupon;
        this.promotionBefore = promotionBefore;
        this.promotionAfter = promotionAfter;
        if (commitments != null) {
            commitments.forEach(commitment => this.commitments.push(commitment));
        }
        this.timeOfTravel = timeOfTravel;
        this.timeOfTravelCalculated = timeOfTravelCalculated;
    }

    static fromDepart(dayId:number,
                      day:Date,
                      boat:Boat,
                      departure:Date,
                      commitments:Array<Commitment>,
                      promotionBefore:PromotionBefore,
                      coupon:boolean,
                      priceCalculatedBefore:number):Rental {
        return new Rental(dayId,
            day,
            boat,
            departure,
            null,
            null,
            null,
            null,
            priceCalculatedBefore,
            null,
            null,
            coupon,
            promotionBefore,
            null,
            commitments,
            null,
            null
        );
    }

    toString():string {
        return JSON.stringify(this);
    }

    style():string {
        if (this.deleted === true) {
            return "rental-deleted";
        } else if (this.finished === true) {
            return "rental-finished";
        } else {
            return "rental-default";
        }
    }

    ppDayId():string {
        return new PrettyPrinter().pp3Pos(this.dayId);
    }

    ppCommitments():string {
        return new PrettyPrinter().printCommitments(this.commitments);
    }

    ppDeparture():string {
        return new PrettyPrinter().printTime(this.departure);
    }

    ppArrival():string {
        if (this.finished === true) {
            return new PrettyPrinter().printTime(this.arrival);
        } else {
            return "";
        }
    }

    ppPricePaidComplete():string {
        return new PrettyPrinter().ppPrice(this.pricePaidBefore + this.pricePaidAfter, null);
    }

    ppPromotionComplete():string {
        return new PrettyPrinter().printPromotions(this.promotionBefore, this.promotionAfter);
    }

    ppTimeOfTravel():string {
        if (isPresent(this.timeOfTravel) && this.timeOfTravel > 0) {
            return this.timeOfTravel + "Min";
        }
        return "";
    }
}