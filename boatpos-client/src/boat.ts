export class BoatCompact {
    id:number;
    name:string;
    shortName:string;
    pictureUrlMedium:string;

    constructor(id:number,
                name:string,
                shortName:string,
                pictureUrlMedium:string) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.pictureUrlMedium = pictureUrlMedium;
    }
}