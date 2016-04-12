import {Component} from "angular2/core";
import {KeyBindingService} from "../../service/keybinding.service";
import {SaleService} from "../../service/sale.service";

@Component({
    selector: 'actions',
    templateUrl: "html/component/sale/actions.component.html",
    styleUrls: ["css/component/sale/actions.component.css"]
})
export class ActionsComponent {

    constructor(private saleService:SaleService, private keyBinding:KeyBindingService) {
        let map:{[key:string]:((e:ExtendedKeyboardEvent, combo:string) => any)} = {
            '*': () => {
                this.bill();
            },
            '-': () => {
                this.cancelLastElement();
            },
            '_': () => {
                this.cancelAllElements();
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

    bill() {
        this.saleService.bill();
    }
}