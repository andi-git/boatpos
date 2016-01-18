import {Injectable, EventEmitter} from 'angular2/core';
import {Http, Headers, HTTP_PROVIDERS} from 'angular2/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import {Observable} from "rxjs/Observable";
import {Config} from "./config";

@Injectable()
export class ConfigService {

    private backendUrl:String;
    private configured:EventEmitter<Config> = new EventEmitter();

    constructor(private http:Http) {
        // load the config and fire an event when the config is loaded
        console.log("load config");
        this.http.get('config.json')
            .map((res) => {
                return res.json();
            })
            .subscribe((config) => {
                console.log("config loaded, fire event");
                this.backendUrl = config.backendUrl;
                this.configured.emit(config);
            });
    }

    isConfigured():EventEmitter<Config> {
        return this.configured;
    }

    getBackendUrl():String {
        return this.backendUrl;
    }
}