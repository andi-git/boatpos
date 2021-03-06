import {Component} from "angular2/core";
import {KeyBindingService} from "../../service/keybinding.service";
import {SaleService} from "../../service/sale.service";
import {Printer} from "../../printer";
import {ConfigService} from "../../service/config.service";

@Component({
    selector: 'actions',
    templateUrl: "html/component/sale/actions.component.html",
    styleUrls: ["css/component/sale/actions.component.css"]
})
export class ActionsComponent {

    constructor(private saleService: SaleService, private keyBinding: KeyBindingService, private printer: Printer, private config: ConfigService) {
        let map: { [key: string]: ((e: ExtendedKeyboardEvent, combo: string) => any) } = {
            '*': () => {
                this.billCash();
            },
            '+': () => {
                this.billCard();
            },
            '-': () => {
                this.cancelLastElement();
            },
            '_': () => {
                this.cancelAllElements();
            },
            '~': () => {
                this.saleService.tagesBeleg();
            },
            '=': () => {
                this.cancelBill();
            }
        };
        this.keyBinding.addBindingForMain(map);
    }

    cancelLastElement() {
        this.saleService.cancelLastElement();
    }

    cancelAllElements() {
        this.saleService.cancelAllElements();
    }

    billCash() {
        this.saleService.bill('CASH');
    }

    billCard() {
        this.saleService.bill('CARD');
    }

    cancelBill() {
        this.saleService.cancelBill();
    }
}
