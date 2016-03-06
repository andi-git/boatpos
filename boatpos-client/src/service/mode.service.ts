import {Injectable, EventEmitter} from 'angular2/core';

@Injectable()
export class ModeService {

    private mode:Mode = Mode.RENTAL;

    setMode(mode:Mode) {
        this.mode = mode;
    }

    getModeAsString():string {
        // TODO: this is ugly!
        if (this.mode == Mode.RENTAL) {
            return "RENTAL";
        } else if (this.mode == Mode.RENTALS) {
            return "RENTALS";
        }
        if (this.mode == Mode.STATS) {
            return "STATS";
        }
        return "UNKNOWN";
    }
}

export enum Mode {
    RENTAL,
    RENTALS,
    STATS
}