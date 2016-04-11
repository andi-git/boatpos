import {bootstrap}      from 'angular2/platform/browser'
import {AppComponent}   from './component/app.component'
import {ConfigService} from "./service/config.service";
import {provide} from "angular2/core";
import {RentalService} from "./service/rental.service";
//noinspection TypeScriptCheckImport
import {ModalConfig} from "lib/angular2-modal";

bootstrap(AppComponent,
    [ConfigService,
        provide(Mousetrap, {
            useFactory: () => new Mousetrap()
        }),
        provide(ModalConfig, {
            useValue: new ModalConfig('lg', true, 81)
        })
    ]);
