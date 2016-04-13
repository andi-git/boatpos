import {Component} from "angular2/core";
import {KeyBindingService} from "../../service/keybinding.service";
import {SaleService} from "../../service/sale.service";
import {ProductService} from "../../service/product.service";
import {ProductGroup} from "../../model/productGroup";
import {Product} from "../../model/product";

@Component({
    selector: 'products',
    templateUrl: "html/component/sale/products.component.html",
    styleUrls: ["css/component/sale/products.component.css"]
})
export class ProductComponent {

    constructor(private productService:ProductService, private saleService:SaleService, private keyBinding:KeyBindingService) {
    }

    getProductGroups():Array<ProductGroup> {
        var productGroups = this.productService.getProductGroups();
        return productGroups;
    }

    getGenericProduct(productGroup:ProductGroup):Product {
        for (var i = 0; i < productGroup.products.length; i++) {
            if (productGroup.products[i].generic === true) {
                return productGroup.products[i];
            }
        }
        return null;
    }

    chooseProduct(product:Product) {
        this.saleService.chooseProduct(product);
    }

    handlePG(s:string):string {
        return s.replace("PG: ", "");
    }
}