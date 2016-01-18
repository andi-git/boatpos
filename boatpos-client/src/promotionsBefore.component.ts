import {Component} from 'angular2/core';
import {PromotionBefore} from './promotionBefore';
import {PromotionBeforeService} from "./promotionBefore.service";
import {ConfigService} from "./config.service";

@Component({
    selector: 'promotionsBefore',
    templateUrl: "promotionsBefore.component.html",
    styleUrls: ["promotionsBefore.component.css"]
})
export class PromotionsBeforeComponent {

    private promotionsBefore:PromotionBefore[];
    private selectedPromotionBefore:PromotionBefore;
    private subscription: any;

    constructor(private promotionBeforeService:PromotionBeforeService, private configService:ConfigService) {
    }

    getBoats() {
        this.promotionBeforeService.getPromotionsBefore().subscribe(promotionsBefore => this.promotionsBefore = promotionsBefore);
    }

    ngOnInit() {
        this.subscription = this.configService.isConfigured().subscribe(config => this.getBoats());
    }

    onSelect(promotionBefore:PromotionBefore) {
        this.selectedPromotionBefore = promotionBefore;
    }
}