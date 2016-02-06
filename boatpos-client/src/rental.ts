import {Boat} from "./boat";
import {Commitment} from "./commitment";
import {PromotionBefore, PromotionAfter} from "./promotion";

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

    static fromDeparte(dayId:number,
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
}