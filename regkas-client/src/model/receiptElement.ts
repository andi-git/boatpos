import {Product} from "./product";

export class ReceiptElement {

    totalPrice:number;
    amount:number;
    product:Product;

    constructor(totalPrice:number, amount:number, product:Product) {
        this.totalPrice = totalPrice;
        this.amount = amount;
        this.product = product;
    }

    toString():string {
        return JSON.stringify(this);
    }
}