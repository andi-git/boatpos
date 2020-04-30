export class Arrival {

    public dayNumber:number;

    constructor(dayNumber:number) {
        this.dayNumber = dayNumber;
    }

    toString():string {
        return JSON.stringify(this);
    }
}