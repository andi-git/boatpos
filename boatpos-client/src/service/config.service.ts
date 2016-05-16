import {Injectable, EventEmitter} from "angular2/core";
import {Http, Headers} from "angular2/http";
import "rxjs/add/operator/map";
import "rxjs/add/operator/toPromise";
import {Config} from "../model/config";
import {Printer} from "../printer";
import {IpAddress} from "../model/ipaddress";
import {isPresent} from "angular2/src/facade/lang";

@Injectable()
export class ConfigService {

    private config:Config;
    private configured:EventEmitter<Config> = new EventEmitter();

    constructor(private http:Http, private printer:Printer) {
        // load the config and fire an event when the config is loaded
        console.log("load config");
        this.http.get('config.json')
            .map((res) => {
                return res.json();
            })
            .subscribe((configJson) => {
                console.log("config loaded, fire event");
                this.config = new Config(configJson.backendUrl, configJson.username, configJson.password);
                this.http.get(this.getBackendUrl() + 'rest/printer', {headers: this.getDefaultHeader()})
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

    isConfigured():EventEmitter<Config> {
        return this.configured;
    }

    getBackendUrl():string {
        if (isPresent(this.config)) {
            return this.config.backendUrl;
        } else {
            return "n/a";
        }
    }

    getPrinterIp():string {
        if (isPresent(this.config)) {
            return this.config.printerIp;
        } else {
            return "n/a";
        }
    }

    getDefaultHeader():Headers {
        let headers = new Headers();
        headers.append("Content-Type", "application/json");
        headers.append("username", this.config.username);
        headers.append("password", this.config.password);
        return headers;
    }

    addQueryParamCredentials(path:string):string {
        return path +
            "username=" + this.config.username + "&" +
            "password=" + this.config.password
    }

    savePrinterIp(ip:string) {
        this.http.post(this.getBackendUrl() + 'rest/printer', JSON.stringify(new IpAddress(ip)), {headers: this.getDefaultHeader()})
            .subscribe(this.config.printerIp = ip);
    }
}