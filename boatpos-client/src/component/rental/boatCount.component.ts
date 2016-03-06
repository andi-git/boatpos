import {Component} from 'angular2/core';
import {BoatCount} from '../../model/boat';
import {BoatService} from "../../service/boat.service";
import {ConfigService} from "../../service/config.service";
import {PrettyPrinter} from "../../prettyprinter";

@Component({
    selector: 'boatCount',
    templateUrl: "../../../html/component/rental/boatCount.component.html",
    styleUrls: ["../../../css/component/rental/boatCount.component.css"]
})
export class BoatCountComponent {

    private boatCounts:BoatCount[];
    private subscription:any;

    constructor(private boatService:BoatService, private configService:ConfigService, private pp:PrettyPrinter) {
    }

    getBoatCounts():Array<BoatCount> {
        return this.boatService.getBoatCounts();
    }
}