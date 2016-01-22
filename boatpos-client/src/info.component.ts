import {Component} from 'angular2/core';
import {InfoService} from "./info.service";

@Component({
    selector: 'info',
    templateUrl: "info.component.html",
    styleUrls: ["info.component.css"]
})
export class InfoComponent {

    private info: String;

    constructor(private infoService:InfoService) {
        infoService.event().subscribe(info => this.info = info);
    }
}