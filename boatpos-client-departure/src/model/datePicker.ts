export class DatePicker {

    private days:Array<number> = [];
    private currentDay:number;
    private months:Array<string> = [];
    private currentMonth:string;
    private years:Array<number> = [];
    private currentYear:number;

    constructor() {
        for (let i:number = 0; i < 31; i++) {
            this.days[i] = i + 1;
        }
        this.months.push("Jänner");
        this.months.push("Februar");
        this.months.push("März");
        this.months.push("April");
        this.months.push("Mai");
        this.months.push("Juni");
        this.months.push("Juli");
        this.months.push("August");
        this.months.push("September");
        this.months.push("Oktober");
        this.months.push("November");
        this.months.push("Dezember");
        for (let i:number = 0; i < 10; i++) {
            this.years[i] = i + 2016;
        }
        this.reset();
    }

    dayChange(day:any) {
        this.currentDay = day;
    }

    monthChange(month:any) {
        this.currentMonth = month;
    }

    yearChange(year:any) {
        this.currentYear = year;
    }

    reset() {
        this.currentDay = new Date(Date.now()).getDate();
        this.currentMonth = this.months[new Date(Date.now()).getMonth()];
        this.currentYear = new Date(Date.now()).getFullYear();
    }

    convertMonth(month:string):number {
        for (let i = 0; i < this.months.length; i++) {
            if (this.months[i] == month) {
                return i + 1;
            }
        }
        return 0;
    }

    public getCurrentDay():number {
        return this.currentDay;
    }

    public getCurrentMonthAsString():string {
        return this.currentMonth;
    }

    public getCurrentMonthAsNumber():number{
        return this.convertMonth(this.currentMonth);
    }

    public getCurrentYear():number {
        return this.currentYear;
    }

    getDays():Array<number> {
        return this.days;
    }

    getMonthsAsString():Array<string> {
        return this.months;
    }

    getYears():Array<number> {
        return this.years;
    }

    toString():string {
        return JSON.stringify(this);
    }

    setCurrentDay(day:number) {
        this.currentDay = day;
    }

    setCurrentMonth(month:string) {
        this.currentMonth = month;
    }

    setCurrentYear(year:number) {
        this.currentYear = year;
    }
}