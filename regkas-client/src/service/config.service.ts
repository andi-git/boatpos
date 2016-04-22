import {Injectable, EventEmitter} from "angular2/core";
import {Http, Headers} from "angular2/http";
import "rxjs/add/operator/map";
import "rxjs/add/operator/toPromise";
import {Config} from "../model/config";
import {isPresent} from "angular2/src/facade/lang";
import {IpAddress} from "../model/ipaddress";

@Injectable()
export class ConfigService {

    private config:Config;
    private configured:EventEmitter<Config> = new EventEmitter();

    constructor(private http:Http) {
        // load the config and fire an event when the config is loaded
        console.log("load config");
        this.http.get('config.json')
            .map((res) => {
                return res.json();
            })
            .subscribe((configJson) => {
                console.log("config loaded, fire event");
                this.config = new Config(configJson.backendUrl, configJson.username, configJson.password, configJson.cashBox);
                this.http.get(this.getBackendUrl() + 'rest/printer/ip', {headers: this.getDefaultHeader()})
                    .map(res => res.json())
                    .map(printerBean => {
                        this.config.printerIp = printerBean.ipAddress;
                        return printerBean.ipAddress;
                    }).subscribe(ipAddress => {
                    this.config.printerIp = ipAddress;
                    this.configured.emit(this.config);
                });
            });
    }

    isAlreadyConfigured():boolean {
        return isPresent(this.config.backendUrl) &&
            isPresent(this.config.printerIp) &&
            isPresent(this.config.username) &&
            isPresent(this.config.password);
    }

    isConfigured():EventEmitter<Config> {
        return this.configured;
    }

    getBackendUrl():string {
        return this.config.backendUrl;
    }

    getPrinterIp():string {
        return this.config.printerIp;
    }

    getDefaultHeader():Headers {
        let headers = new Headers();
        headers.append("Content-Type", "application/json");
        headers.append("username", this.config.username);
        headers.append("password", this.config.password);
        headers.append("cashbox", this.config.cashBox);
        return headers;
    }

    savePrinterIp(ip:string) {
        this.http.post(this.getBackendUrl() + 'rest/printer/ip', JSON.stringify(new IpAddress(ip)), {headers: this.getDefaultHeader()})
            .subscribe(this.config.printerIp = ip);
    }
}