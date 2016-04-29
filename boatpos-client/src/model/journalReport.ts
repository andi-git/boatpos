export class JournalReport {

    start:Date;
    end:Date;
    journalReportItems:Array<JournalReportItem> = [];

    constructor() { }

    public add(journalReportItem:JournalReportItem) {
        this.journalReportItems.push(journalReportItem);
    }

    toString():string {
        return JSON.stringify(this);
    }
}

export class JournalReportItem {

    boatName:string;
    pricePaidBeforeCash:number;
    pricePaidBeforeCashBeforeTax:number;
    pricePaidBeforeCashTax:number;
    pricePaidBeforeCard:number;
    pricePaidBeforeCardBeforeTax:number;
    pricePaidBeforeCardTax:number;
    pricePaidAfterCash:number;
    pricePaidAfterCashBeforeTax:number;
    pricePaidAfterCashTax:number;
    pricePaidAfterCard:number;
    pricePaidAfterCardBeforeTax:number;
    pricePaidAfterCardTax:number;
    count:number;

    constructor(boatName:string,
                pricePaidBeforeCash:number,
                pricePaidBeforeCashBeforeTax:number,
                pricePaidBeforeCashTax:number,
                pricePaidBeforeCard:number,
                pricePaidBeforeCardBeforeTax:number,
                pricePaidBeforeCardTax:number,
                pricePaidAfterCash:number,
                pricePaidAfterCashBeforeTax:number,
                pricePaidAfterCashTax:number,
                pricePaidAfterCard:number,
                pricePaidAfterCardBeforeTax:number,
                pricePaidAfterCardTax:number,
                count:number) {
        this.boatName = boatName;
        this.pricePaidBeforeCash = pricePaidBeforeCash;
        this.pricePaidBeforeCashBeforeTax = pricePaidBeforeCashBeforeTax;
        this.pricePaidBeforeCashTax = pricePaidBeforeCashTax;
        this.pricePaidBeforeCard = pricePaidBeforeCard;
        this.pricePaidBeforeCardBeforeTax = pricePaidBeforeCardBeforeTax;
        this.pricePaidBeforeCardTax = pricePaidBeforeCardTax;
        this.pricePaidAfterCash = pricePaidAfterCash;
        this.pricePaidAfterCashBeforeTax = pricePaidAfterCashBeforeTax;
        this.pricePaidAfterCashTax = pricePaidAfterCashTax;
        this.pricePaidAfterCard = pricePaidAfterCard;
        this.pricePaidAfterCardBeforeTax = pricePaidAfterCardBeforeTax;
        this.pricePaidAfterCardTax = pricePaidAfterCardTax;
        this.count = count;
    }

    toString():string {
        return JSON.stringify(this);
    }
}