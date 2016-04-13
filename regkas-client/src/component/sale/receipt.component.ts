import {Component} from "angular2/core";
import {SaleService} from "../../service/sale.service";
import {ReceiptElement} from "../../model/receiptElement";
import {PrettyPrinter} from "../../prettyprinter";

@Component({
    selector: 'receipt',
    templateUrl: "html/component/sale/receipt.component.html",
    styleUrls: ["css/component/sale/receipt.component.css"]
})
export class ReceiptComponent {

    constructor(private saleService:SaleService, private pp:PrettyPrinter) {
    }

    getReceiptElements():Array<ReceiptElement> {
        return this.saleService.getReceiptElements();
    }

    ppPrice(price:number):string {
        return this.pp.ppPrice(price, "");
    }

    printSum():string {
        return "Summe: " + this.pp.ppPrice(this.saleService.getSum());
    }

    handlePG(s:string):string {
        return s.replace("PG: ", "");
    }
}