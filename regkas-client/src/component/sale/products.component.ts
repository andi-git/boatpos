import {Component} from "angular2/core";
import {KeyBindingService} from "../../service/keybinding.service";
import {SaleService} from "../../service/sale.service";
import {ProductService} from "../../service/product.service";
import {ProductGroup} from "../../model/productGroup";
import {Product} from "../../model/product";
import {PrettyPrinter} from "../../prettyprinter";

@Component({
    selector: 'products',
    templateUrl: "html/component/sale/products.component.html",
    styleUrls: ["css/component/sale/products.component.css"]
})
export class ProductComponent {

    constructor(private productService:ProductService, private saleService:SaleService, private keyBinding:KeyBindingService, private pp:PrettyPrinter) {
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

    ppPrice(price:number):string {
        return this.pp.ppPrice(price, "");
    }

    getNonGenericProducts(productGroup:ProductGroup):Array<Product> {
        let products:Array<Product> = [];
        productGroup.products.forEach(p => {
            if (p.generic === false) {
                products.push(p);
            }
        });
        return products;
    }
}