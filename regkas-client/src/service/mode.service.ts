import {Injectable, EventEmitter} from 'angular2/core';
import {ConfigService} from "./config.service";

@Injectable()
export class ModeService {

    defaultMode:Mode = Mode.REGKAS;

    private modeChangeEvent:EventEmitter<Mode> = new EventEmitter();

    // inject here the services to have a constructor-call on these components
    constructor(private configService:ConfigService) {
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
    REGKAS
}