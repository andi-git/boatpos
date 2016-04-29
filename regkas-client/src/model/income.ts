import {isPresent} from "angular2/src/facade/lang";
export class Income {

    start:Date;
    end:Date;
    totalIncome:number;
    incomeProductGroups:Array<IncomeProductGroup> = [];
    taxElements:Array<TaxElement> = [];

    constructor(start:Date, end:Date, totalIncome:number, incomeProductGroups:Array<IncomeProductGroup>, taxElements:Array<TaxElement>) {
        this.start = start;
        this.end = end;
        this.totalIncome = totalIncome;
        if (isPresent(incomeProductGroups)) {
            incomeProductGroups.forEach(p => this.incomeProductGroups.push(p));
        }
        if (isPresent(taxElements)) {
            taxElements.forEach(t => this.taxElements.push(t));
        }
    }

    toString():string {
        return JSON.stringify(this);
    }
}

export class IncomeProductGroup {

    name:String;
    income:number;
    taxPercent:number;
    priority:number;

    constructor(name:String, income:number, taxPercent:number, priority:number) {
        this.name = name;
        this.income = income;
        this.taxPercent = taxPercent;
        this.priority = priority;
    }

    toString():string {
        return JSON.stringify(this);
    }
}

export class TaxElement {

    taxPercent:number;
    priority:boolean;
    price:number;
    priceBeforeTax:number;
    priceTax:number;

    constructor(taxPercent:number, priority:boolean, price:number, priceBeforeTax:number, priceTax:number) {
        this.taxPercent = taxPercent;
        this.priority = priority;
        this.price = price;
        this.priceBeforeTax = priceBeforeTax;
        this.priceTax = priceTax;
    }

    toString():string {
        return JSON.stringify(this);
    }
}