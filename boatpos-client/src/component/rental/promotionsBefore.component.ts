import {Component, NgZone} from 'angular2/core';
import {PromotionBefore} from '../../model/promotion';
import {PromotionService} from "../../service/promotion.service";
import {InfoService} from "../../service/info.service";
import {KeyBindingService} from "../../service/keybinding.service";

@Component({
    selector: 'promotionsBefore',
    templateUrl: "html/component/rental/promotionsBefore.component.html",
    styleUrls: ["css/component/rental/promotionsBefore.component.css"]
})
export class PromotionsBeforeComponent {

    constructor(private promotionService:PromotionService, private infoService:InfoService, private zone:NgZone, private keyBinding:KeyBindingService) {
    }

    ngOnInit() {
        let map:{[key: string] : ((e:ExtendedKeyboardEvent, combo:string) => any)} = {};
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
        if (promotionBefore.enabled === true) {
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
}