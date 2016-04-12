import {Product} from "./product";

export class ReceiptElement {

    price:number;
    amount:number;
    product:Product;

    constructor(price:number, amount:number, product:Product) {
        this.price = price;
        this.amount = amount;
        this.product = product;
    }

    toString():string {
        return JSON.stringify(this);
    }
}