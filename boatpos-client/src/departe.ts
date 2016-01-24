import {Boat} from "./boat";
import {Commitment} from "./commitment";
import {PromotionBefore} from "./promotionBefore";

export class Departe {
    boatId:number;
    commitmentIds:Array<number> = [];
    promotionId:number;

    constructor(boatId:number,
                commitmentIds:Array<number>,
                promotionId:number) {
        this.boatId = boatId;
        this.commitmentIds = commitmentIds;
        this.promotionId = promotionId;
    }

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
}