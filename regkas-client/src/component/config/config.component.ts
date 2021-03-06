import {Component} from "angular2/core";
import {ConfigService} from "../../service/config.service";
import {Printer} from "../../printer";

@Component({
    selector: 'config',
    templateUrl: "html/component/config/config.component.html",
    styleUrls: ["css/component/config/config.component.css"]
})
export class ConfigComponent {

    private possibleIps:Array<string> = [];

    constructor(private config:ConfigService, private printer:Printer) {
        console.log("constructor of ConfigComponent");
        this.possibleIps.push("192.168.8.11");
    }

    printerIp():string {
        return this.config.getPrinterIp();
    }

    getPossibleIps():Array<string> {
        return this.possibleIps;
    }

    testIp(ip:string) {
        console.log("test ip " + ip);
        this.printer.printTest(ip);
    }

    useIp(ip:string) {
        console.log("use ip " + ip);
        this.config.savePrinterIp(ip);
    }
}
