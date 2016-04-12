import {Component} from "angular2/core";
import {KeyBindingService} from "../../service/keybinding.service";
import {SaleService} from "../../service/sale.service";
import {ProductService} from "../../service/product.service";
import {ProductGroup} from "../../model/productGroup";

@Component({
    selector: 'products',
    templateUrl: "html/component/sale/products.component.html",
    styleUrls: ["css/component/sale/products.component.css"]
})
export class ProductComponent {

    constructor(private productService:ProductService, private saleService:SaleService, private keyBinding:KeyBindingService) {
        // let map:{[key:string]:((e:ExtendedKeyboardEvent, combo:string) => any)} = {
        //     '*': () => {
        //         this.bill();
        //     },
        //     '-': () => {
        //         this.cancelLastElement();
        //     },
        //     '_': () => {
        //         this.cancelAllElements();
        //     }
        // };
        // this.keyBinding.addBindingForMain(map);
    }

    getProductGroups():Array<ProductGroup> {
        console.log("#####");
        var productGroups = this.productService.getProductGroups();
        console.log(productGroups);
        return productGroups;
    }
}