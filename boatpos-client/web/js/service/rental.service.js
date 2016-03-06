System.register(['angular2/core', 'angular2/http', 'rxjs/add/operator/map', "./config.service", "../model/rental", "../model/arrival", "../model/bill"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, http_1, config_service_1, rental_1, arrival_1, bill_1;
    var RentalService;
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
            function (rental_1_1) {
                rental_1 = rental_1_1;
            },
            function (arrival_1_1) {
                arrival_1 = arrival_1_1;
            },
            function (bill_1_1) {
                bill_1 = bill_1_1;
            }],
        execute: function() {
            RentalService = (function () {
                function RentalService(http, configService) {
                    this.http = http;
                    this.configService = configService;
                    this.headers = new http_1.Headers();
                    this.headers.append('Content-Type', 'application/json');
                }
                RentalService.prototype.depart = function (depart) {
                    return this.http.post(this.configService.getBackendUrl() + 'rest/departure/depart', JSON.stringify(depart), { headers: this.headers })
                        .map(function (res) { return res.json(); })
                        .map(function (rentalBean) {
                        return rental_1.Rental.fromDepart(rentalBean.dayId, RentalService.createDate(rentalBean.day), rentalBean.boatBean, RentalService.createDate(rentalBean.departure), rentalBean.commitmentBeans, rentalBean.promotionBeforeBean, rentalBean.coupon, rentalBean.priceCalculatedBefore);
                    });
                };
                RentalService.prototype.payBefore = function (payment) {
                    var _this = this;
                    return this.http.post(this.configService.getBackendUrl() + 'rest/departure/pay', JSON.stringify(payment), { headers: this.headers })
                        .map(function (res) { return res.json(); })
                        .map(function (rentalBean) {
                        return _this.convertRentalBeanToRental(rentalBean);
                    });
                };
                RentalService.prototype.deleteRental = function (dayNumber) {
                    var _this = this;
                    return this.http.delete(this.configService.getBackendUrl() + 'rest/rental/' + dayNumber, { headers: this.headers })
                        .map(function (res) { return res.json(); })
                        .map(function (rentalBean) {
                        return _this.convertRentalBeanToRental(rentalBean);
                    });
                };
                RentalService.prototype.undoDeleteRental = function (dayNumber) {
                    var _this = this;
                    return this.http.get(this.configService.getBackendUrl() + 'rest/rental/undoDelete/' + dayNumber, { headers: this.headers })
                        .map(function (res) { return res.json(); })
                        .map(function (rentalBean) {
                        return _this.convertRentalBeanToRental(rentalBean);
                    });
                };
                RentalService.prototype.getRental = function (dayNumber) {
                    var _this = this;
                    return this.http.get(this.configService.getBackendUrl() + 'rest/rental/' + dayNumber, { headers: this.headers })
                        .map(function (res) { return res.json(); })
                        .map(function (rentalBean) {
                        return _this.convertRentalBeanToRental(rentalBean);
                    });
                };
                RentalService.prototype.arrive = function (dayNumber) {
                    var _this = this;
                    return this.http.post(this.configService.getBackendUrl() + 'rest/arrival/arrive', JSON.stringify(new arrival_1.Arrival(dayNumber)), { headers: this.headers })
                        .map(function (res) { return res.json(); })
                        .map(function (rentalBean) {
                        return _this.convertRentalBeanToRental(rentalBean);
                    });
                };
                RentalService.prototype.payAfter = function (payment) {
                    var _this = this;
                    return this.http.post(this.configService.getBackendUrl() + 'rest/arrival/pay', JSON.stringify(payment), { headers: this.headers })
                        .map(function (res) { return res.json(); })
                        .map(function (billBean) {
                        return _this.convertBillBeanToBill(billBean);
                    });
                };
                RentalService.prototype.loadAllForCurrentDay = function () {
                    var _this = this;
                    return this.http.get(this.configService.getBackendUrl() + 'rest/rental/currentDay')
                        .map(function (res) { return res.json(); })
                        .map(function (rentals) {
                        var result = [];
                        if (rentals) {
                            rentals.forEach(function (rental) {
                                result.push(_this.convertRentalBeanToRental(rental));
                            });
                        }
                        return result;
                    });
                };
                RentalService.prototype.convertRentalBeanToRental = function (rentalBean) {
                    return new rental_1.Rental(rentalBean.dayId, RentalService.createDate(rentalBean.day), rentalBean.boatBean, RentalService.createDate(rentalBean.departure), RentalService.createDate(rentalBean.arrival), rentalBean.pricePaidAfter, rentalBean.pricePaidBefore, rentalBean.priceCalculatedAfter, rentalBean.priceCalculatedBefore, rentalBean.finished, rentalBean.deleted, rentalBean.coupon, rentalBean.promotionBeforeBean, rentalBean.promotionAfterBean, rentalBean.commitmentBeans, rentalBean.timeOfTravel, rentalBean.timeOfTravelCalculated);
                };
                ;
                RentalService.prototype.convertBillBeanToBill = function (billBean) {
                    return new bill_1.Bill();
                };
                ;
                RentalService.createDate = function (jsonDate) {
                    var date = new Date(jsonDate);
                    date.setUTCHours(date.getUTCHours());
                    date.setUTCDate(date.getUTCDate());
                    date.setUTCMonth(date.getUTCMonth() + 1);
                    return date;
                };
                RentalService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [http_1.Http, config_service_1.ConfigService])
                ], RentalService);
                return RentalService;
            })();
            exports_1("RentalService", RentalService);
        }
    }
});