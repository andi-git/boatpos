import {Boat} from "./boat";
import {Commitment} from "./commitment";
import {PromotionBefore} from "./promotion";

export class Payment {
    public dayNumber:number;
    public value:number;

    constructor(dayNumber:number, value:number) {
        this.dayNumber = dayNumber;
        this.value = value;
    }

    toString():string {
        return JSON.stringify(this);
    }
}