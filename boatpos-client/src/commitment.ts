export class Commitment {
    id:number;
    name:string;
    enabled:boolean;
    priority:number;
    selected:boolean;
    keyBinding:string;
    pictureUrl:string;
    pictureUrlThumb:string;
    paper:boolean;

    constructor(id:number,
                name:string,
                enabled:boolean,
                priority:number,
                keyBinding:string,
                pictureUrl:string,
                pictureUrlThumb:string,
                paper:boolean) {
        this.id = id;
        this.name = name;
        this.enabled = enabled;
        this.priority = priority;
        this.keyBinding = keyBinding;
        this.pictureUrl = pictureUrl;
        this.pictureUrlThumb = pictureUrlThumb;
        this.paper = paper;
    }

    toString():string {
        return JSON.stringify(this);
    }
}