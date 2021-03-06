import {PromotionBefore, PromotionAfter, AddPromotion} from "../model/promotion";
import {Injectable} from "angular2/core";
import {Http} from "angular2/http";
import "rxjs/add/operator/map";
import {ConfigService} from "./config.service";
import {Observable} from "rxjs/Observable";
import {Rental} from "../model/rental";

@Injectable()
export class PromotionService {

    private promotionsBeforeCache:Array<PromotionBefore>;

    private promotionHolliKnolliCache:PromotionAfter;

    constructor(private http:Http, private configService:ConfigService) {
        // when configuration is finished, load and cache promotions
        this.configService.isConfigured().subscribe((config) => {
            console.log("constructor of PromotionService");
            this.loadPromotionsBefore().subscribe(promotionsBefore => this.promotionsBeforeCache = promotionsBefore);
            this.loadPromotionHolliKnolli().subscribe(promotionHolliKnolli => this.promotionHolliKnolliCache = promotionHolliKnolli);
        });
    }

    getPromotionsBefore():Array<PromotionBefore> {
        return this.promotionsBeforeCache;
    }

    private loadPromotionsBefore():Observable<Array<PromotionBefore>> {
        return this.http.get(this.configService.getBackendUrl() + 'rest/promotion/before/enabled', {headers: this.configService.getDefaultHeader()})
            .map(res => res.json())
            .map((promotions:Array<any>) => {
                let result:Array<PromotionBefore> = [];
                if (promotions) {
                    promotions.forEach((promotion) => {
                        result.push(new PromotionBefore(
                            promotion.id,
                            promotion.name,
                            promotion.timeCredit,
                            promotion.enabled,
                            promotion.priority,
                            promotion.keyBinding,
                            promotion.pictureUrl,
                            promotion.pictureUrlThumb));
                    });
                }
                return result;
            });
    }

    loadPromotionHolliKnolli():Observable<PromotionAfter> {
        return this.http.get(this.configService.getBackendUrl() + 'rest/promotion/after/name/HolliKnolli', {headers: this.configService.getDefaultHeader()})
            .map(res => res.json())
            .map((holliKnolli) => {
                return new PromotionAfter(
                    holliKnolli.id,
                    holliKnolli.name,
                    holliKnolli.enabled,
                    holliKnolli.priority,
                    holliKnolli.keyBinding,
                    holliKnolli.pictureUrl,
                    holliKnolli.pictureUrlThumb);
            });
    }

    getPromotionBeforeByKeyBinding(keyBinding:string):PromotionBefore {
        let promotionBefore:PromotionBefore = null;
        this.getPromotionsBefore().forEach((p) => {
            if (p.keyBinding == keyBinding) {
                promotionBefore = p;
            }
        });
        return promotionBefore;
    }

    resetSelected() {
        this.getPromotionsBefore().forEach((pb:PromotionBefore) => {
            pb.selected = false
        });
    }

    getSelectedPromotionsBefore():PromotionBefore {
        let selectedPromotionBefore:PromotionBefore;
        this.getPromotionsBefore().forEach((promotionBefore) => {
            if (promotionBefore.selected === true) {
                selectedPromotionBefore = promotionBefore;
            }
        });
        return selectedPromotionBefore;
    }

    getHolliKnolli():PromotionAfter {
        return this.promotionHolliKnolliCache;
    }
}