export class Config {
    backendUrl:string;
    printerIp:string;
    username:string;
    password:string;
    cashBox: string;

    constructor(backendUrl:string, username:string, password:string, cashBox:string) {
        this.backendUrl = backendUrl;
        this.username = username;
        this.password = password;
        this.cashBox = cashBox;
    }

    toString():string {
        return JSON.stringify(this);
    }
}