import {Injectable} from 'angular2/core';
import {Http} from 'angular2/http';
import 'rxjs/add/operator/map';
import {ConfigService} from "./config.service";
import {Observable} from "rxjs/Observable";
import {JournalReport} from "../model/journalReport";
import {JournalReportItem} from "../model/journalReport";
import {RentalService} from "./rental.service";
import {isPresent} from "angular2/src/facade/lang";

@Injectable()
export class JournalService {

    constructor(private http:Http, private configService:ConfigService) {
    }

    public incomeCurrentDay():Observable<JournalReport> {
        // call the rest-service
        return this.http.get(this.configService.getBackendUrl() + 'rest/journal/income/day', {headers : this.configService.getDefaultHeader()})
            // map the result to json
            .map(res => res.json())
            // map the result to Boat
            .map((journalReportBean:any) => {
                let journalReport:JournalReport = new JournalReport();
                journalReport.start = RentalService.createDate(journalReportBean.start);
                journalReport.end = RentalService.createDate(journalReportBean.end);
                journalReportBean.journalReportItemBeans.forEach(jrib =>
                    journalReport.add(new JournalReportItem(jrib.boatName,
                        jrib.pricePaidBeforeCash,
                        jrib.pricePaidBeforeCashBeforeTax,
                        jrib.pricePaidBeforeCashTax,
                        jrib.pricePaidBeforeCard,
                        jrib.pricePaidBeforeCardBeforeTax,
                        jrib.pricePaidBeforeCardTax,
                        jrib.pricePaidAfterCash,
                        jrib.pricePaidAfterCashBeforeTax,
                        jrib.pricePaidAfterCashTax,
                        jrib.pricePaidAfterCard,
                        jrib.pricePaidAfterCardBeforeTax,
                        jrib.pricePaidAfterCardTax,
                        jrib.count))
                );
                return journalReport;
            });
    }

    public income(year:number, month?:number, day?:number):Observable<JournalReport> {
        let args:string = year;
        if (isPresent(month)) {
            args += "/";
            args += month;
        }
        if (isPresent(day)) {
            args += "/";
            args += day;
        }
        return this.http.get(this.configService.getBackendUrl() + 'rest/journal/income/' + args, {headers : this.configService.getDefaultHeader()})
            .map(res => res.json())
            .map((journalReportBean:any) => {
                let journalReport:JournalReport = new JournalReport();
                journalReport.start = RentalService.createDate(journalReportBean.start);
                journalReport.end = RentalService.createDate(journalReportBean.end);
                journalReportBean.journalReportItemBeans.forEach(jrib =>
                    journalReport.add(new JournalReportItem(jrib.boatName,
                        jrib.pricePaidBeforeCash,
                        jrib.pricePaidBeforeCashBeforeTax,
                        jrib.pricePaidBeforeCashTax,
                        jrib.pricePaidBeforeCard,
                        jrib.pricePaidBeforeCardBeforeTax,
                        jrib.pricePaidBeforeCardTax,
                        jrib.pricePaidAfterCash,
                        jrib.pricePaidAfterCashBeforeTax,
                        jrib.pricePaidAfterCashTax,
                        jrib.pricePaidAfterCard,
                        jrib.pricePaidAfterCardBeforeTax,
                        jrib.pricePaidAfterCardTax,
                        jrib.count))
                );
                return journalReport;
            });
    }
}