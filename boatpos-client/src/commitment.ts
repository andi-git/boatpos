export class Commitment {
    id:number;
    name:string;
    enabled:boolean;
    priority:number;
    selected:boolean;

    constructor(id:number,
                name:string,
                enabled:boolean,
                priority:number) {
        this.id = id;
        this.name = name;
        this.enabled = enabled;
        this.priority = priority;
    }
}