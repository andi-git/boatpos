import {Component} from 'angular2/core';
import {InfoService} from "../../service/info.service";

@Component({
    selector: 'info',
    templateUrl: "../../../html/component/rental/info.component.html",
    styleUrls: ["../../../css/component/rental/info.component.css"]
})
export class InfoComponent {

    private info: String;

    constructor(private infoService:InfoService) {
        infoService.event().subscribe((info) => {
            this.info = info;
            console.log("info: " + this.info);
        });
    }
}