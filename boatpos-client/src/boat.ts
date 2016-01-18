export class Boat {
    id:number;
    name:string;
    shortName:string;
    enabled:boolean;
    priority:number;
    pictureUrlSmall:string;
    pictureUrlMedium:string;

    constructor(id:number,
                name:string,
                enabled:boolean,
                priority:number,
                shortName:string,
                pictureUrlSmall:string,
                pictureUrlMedium:string) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.enabled = enabled;
        this.priority = priority;
        this.pictureUrlSmall = pictureUrlSmall;
        this.pictureUrlMedium = pictureUrlMedium;
    }
}

export class BoatCount {
    id:number;
    name:string;
    shortName:string;
    count:number;
    max:number;

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
    }
}