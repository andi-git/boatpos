export class Boat {
    id:number;
    name:string;
    shortName:string;
    pictureUrlSmall:string;
    pictureUrlMedium:string;

    constructor(id:number,
                name:string,
                shortName:string,
                pictureUrlSmall:string,
                pictureUrlMedium:string) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.pictureUrlSmall = pictureUrlSmall;
        this.pictureUrlMedium = pictureUrlMedium;
    }
}