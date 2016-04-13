import {Product} from "./product";
import {isPresent} from "angular2/src/facade/lang";
export class ProductGroup {

    id:string;
    name:string;
    taxPercent:number;
    priority:string;
    pictureUrl:string;
    pictureUrlThumb:string;
    keyBinding:string;
    products:Array<Product> = [];

    constructor(id:string, name:string, taxPercent:number, priority:string, pictureUrl:string, pictureUrlThumb:string, keyBinding:string, products:Array<Product>) {
        this.id = id;
        this.name = name;
        this.taxPercent = taxPercent;
        this.priority = priority;
        this.pictureUrl = pictureUrl;
        this.pictureUrlThumb = pictureUrlThumb;
        this.keyBinding = keyBinding;
        if (isPresent(products)) {
            // this.products.push(products);
            products.forEach(p => this.products.push(p));
        }
    }

    toString():string {
        return JSON.stringify(this);
    }
}