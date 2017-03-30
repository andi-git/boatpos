import {Injectable} from "angular2/core";
import {Http} from "angular2/http";
import "rxjs/add/operator/map";
import {ConfigService} from "./config.service";
import {isPresent} from "angular2/src/facade/lang";
import {Observable} from "rxjs/Observable";
import {Income, IncomeProductGroup, TaxElement} from "../model/income";

@Injectable()
export class JournalService {

    constructor(private http:Http, private configService:ConfigService) {
    }

    public incomeCurrentDay():Observable<Income> {
        return this.income(
            new Date(Date.now()).getFullYear(),
            new Date(Date.now()).getMonth() + 1,
            new Date(Date.now()).getDate());
    }

    public income(year:number, month:number, day:number):Observable<Income> {
        let args:string = year;
        if (isPresent(month)) {
            args += "/";
            args += month;
        }
        if (isPresent(day)) {
            args += "/";
            args += day;
        }
        return this.http.get(this.configService.getBackendUrl() + 'rest/journal/income/' + args, {headers: this.configService.getDefaultHeader()})
            .map(res => res.json())
            .map((incomeBean:any) => {
                return JournalService.convertToIncome(incomeBean);
            });
    }

    public static convertToIncome(incomeBean: any): Income {
        let incomeProductGroups:Array<IncomeProductGroup> = [];
        incomeBean.incomeElements.forEach(
            pg => incomeProductGroups.push(new IncomeProductGroup(
                pg.name,
                pg.income,
                pg.taxPercent,
                pg.priority
            ))
        );
        let taxElements:Array<TaxElement> = [];
        incomeBean.taxElements.forEach(
            te => taxElements.push(new TaxElement(
                te.taxPercent,
                te.priority,
                te.price,
                te.priceBeforeTax,
                te.priceTax
            ))
        );
        return new Income(
            JournalService.createDate(incomeBean.start),
            JournalService.createDate(incomeBean.end),
            incomeBean.totalIncome,
            incomeProductGroups,
            taxElements);
    }

    public static createDate(jsonDate:string):Date {
        return new Date(jsonDate + "T00:00:00.000Z");
    }

    public static createDateTime(jsonDateTime:string):Date {
        return new Date(jsonDateTime);
    }

    dep(year:number, month:number, day:number):Observable {
        let args:string = year;
        if (isPresent(month)) {
            args += "/";
            args += month;
        }
        if (isPresent(day)) {
            args += "/";
            args += day;
        }
        // return this.http.get(this.configService.getBackendUrl() + 'rest/journal/income/' + args, {headers: this.configService.getDefaultHeader()})
        //     .map(res => new Blob([res], {type: 'application/zip'}));
        return this.http.get(this.configService.getBackendUrl() + 'rest/journal/dep/' + args, {headers: this.configService.getDefaultHeader()});
    }
}