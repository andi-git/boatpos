export class Config {
    backendUrl:string;
    printerIp:string;
    username:string;
    password:string;

    constructor(backendUrl:string, username:string, password:string) {
        this.backendUrl = backendUrl;
        this.username = username;
        this.password = password;
    }

    toString():string {
        return JSON.stringify(this);
    }
}