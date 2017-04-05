import {Injectable, EventEmitter} from 'angular2/core';

@Injectable()
export class ErrorService {

    private errorEvent:EventEmitter<String> = new EventEmitter();

    event():EventEmitter<String> {
        return this.errorEvent;
    }
}