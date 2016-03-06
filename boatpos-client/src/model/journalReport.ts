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
    pricePaidBeforeCard:number;
    pricePaidAfterCash:number;
    pricePaidAfterCard:number;
    count:number;

    constructor(boatName:string,
                pricePaidBeforeCash:number,
                pricePaidBeforeCard:number,
                pricePaidAfterCash:number,
                pricePaidAfterCard:number,
                count:number) {
        this.boatName = boatName;
        this.pricePaidBeforeCash = pricePaidBeforeCash;
        this.pricePaidBeforeCard = pricePaidBeforeCard;
        this.pricePaidAfterCash = pricePaidAfterCash;
        this.pricePaidAfterCard = pricePaidAfterCard;
        this.count = count;
    }

    toString():string {
        return JSON.stringify(this);
    }
}