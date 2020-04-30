import {Boat} from "./boat";
import {Commitment} from "./commitment";
import {PromotionBefore} from "./promotion";

export class Departure {
    boatId:number;
    commitmentIds:Array<number> = [];
    promotionId:number;

    constructor(boat:Boat,
                commitments:Array<Commitment>,
                promotion:PromotionBefore) {
        if (boat != null) {
            this.boatId = boat.id;
        }
        if (commitments != null) {
            commitments.forEach(commitment => this.commitmentIds.push(commitment.id));
        }
        if (promotion != null) {
            this.promotionId = promotion.id;
        }
    }

    toString():string {
        return JSON.stringify(this);
    }
}