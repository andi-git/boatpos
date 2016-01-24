import {bootstrap}      from 'angular2/platform/browser'
import {AppComponent}   from './app.component'
import {ConfigService} from "./config.service";
import {provide} from "angular2/core";
import {RentalService} from "./rental.service";

bootstrap(AppComponent,
    [ConfigService,
        provide(Mousetrap, {
            useFactory: () => new Mousetrap()
        })
    ]);
