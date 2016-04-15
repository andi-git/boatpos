import {Injectable} from "angular2/core";
import {Http} from "angular2/http";
import "rxjs/add/operator/map";
import {ConfigService} from "./config.service";
import {Observable} from "rxjs/Observable";
import {Product} from "../model/product";
import {ProductGroup} from "../model/productGroup";
import {isPresent} from "angular2/src/facade/lang";
import {KeyBindingService} from "./keybinding.service";
import {SaleService} from "./sale.service";

@Injectable()
export class ProductService {

    private productGroupsCache:Array<ProductGroup>;

    // constructors do dependency injection in Angular2
    constructor(private http:Http, private configService:ConfigService, private keyBinding:KeyBindingService, private saleService:SaleService) {
        if (this.configService.isAlreadyConfigured() === true) {
            this.loadProductGroups().subscribe(productGroups => {
                this.productGroupsCache = productGroups;
                this.addKeyBindings();
            });
        } else {
            this.configService.isConfigured().subscribe((config) => {
                this.loadProductGroups().subscribe(productGroups => this.productGroupsCache = productGroups);
                this.addKeyBindings();
            });
        }
    }

    private loadProductGroups():Observable<Array<ProductGroup>> {
        // call the rest-service
        return this.http.get(this.configService.getBackendUrl() + 'rest/productgroup', {headers: this.configService.getDefaultHeader()})
            // map the result to json
            .map(res => res.json())
            // map the result to Boat
            .map((productGroupBeans:Array<any>) => {
                let result:Array<any> = [];
                if (productGroupBeans) {
                    productGroupBeans.forEach((productGroupBean) => {
                        let products:Array<Product> = [];
                        if (isPresent(productGroupBean.products)) {
                            productGroupBean.products.forEach(p => {
                                products.push(new Product(
                                    p.id,
                                    p.name,
                                    p.price,
                                    p.priority,
                                    p.pictureUrl,
                                    p.pictureUrlThumb,
                                    p.keyBinding,
                                    p.generic
                                ))
                            })
                        }
                        result.push(new ProductGroup(
                            productGroupBean.id,
                            productGroupBean.name,
                            productGroupBean.totalPrice,
                            productGroupBean.priority,
                            productGroupBean.pictureUrl,
                            productGroupBean.pictureUrlThumb,
                            productGroupBean.keyBinding,
                            products));
                    });
                }
                return result;
            });
    }

    private addKeyBindings() {
        let map:{[key:string]:((e:ExtendedKeyboardEvent, combo:string) => any)} = {};
        this.productGroupsCache.forEach(pg => {
            pg.products.forEach(p => {
                if (isPresent(p.keyBinding) && p.keyBinding != "" && p.keyBinding != "#") {
                    map[p.keyBinding] = e => {
                        this.saleService.chooseProduct(p);
                    }
                }
            })
        });
        this.keyBinding.addBindingForMain(map);
    }

    getProductGroups():Array<ProductGroup> {
        return this.productGroupsCache;
    }
}