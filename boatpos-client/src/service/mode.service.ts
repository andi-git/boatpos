import {Injectable, EventEmitter} from 'angular2/core';
import {BoatService} from "./boat.service";
import {CommitmentService} from "./commitment.service";
import {PromotionService} from "./promotion.service";
import {RentalService} from "./rental.service";
import {ConfigService} from "./config.service";

@Injectable()
export class ModeService {

    defaultMode:Mode = Mode.RENTAL;

    private modeChangeEvent:EventEmitter<Mode> = new EventEmitter();

    // inject here the services to have a constructor-call on these components
    constructor(private boatService:BoatService, private commitmentService:CommitmentService, private promotionService:PromotionService, private configService:ConfigService) {
        this.configService.isConfigured().subscribe((config) => {
            console.log("constructor of ModeService");
            this.event().emit(this.defaultMode);
        });
    }

    event():EventEmitter<Mode> {
        return this.modeChangeEvent;
    }
}

export enum Mode {
    RENTAL,
    RENTALS,
    STATS,
    CONFIG
}