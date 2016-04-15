import {Component} from "angular2/core";
import {KeyBindingService} from "../../service/keybinding.service";
import {SaleService} from "../../service/sale.service";

@Component({
    selector: 'numbers',
    templateUrl: "html/component/sale/numbers.component.html",
    styleUrls: ["css/component/sale/numbers.component.css"]
})
export class NumbersComponent {

    constructor(private saleService:SaleService, private keyBinding:KeyBindingService) {
        let map:{[key:string]:((e:ExtendedKeyboardEvent, combo:string) => any)} = {
            '.': () => {
                this.numberClick('.');
            },
            '<': () => {
                this.numberClick('<');
            },
            '>': () => {
                this.deleteNumberInput();
            }
        };
        for (var i = 0; i <= 9; i++) {
            map[i] = (e) => {
                this.numberClick(String.fromCharCode(e.charCode));
            };
        }
        this.keyBinding.addBindingForMain(map);
    }

    private numberClick(s:string) {
        this.saleService.setNumberInput(s);
    }

    private getNumberInput():string {
        return this.saleService.getNumberInput();
    }

    private deleteNumberInput() {
        this.saleService.deleteNumberInput();
    }
}