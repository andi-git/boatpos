import {Injectable, EventEmitter} from "angular2/core";
import {Http, Headers} from "angular2/http";
import "rxjs/add/operator/map";
import "rxjs/add/operator/toPromise";
import {Config} from "../model/config";
import {isPresent} from "angular2/src/facade/lang";

@Injectable()
export class ConfigService {

    private backendUrl:string;
    private printerUrl:string;
    private username:string;
    private password:string;
    private cashbox:string;
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
                this.printerUrl = config.printerUrl;
                this.username = config.username;
                this.password = config.password;
                this.cashbox = config.cashbox;
                this.configured.emit(config);
            });
    }

    isAlreadyConfigured():boolean {
        return isPresent(this.backendUrl) &&
            isPresent(this.printerUrl) &&
            isPresent(this.username) &&
            isPresent(this.password);
    }

    isConfigured():EventEmitter<Config> {
        return this.configured;
    }

    getBackendUrl():string {
        return this.backendUrl;
    }

    getPrinterUrl():string {
        return this.printerUrl;
    }

    getDefaultHeader():Headers {
        let headers = new Headers();
        headers.append("Content-Type", "application/json");
        headers.append("username", this.username);
        headers.append("password", this.password);
        headers.append("cashbox", this.cashbox);
        return headers;
    }
}