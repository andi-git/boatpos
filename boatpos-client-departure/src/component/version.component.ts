import {Component} from "angular2/core";
import {ConfigService} from "../service/config.service";

@Component({
    selector: 'version',
    templateUrl: "html/component/version.component.html",
    styleUrls: ["css/component/version.component.css"]
})
export class VersionComponent {

    private config:ConfigService;

    constructor(private configService:ConfigService) {
        this.config = configService;
    }

    printerIp():string {
        return this.config.getPrinterIp();
    }

    backendUrl():string {
        return this.config.getBackendUrl();
    }
}
