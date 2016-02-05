import {Component, NgZone} from 'angular2/core';
import {PromotionBefore} from './promotion';
import {PromotionService} from "./promotion.service";
import {InfoService} from "./info.service";
import {KeyBindingService} from "./keybinding.service";

@Component({
    selector: 'promotionsBefore',
    templateUrl: "promotionsBefore.component.html",
    styleUrls: ["promotionsBefore.component.css"]
})
export class PromotionsBeforeComponent {

    constructor(private promotionService:PromotionService, private infoService:InfoService, private zone:NgZone, private keyBinding:KeyBindingService) {
    }

    ngOnInit() {
        let map = {K: Function};
        // k...t
        for (var i = 107; i <= 116; i++) {
            map[String.fromCharCode(i)] = (e) => {
                this.zone.run(() => {
                    var promotionBefore = this.promotionService.getPromotionBeforeByKeyBinding(String.fromCharCode(e.charCode));
                    if (promotionBefore != null) {
                        this.click(promotionBefore);
                    }
                });
            };
        }
        this.keyBinding.addBindingForMain(map);
    }

    getPromotionsBefore():Array<PromotionBefore> {
        return this.promotionService.getPromotionsBefore();
    }

    click(promotionBefore:PromotionBefore) {
        if (promotionBefore.selected) {
            this.promotionService.resetSelected();
            this.infoService.event().emit("Aktion '" + promotionBefore.name + "' wurde entfernt.");
        } else {
            this.promotionService.resetSelected();
            promotionBefore.selected = true;
            this.infoService.event().emit("Aktion '" + promotionBefore.name + "' wurde ausgewählt.");
        }
    }
}