import {Injectable, EventEmitter} from 'angular2/core';

@Injectable()
export class InfoService {

    private infoEvent:EventEmitter<String> = new EventEmitter();

    event():EventEmitter<String> {
        return this.infoEvent;
    }
}