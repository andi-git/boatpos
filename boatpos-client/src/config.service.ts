import {Injectable} from 'angular2/core';
import {Http, Headers, HTTP_PROVIDERS} from 'angular2/http';
import 'rxjs/add/operator/map';
import {Observable} from "rxjs/Observable";
import {Config} from "./config";

@Injectable()
export class ConfigService {

    private backendUrl:String;

    constructor(private http:Http) {
        this.loadConfig().subscribe((config) => {
            console.log("+++ " + config.backendUrl);
            this.backendUrl = config.backendUrl
        });
        console.log("backendUrl: " + this.backendUrl)
    }

    loadConfig(): Observable<Config> {
        return this.http.get('config.json')
            .map((res) => {
                return res.json();
            });
    }

    getBackendUrl(): String {
        return this.backendUrl;
        //let backendUrl:String = "";
        //this.loadConfig().subscribe(config => backendUrl = config.backendUrl);
        //return backendUrl;
    }
}