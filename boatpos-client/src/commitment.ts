export class Commitment {
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
}