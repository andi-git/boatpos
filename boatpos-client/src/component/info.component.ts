import {Component} from 'angular2/core';
import {InfoService} from "../service/info.service";
import {ErrorService} from "../service/error.service";

@Component({
    selector: 'info',
    templateUrl: "html/component/info.component.html",
    styleUrls: ["css/component/info.component.css"]
})
export class InfoComponent {

    private info:string = "Info: ";

    private containerClass: string = "info-container";

    constructor(private infoService:InfoService, private errorService:ErrorService) {
        infoService.event().subscribe((info) => {
            this.info = "Info: " + info;
            this.containerClass = "info-container";
            console.log("info: " + this.info);
        });
        errorService.event().subscribe((error) => {
            this.info = "Fehler: " + error;
            this.containerClass = "error-container";
            console.log("info: " + this.info);
        });
    }
}