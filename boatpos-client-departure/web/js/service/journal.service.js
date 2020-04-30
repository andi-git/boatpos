System.register(['angular2/core', 'angular2/http', 'rxjs/add/operator/map', "./config.service", "../model/journalReport", "./rental.service", "angular2/src/facade/lang"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, http_1, config_service_1, journalReport_1, journalReport_2, rental_service_1, lang_1;
    var JournalService;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (_1) {},
            function (config_service_1_1) {
                config_service_1 = config_service_1_1;
            },
            function (journalReport_1_1) {
                journalReport_1 = journalReport_1_1;
                journalReport_2 = journalReport_1_1;
            },
            function (rental_service_1_1) {
                rental_service_1 = rental_service_1_1;
            },
            function (lang_1_1) {
                lang_1 = lang_1_1;
            }],
        execute: function() {
            JournalService = (function () {
                function JournalService(http, configService) {
                    this.http = http;
                    this.configService = configService;
                }
                JournalService.prototype.incomeCurrentDay = function () {
                    // call the rest-service
                    return this.http.get(this.configService.getBackendUrl() + 'rest/journal/income/day', { headers: this.configService.getDefaultHeader() })
                        .map(function (res) { return res.json(); })
                        .map(function (journalReportBean) {
                        var journalReport = new journalReport_1.JournalReport();
                        journalReport.start = rental_service_1.RentalService.createDate(journalReportBean.start);
                        journalReport.end = rental_service_1.RentalService.createDate(journalReportBean.end);
                        journalReportBean.journalReportItemBeans.forEach(function (jrib) {
                            return journalReport.add(new journalReport_2.JournalReportItem(jrib.boatName, jrib.pricePaidBeforeCash, jrib.pricePaidBeforeCashBeforeTax, jrib.pricePaidBeforeCashTax, jrib.pricePaidBeforeCard, jrib.pricePaidBeforeCardBeforeTax, jrib.pricePaidBeforeCardTax, jrib.pricePaidAfterCash, jrib.pricePaidAfterCashBeforeTax, jrib.pricePaidAfterCashTax, jrib.pricePaidAfterCard, jrib.pricePaidAfterCardBeforeTax, jrib.pricePaidAfterCardTax, jrib.count));
                        });
                        return journalReport;
                    });
                };
                JournalService.prototype.income = function (year, month, day) {
                    var args = year;
                    if (lang_1.isPresent(month)) {
                        args += "/";
                        args += month;
                    }
                    if (lang_1.isPresent(day)) {
                        args += "/";
                        args += day;
                    }
                    return this.http.get(this.configService.getBackendUrl() + 'rest/journal/income/' + args, { headers: this.configService.getDefaultHeader() })
                        .map(function (res) { return res.json(); })
                        .map(function (journalReportBean) {
                        var journalReport = new journalReport_1.JournalReport();
                        journalReport.start = rental_service_1.RentalService.createDate(journalReportBean.start);
                        journalReport.end = rental_service_1.RentalService.createDate(journalReportBean.end);
                        journalReportBean.journalReportItemBeans.forEach(function (jrib) {
                            return journalReport.add(new journalReport_2.JournalReportItem(jrib.boatName, jrib.pricePaidBeforeCash, jrib.pricePaidBeforeCashBeforeTax, jrib.pricePaidBeforeCashTax, jrib.pricePaidBeforeCard, jrib.pricePaidBeforeCardBeforeTax, jrib.pricePaidBeforeCardTax, jrib.pricePaidAfterCash, jrib.pricePaidAfterCashBeforeTax, jrib.pricePaidAfterCashTax, jrib.pricePaidAfterCard, jrib.pricePaidAfterCardBeforeTax, jrib.pricePaidAfterCardTax, jrib.count));
                        });
                        return journalReport;
                    });
                };
                JournalService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [http_1.Http, config_service_1.ConfigService])
                ], JournalService);
                return JournalService;
            }());
            exports_1("JournalService", JournalService);
        }
    }
});
