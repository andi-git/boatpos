export class IpAddress {
    ipAddress:string;

    constructor(ipAddress:string) {
        this.ipAddress = ipAddress;
    }

    toString():string {
        return JSON.stringify(this);
    }
}