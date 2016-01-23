import {Component} from 'angular2/core';
import {PromotionBefore} from './promotionBefore';
import {PromotionService} from "./promotion.service";
import {InfoService} from "./info.service";
import {NgZone} from "angular2/core";

@Component({
    selector: 'promotionsBefore',
    templateUrl: "promotionsBefore.component.html",
    styleUrls: ["promotionsBefore.component.css"]
})
export class PromotionsBeforeComponent {

    constructor(private promotionService:PromotionService, private infoService:InfoService, private zone:NgZone) {
    }

    ngOnInit() {
        Mousetrap.bind(['k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't'], (e) => {
            this.zone.run(() => {
                var promotionBefore = this.promotionService.getPromotionBeforeByKeyBinding(String.fromCharCode(e.charCode));
                if (promotionBefore != null) {
                    this.click(promotionBefore);
                }
            });
        });
    }

    getPromotionsBefore() : Array<PromotionBefore> {
        return this.promotionService.getPromotionsBefore();
    }

    click(promotionBefore:PromotionBefore) {
        if (promotionBefore.selected) {
            this.getPromotionsBefore().forEach(pb => pb.selected = false);
            this.infoService.event().emit("Aktion '" + promotionBefore.name + "' wurde entfernt.");
        } else {
            this.getPromotionsBefore().forEach(pb => pb.selected = false);
            promotionBefore.selected = true;
            this.infoService.event().emit("Aktion '" + promotionBefore.name + "' wurde ausgewählt.");
        }
    }
}