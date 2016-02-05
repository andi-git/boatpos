System.register(['angular2/core', 'angular2/http', 'rxjs/add/operator/map', "./config.service", "./rental"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, http_1, config_service_1, rental_1;
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
            }],
        execute: function() {
            RentalService = (function () {
                function RentalService(http, configService) {
                    this.http = http;
                    this.configService = configService;
                    this.headers = new http_1.Headers();
                    this.headers.append('Content-Type', 'application/json');
                }
                RentalService.prototype.departe = function (departe) {
                    return this.http.post(this.configService.getBackendUrl() + 'rest/departure/depart', JSON.stringify(departe), { headers: this.headers })
                        .map(function (res) { return res.json(); })
                        .map(function (rentalBean) {
                        return rental_1.Rental.fromDeparte(rentalBean.dayId, rentalBean.day, rentalBean.boatBean, rentalBean.departure, rentalBean.commitmentBeans, rentalBean.promotionBeforeBean, rentalBean.coupon, rentalBean.priceCalculatedBefore);
                    });
                };
                RentalService.prototype.deleteRental = function (dayNumber) {
                    return this.http.delete(this.configService.getBackendUrl() + 'rest/rental/' + dayNumber, { headers: this.headers })
                        .map(function (res) { return res.json(); })
                        .map(function (rentalBean) {
                        return new rental_1.Rental(rentalBean.dayId, rentalBean.day, rentalBean.boatBean, rentalBean.departure, rentalBean.arrival, rentalBean.pricePaidAfter, rentalBean.pricePaidBefore, rentalBean.priceCalculatedAfter, rentalBean.priceCalculatedBefore, rentalBean.finished, rentalBean.deleted, rentalBean.coupon, rentalBean.promotionBeforeBean, rentalBean.promotionAfterBean, rentalBean.commitmentBeans);
                    });
                };
                RentalService.prototype.getRental = function (dayNumber) {
                    var _this = this;
                    return this.http.get(this.configService.getBackendUrl() + 'rest/rental/' + dayNumber, { headers: this.headers })
                        .map(function (res) { return res.json(); })
                        .map(function (rentalBean) {
                        return new rental_1.Rental(rentalBean.dayId, rentalBean.day, rentalBean.boatBean, _this.createDate(rentalBean.departure), _this.createDate(rentalBean.arrival), rentalBean.pricePaidAfter, rentalBean.pricePaidBefore, rentalBean.priceCalculatedAfter, rentalBean.priceCalculatedBefore, rentalBean.finished, rentalBean.deleted, rentalBean.coupon, rentalBean.promotionBeforeBean, rentalBean.promotionAfterBean, rentalBean.commitmentBeans);
                    });
                };
                RentalService.prototype.createDate = function (jsonDate) {
                    var date = new Date(jsonDate);
                    date.setUTCHours(date.getUTCHours() - 1);
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
