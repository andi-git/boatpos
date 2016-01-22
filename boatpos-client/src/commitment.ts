export class Commitment {
    id:number;
    name:string;
    enabled:boolean;
    priority:number;
    selected:boolean;
    keyBinding:string;

    constructor(id:number,
                name:string,
                enabled:boolean,
                priority:number,
                keyBinding:string) {
        this.id = id;
        this.name = name;
        this.enabled = enabled;
        this.priority = priority;
        this.keyBinding = keyBinding;
    }
}