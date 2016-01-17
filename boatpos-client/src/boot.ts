import {bootstrap}      from 'angular2/platform/browser'
import {AppComponent}   from './app.component'
import {ConfigService} from "./config.service";

bootstrap(AppComponent, [ConfigService]);
