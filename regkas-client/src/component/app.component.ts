import {Component, OnInit, enableProdMode} from "angular2/core";
import {ConfigService} from "./../service/config.service";
import {HTTP_PROVIDERS} from "angular2/http";
import {InfoComponent} from "./info.component";
import {InfoService} from "./../service/info.service";
import {MenuComponent} from "./menu.component";
//noinspection TypeScriptCheckImport
import {Modal} from "lib/angular2-modal";
import {ModalHandler} from "./../modalHandler";
import {PrettyPrinter} from "./../prettyprinter";
import {Printer} from "./../printer";
import {ModeService, Mode} from "./../service/mode.service";
import {VersionComponent} from "./version.component";
import {KeyBindingService} from "../service/keybinding.service";
import {NumbersComponent} from "./sale/numbers.component";
import {SaleService} from "../service/sale.service";
import {ActionsComponent} from "./sale/actions.component";
import {ProductService} from "../service/product.service";
import {ProductComponent} from "./sale/products.component";
import {ReceiptComponent} from "./sale/receipt.component";
import {JournalService} from "../service/journal.service";
import {StatsComponent} from "./stats/stats.component";
import {ConfigComponent} from "./config/config.component";

enableProdMode();

@Component({
    selector: 'my-app',
    templateUrl: "html/component/app.component.html",
    styleUrls: ["css/component/app.component.css"],
    directives: [InfoComponent, MenuComponent, VersionComponent, NumbersComponent, ActionsComponent, ProductComponent, ReceiptComponent, StatsComponent, ConfigComponent],
    providers: [InfoService, HTTP_PROVIDERS, Modal, KeyBindingService, ModalHandler, PrettyPrinter, Printer, ModeService, SaleService, ProductService, ConfigService, JournalService]
})
export class AppComponent implements OnInit {

    private modeService:ModeService;

    mode:String;

    constructor(modeService:ModeService) {
        this.modeService = modeService;
        this.modeService.event().subscribe((mode) => {
            console.log("mode-change - AppComponent: " + Mode[mode]);
            this.mode = Mode[mode];
        });
    }

    ngOnInit():any {
        return undefined;
    }
}
