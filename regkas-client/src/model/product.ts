export class Product {

    id:string;
    name:string;
    price:number;
    priority:string;
    pictureUrl:string;
    pictureUrlThumb:string;
    keyBinding:string;
    generic:boolean;

    constructor(id:string, name:string, price:number, priority:string, pictureUrl:string, pictureUrlThumb:string, keyBinding:string, generic:boolean) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.priority = priority;
        this.pictureUrl = pictureUrl;
        this.pictureUrlThumb = pictureUrlThumb;
        this.keyBinding = keyBinding;
        this.generic = generic;
    }

    toString():string {
        return JSON.stringify(this);
    }
}