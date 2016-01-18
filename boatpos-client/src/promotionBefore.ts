export class PromotionBefore {
    id:number;
    name:string;
    timeCredit:number;
    enabled:boolean;
    priority:number;

    constructor(id:number,
                name:string,
                timeCredit:number,
                enabled:boolean,
                priority:number) {
        this.id = id;
        this.name = name;
        this.timeCredit = timeCredit;
        this.enabled = enabled;
        this.priority = priority;
    }
}