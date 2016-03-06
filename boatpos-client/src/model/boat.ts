export class Boat {
    id:number;
    name:string;
    shortName:string;
    enabled:boolean;
    priority:number;
    pictureUrl:string;
    pictureUrlThumb:string;
    selected:boolean;
    keyBinding:string;

    constructor(id:number,
                name:string,
                enabled:boolean,
                priority:number,
                shortName:string,
                pictureUrl:string,
                pictureUrlThumb:string,
                keyBinding:string) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.enabled = enabled;
        this.priority = priority;
        this.pictureUrl = pictureUrl;
        this.pictureUrlThumb = pictureUrlThumb;
        this.keyBinding = keyBinding;
    }

    isSelected():boolean {
        return this.selected;
    }

    setSelected(selected:boolean) {
        this.selected = selected;
    }

    toString():string {
        return JSON.stringify(this);
    }
}

export class BoatCount {
    id:number;
    name:string;
    shortName:string;
    count:number;
    max:number;
    style:string;

    constructor(id:number,
                name:string,
                shortName:string,
                count:number,
                max:number) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.count = count;
        this.max = max;
        if (count >= max) {
            this.style = "max";
        }
    }

    toString():string {
        return JSON.stringify(this);
    }
}