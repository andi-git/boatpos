import {Component} from 'angular2/core';
import {PromotionBefore} from './promotionBefore';
import {PromotionBeforeService} from "./promotionBefore.service";
import {ConfigService} from "./config.service";
import {InfoService} from "./info.service";
import {NgZone} from "angular2/core";

@Component({
    selector: 'promotionsBefore',
    templateUrl: "promotionsBefore.component.html",
    styleUrls: ["promotionsBefore.component.css"]
})
export class PromotionsBeforeComponent {

    private promotionsBefore:PromotionBefore[];
    private subscription:any;
    private zone:NgZone;

    constructor(private promotionBeforeService:PromotionBeforeService, private configService:ConfigService, private infoService:InfoService, private zone:NgZone) {
    }

    ngOnInit() {
        Mousetrap.bind(['k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't'], (e) => {
            console.log(e.charCode);
            this.zone.run(() => {
                this.onSelect(this.getPromotionBeforeByKeyBinding(String.fromCharCode(e.charCode)));
            });
        });
        this.subscription = this.configService.isConfigured().subscribe(
            config => this.promotionBeforeService.getPromotionsBefore().subscribe(promotionsBefore => this.promotionsBefore = promotionsBefore)
        );
    }

    onSelect(promotionBefore:PromotionBefore) {
        if (promotionBefore.selected) {
            this.promotionsBefore.forEach(pb => pb.selected = false);
            this.infoService.event().emit("Aktion '" + promotionBefore.name + "' wurde entfernt.");
        } else {
            this.promotionsBefore.forEach(pb => pb.selected = false);
            promotionBefore.selected = true;
            this.infoService.event().emit("Aktion '" + promotionBefore.name + "' wurde ausgewählt.");
        }
    }

    getPromotionBeforeByKeyBinding(keyBinding:string):PromotionBefore {
        let promotionBefore:PromotionBefore = null;
        this.promotionsBefore.forEach((c) => {
            if (c.keyBinding == keyBinding) {
                promotionBefore = c;
            }
        });
        return promotionBefore;
    }
}