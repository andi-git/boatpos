export class PromotionBefore {
    id:number;
    name:string;
    timeCredit:number;
    enabled:boolean;
    priority:number;
    selected:boolean;
    keyBinding:string;

    constructor(id:number,
                name:string,
                timeCredit:number,
                enabled:boolean,
                priority:number,
                keyBinding:string) {
        this.id = id;
        this.name = name;
        this.timeCredit = timeCredit;
        this.enabled = enabled;
        this.priority = priority;
        this.keyBinding = keyBinding;
    }
}