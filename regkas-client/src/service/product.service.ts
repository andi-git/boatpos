import {Injectable} from "angular2/core";
import {Http} from "angular2/http";
import "rxjs/add/operator/map";
import {ConfigService} from "./config.service";
import {Observable} from "rxjs/Observable";
import {Product} from "../model/product";
import {ProductGroup} from "../model/productGroup";
import {isPresent} from "angular2/src/facade/lang";

@Injectable()
export class ProductService {

    private productGroupsCache:Array<ProductGroup>;

    // constructors do dependency injection in Angular2
    constructor(private http:Http, private configService:ConfigService) {
        if (this.configService.isAlreadyConfigured() === true) {
            this.loadProductGroups().subscribe(productGroups => this.productGroupsCache = productGroups);
        } else {
            this.configService.isConfigured().subscribe((config) => {
                this.loadProductGroups().subscribe(productGroups => this.productGroupsCache = productGroups);
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
                            productGroupBean.products.forEach(p => products.push(new Product(
                                p.id,
                                p.name,
                                p.price,
                                p.priority,
                                p.pictureUrl,
                                p.pictureUrlThumb,
                                p.keyBinding,
                                productGroupBean.name
                            )))
                        }
                        result.push(new ProductGroup(
                            productGroupBean.id,
                            productGroupBean.name,
                            productGroupBean.price,
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

    getProductGroups():Array<ProductGroup> {
        return this.productGroupsCache;
    }
}