import {Component} from "angular2/core";
import {ConfigService} from "../service/config.service";

@Component({
    selector: 'version',
    templateUrl: "html/component/version.component.html",
    styleUrls: ["css/component/version.component.css"]
})
export class VersionComponent {

    private backend:string;
    private printer:string;

    constructor(private configService:ConfigService) {
        // when configuration is finished, load and cache boats
        this.configService.isConfigured().subscribe((config) => {
            this.backend = this.configService.getBackendUrl();
            this.printer = this.configService.getPrinterUrl();
        });
    }
}