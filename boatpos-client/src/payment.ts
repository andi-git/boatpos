import {Boat} from "./boat";
import {Commitment} from "./commitment";
import {PromotionBefore} from "./promotion";

export class Payment {
    public dayNumber:number;
    public value:number;
    public paymentMethod:string;

    constructor(dayNumber:number, value:number, paymentMethod:string) {
        this.dayNumber = dayNumber;
        this.value = value;
        this.paymentMethod = paymentMethod;
    }

    toString():string {
        return JSON.stringify(this);
    }
}