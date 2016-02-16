export class PromotionBefore {
    id:number;
    name:string;
    timeCredit:number;
    enabled:boolean;
    priority:number;
    selected:boolean;
    keyBinding:string;
    pictureUrl:string;
    pictureUrlThumb:string;
    style:string = "enabled";

    constructor(id:number,
                name:string,
                timeCredit:number,
                enabled:boolean,
                priority:number,
                keyBinding:string,
                pictureUrl:string,
                pictureUrlThumb:string) {
        this.id = id;
        this.name = name;
        this.timeCredit = timeCredit;
        this.enabled = enabled;
        this.priority = priority;
        this.keyBinding = keyBinding;
        this.pictureUrl = pictureUrl;
        this.pictureUrlThumb = pictureUrlThumb;
        if (this.enabled === false) {
            this.style = "disabled";
        }

    }

    toString():string {
        return JSON.stringify(this);
    }
}

export class PromotionAfter {

    id:number;
    name:string;
    enabled:boolean;
    priority:number;
    selected:boolean;
    keyBinding:string;
    pictureUrl:string;
    pictureUrlThumb:string;

    constructor(id:number,
                name:string,
                enabled:boolean,
                priority:number,
                keyBinding:string,
                pictureUrl:string,
                pictureUrlThumb:string) {
        this.id = id;
        this.name = name;
        this.enabled = enabled;
        this.priority = priority;
        this.keyBinding = keyBinding;
        this.pictureUrl = pictureUrl;
        this.pictureUrlThumb = pictureUrlThumb;
    }

    toString():string {
        return JSON.stringify(this);
    }
}