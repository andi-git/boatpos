import {ReceiptElement} from "./receiptElement";
export class Sale {

    paymentMethod:string;
    receiptType:string;
    saleElements:Array<ReceiptElement>;

    constructor(paymentMethod:string, receiptType:string, saleElements:Array<ReceiptElement>) {
        this.paymentMethod = paymentMethod;
        this.receiptType = receiptType;
        this.saleElements = saleElements;
    }

    toString():string {
        return JSON.stringify(this);
    }
}