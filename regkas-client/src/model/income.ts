import {isPresent} from "angular2/src/facade/lang";
export class Income {

    start:Date;
    end:Date;
    totalIncome:number;
    incomeProductGroups:Array<IncomeProductGroup> = [];

    constructor(start:Date, end:Date, totalIncome:number, incomeProductGroups:Array<IncomeProductGroup>) {
        this.start = start;
        this.end = end;
        this.totalIncome = totalIncome;
        if (isPresent(incomeProductGroups)) {
            // this.products.push(products);
            incomeProductGroups.forEach(p => this.incomeProductGroups.push(p));
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