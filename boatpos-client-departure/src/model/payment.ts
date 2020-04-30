import {Boat} from "./boat";
import {Commitment} from "./commitment";
import {PromotionBefore} from "./promotion";

export class Payment {

    dayNumber: number;
    value: number;
    paymentMethod: string;
    receiptType: string;

    constructor(dayNumber: number, value: number, paymentMethod: string, receiptType: string) {
        this.dayNumber = dayNumber;
        this.value = value;
        this.paymentMethod = paymentMethod;
        this.receiptType = receiptType;
    }

    toString(): string {
        return JSON.stringify(this);
    }
}