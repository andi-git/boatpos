System.register(["angular2/core", "angular2/http", "rxjs/add/operator/map", "./config.service", "../model/rental", "../model/arrival", "../model/bill", "./promotion.service", "../model/promotion", "../model/income", "./info.service", "./error.service", "../printer", "../prettyprinter", "angular2/src/facade/lang", "./journal.service"], function(exports_1, context_1) {
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
    var core_1, http_1, config_service_1, rental_1, arrival_1, bill_1, promotion_service_1, promotion_1, income_1, info_service_1, error_service_1, printer_1, prettyprinter_1, lang_1, journal_service_1;
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
            },
            function (promotion_service_1_1) {
                promotion_service_1 = promotion_service_1_1;
            },
            function (promotion_1_1) {
                promotion_1 = promotion_1_1;
            },
            function (income_1_1) {
                income_1 = income_1_1;
            },
            function (info_service_1_1) {
                info_service_1 = info_service_1_1;
            },
            function (error_service_1_1) {
                error_service_1 = error_service_1_1;
            },
            function (printer_1_1) {
                printer_1 = printer_1_1;
            },
            function (prettyprinter_1_1) {
                prettyprinter_1 = prettyprinter_1_1;
            },
            function (lang_1_1) {
                lang_1 = lang_1_1;
            },
            function (journal_service_1_1) {
                journal_service_1 = journal_service_1_1;
            }],
        execute: function() {
            RentalService = (function () {
                function RentalService(http, configService, promotionService, errorService, infoService, printer, pp, journalService) {
                    this.http = http;
                    this.configService = configService;
                    this.promotionService = promotionService;
                    this.errorService = errorService;
                    this.infoService = infoService;
                    this.printer = printer;
                    this.pp = pp;
                    this.journalService = journalService;
                    this.signatureDeviceAvailableText = "";
                }
                RentalService.prototype.depart = function (depart) {
                    return this.http.post(this.configService.getBackendUrl() + 'rest/departure/depart', JSON.stringify(depart), { headers: this.configService.getDefaultHeader() })
                        .map(function (res) { return res.json(); })
                        .map(function (rentalBean) {
                        return rental_1.Rental.fromDepart(rentalBean.dayId, RentalService.createDate(rentalBean.day), rentalBean.boatBean, RentalService.createDateTime(rentalBean.departure), rentalBean.commitmentBeans, rentalBean.promotionBeforeBean, rentalBean.coupon, rentalBean.priceCalculatedBefore, rentalBean.myRentalId);
                    });
                };
                RentalService.prototype.payBefore = function (payment) {
                    var _this = this;
                    return this.http.post(this.configService.getBackendUrl() + 'rest/departure/pay', JSON.stringify(payment), { headers: this.configService.getDefaultHeader() })
                        .map(function (res) { return res.json(); })
                        .map(function (rentalBean) {
                        return _this.convertRentalBeanToRental(rentalBean);
                    });
                };
                RentalService.prototype.deleteRental = function (dayNumber) {
                    var _this = this;
                    return this.http.delete(this.configService.getBackendUrl() + 'rest/rental/' + dayNumber, { headers: this.configService.getDefaultHeader() })
                        .map(function (res) { return res.json(); })
                        .map(function (rentalBean) {
                        return _this.convertRentalBeanToRental(rentalBean);
                    });
                };
                RentalService.prototype.undoDeleteRental = function (dayNumber) {
                    var _this = this;
                    return this.http.get(this.configService.getBackendUrl() + 'rest/rental/undoDelete/' + dayNumber, { headers: this.configService.getDefaultHeader() })
                        .map(function (res) { return res.json(); })
                        .map(function (rentalBean) {
                        return _this.convertRentalBeanToRental(rentalBean);
                    });
                };
                RentalService.prototype.getRental = function (dayNumber) {
                    var _this = this;
                    return this.http.get(this.configService.getBackendUrl() + 'rest/rental/' + dayNumber, { headers: this.configService.getDefaultHeader() })
                        .map(function (res) { return res.json(); })
                        .map(function (rentalBean) {
                        return _this.convertRentalBeanToRental(rentalBean);
                    });
                };
                RentalService.prototype.arrive = function (dayNumber) {
                    var _this = this;
                    return this.http.post(this.configService.getBackendUrl() + 'rest/arrival/arrive', JSON.stringify(new arrival_1.Arrival(dayNumber)), { headers: this.configService.getDefaultHeader() })
                        .map(function (res) { return res.json(); })
                        .map(function (rentalBean) {
                        return _this.convertRentalBeanToRental(rentalBean);
                    });
                };
                RentalService.prototype.addHolliKnolli = function (rental) {
                    var _this = this;
                    return this.http.post(this.configService.getBackendUrl() + 'rest/arrival/promotion', JSON.stringify(new promotion_1.AddPromotion(rental.dayId, this.promotionService.getHolliKnolli().id)), { headers: this.configService.getDefaultHeader() })
                        .map(function (res) { return res.json(); })
                        .map(function (rentalBean) {
                        return _this.convertRentalBeanToRental(rentalBean);
                    });
                };
                RentalService.prototype.removeHolliKnolli = function (rental) {
                    var _this = this;
                    return this.http.put(this.configService.getBackendUrl() + 'rest/arrival/promotion', JSON.stringify(new promotion_1.RemovePromotionsAfter(rental.dayId)), { headers: this.configService.getDefaultHeader() })
                        .map(function (res) { return res.json(); })
                        .map(function (rentalBean) {
                        return _this.convertRentalBeanToRental(rentalBean);
                    });
                };
                RentalService.prototype.payAfter = function (payment) {
                    var _this = this;
                    return this.http.post(this.configService.getBackendUrl() + 'rest/arrival/pay', JSON.stringify(payment), { headers: this.configService.getDefaultHeader() })
                        .map(function (res) {
                        console.log(res.json());
                        return res.json();
                    })
                        .map(function (billBean) {
                        console.log("billBean: " + billBean);
                        return _this.convertBillBeanToBill(billBean);
                    });
                };
                RentalService.prototype.startBeleg = function () {
                    var _this = this;
                    this.checkIfStarbelegMustBePrinted().subscribe(function (check) {
                        if (check === true) {
                            _this.receipt("Start-Beleg");
                        }
                    });
                };
                RentalService.prototype.schlussBeleg = function () {
                    this.receipt("Schluss-Beleg");
                };
                RentalService.prototype.nullBeleg = function () {
                    this.receipt("Null-Beleg");
                };
                RentalService.prototype.tagesBeleg = function () {
                    this.receipt("Tages-Beleg", this.currentYear(), this.currentMonth(), this.currentDay());
                };
                RentalService.prototype.monatsBeleg = function () {
                    this.receipt("Monats-Beleg", this.currentYear(), this.currentMonth());
                };
                RentalService.prototype.jahresBeleg = function () {
                    this.receipt("Jahres-Beleg", this.currentYear());
                };
                //noinspection JSMethodCanBeStatic
                RentalService.prototype.currentDay = function () {
                    return new Date(Date.now()).getDate();
                };
                //noinspection JSMethodCanBeStatic
                RentalService.prototype.currentMonth = function () {
                    return new Date(Date.now()).getMonth() + 1;
                };
                //noinspection JSMethodCanBeStatic
                RentalService.prototype.currentYear = function () {
                    return new Date(Date.now()).getFullYear();
                };
                RentalService.prototype.receipt = function (receiptType, withJournalYear, withJournalMonth, withJournalDay) {
                    var _this = this;
                    this.http.post(this.configService.getBackendUrl() + 'rest/arrival/receipt', receiptType, { headers: this.configService.getDefaultHeader() })
                        .map(function (res) {
                        return res.json();
                    })
                        .map(function (billBean) {
                        return _this.convertBillBeanToBill(billBean);
                    }).subscribe(function (bill) {
                        if (lang_1.isPresent(withJournalYear)) {
                            _this.journalService.income(withJournalYear, withJournalMonth, withJournalDay).subscribe(function (journalReport) {
                                _this.printer.printBill(bill, _this.configService.getPrinterIp(), journalReport);
                            });
                        }
                        else {
                            _this.printer.printBill(bill, _this.configService.getPrinterIp());
                        }
                        _this.infoService.event().emit("Rechnung '" + bill.receiptIdentifier + "' wurde gedruckt.");
                    }, function (error) {
                        console.log("error: " + JSON.stringify(error));
                        _this.errorService.event().emit("Rechnung konnte NICHT erstellt werden - Vorgang wurde abgebrochen!");
                    });
                };
                RentalService.prototype.checkIfStarbelegMustBePrinted = function () {
                    // call the rest-service
                    return this.http.get(this.configService.getBackendUrl() + 'rest/arrival/start/check', { headers: this.configService.getDefaultHeader() })
                        .map(function (res) { return res.text() === 'true'; });
                };
                RentalService.prototype.loadAllForCurrentDay = function () {
                    var _this = this;
                    return this.http.get(this.configService.getBackendUrl() + 'rest/rental/currentDay', { headers: this.configService.getDefaultHeader() })
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
                RentalService.prototype.loadAllFor = function (year, month, day) {
                    var _this = this;
                    return this.http.get(this.configService.getBackendUrl() + 'rest/rental/' + year + '/' + month + '/' + day, { headers: this.configService.getDefaultHeader() })
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
                //noinspection JSMethodCanBeStatic
                RentalService.prototype.convertRentalBeanToRental = function (rentalBean) {
                    return new rental_1.Rental(rentalBean.dayId, RentalService.createDate(rentalBean.day), rentalBean.boatBean, RentalService.createDateTime(rentalBean.departure), RentalService.createDateTime(rentalBean.arrival), rentalBean.pricePaidAfter, rentalBean.pricePaidBefore, rentalBean.priceCalculatedAfter, rentalBean.priceCalculatedBefore, rentalBean.finished, rentalBean.deleted, rentalBean.coupon, rentalBean.promotionBeforeBean, rentalBean.promotionAfterBean, rentalBean.commitmentBeans, rentalBean.timeOfTravel, rentalBean.timeOfTravelCalculated, rentalBean.receiptId, rentalBean.myRentalId);
                };
                ;
                RentalService.prototype.convertBillBeanToBill = function (billBean) {
                    var taxSetElements = [];
                    billBean.billTaxSetElements.forEach(function (tse) {
                        taxSetElements.push(new bill_1.TaxSetElement(tse.name, tse.taxPercent, tse.amount, tse.pricePreTax, tse.priceAfterTax, tse.priceTax));
                    });
                    var sammelBeleg = null;
                    var tagesBeleg = null;
                    var monatsBeleg = null;
                    var jahresBeleg = null;
                    var income = null;
                    if (billBean.sammelBeleg != null) {
                        sammelBeleg = this.convertBillBeanToBill(billBean.sammelBeleg);
                    }
                    if (billBean.tagesBeleg != null) {
                        tagesBeleg = this.convertBillBeanToBill(billBean.tagesBeleg);
                    }
                    if (billBean.monatsBeleg != null) {
                        monatsBeleg = this.convertBillBeanToBill(billBean.monatsBeleg);
                    }
                    if (billBean.jahresBeleg != null) {
                        jahresBeleg = this.convertBillBeanToBill(billBean.jahresBeleg);
                    }
                    if (billBean.incomeBean != null) {
                        income = this.convertToIncome(billBean.incomeBean);
                    }
                    return new bill_1.Bill(billBean.cashBoxID, billBean.receiptIdentifier, RentalService.createDateTime(billBean.receiptDateAndTime), billBean.sumTaxSetNormal, billBean.sumTaxSetErmaessigt1, billBean.sumTaxSetErmaessigt2, billBean.sumTaxSetNull, billBean.sumTaxSetBesonders, billBean.encryptedTurnoverValue, billBean.signatureCertificateSerialNumber, billBean.signatureValuePreviousReceipt, new bill_1.Company(billBean.company.name, billBean.company.street, billBean.company.zip, billBean.company.city, billBean.company.country, billBean.company.phone, billBean.company.mail, billBean.company.atu), billBean.sumTotal, taxSetElements, sammelBeleg, new Date(billBean.sammelBelegStart), new Date(billBean.sammelBelegEnd), income, tagesBeleg, monatsBeleg, jahresBeleg, billBean.receiptType, billBean.jwsCompact, billBean.signatureDeviceAvailable);
                };
                ;
                RentalService.prototype.convertToIncome = function (incomeBean) {
                    var incomeProductGroups = [];
                    incomeBean.incomeElements.forEach(function (pg) { return incomeProductGroups.push(new income_1.IncomeProductGroup(pg.name, pg.income, pg.taxPercent, pg.priority)); });
                    var taxElements = [];
                    incomeBean.taxElements.forEach(function (te) { return taxElements.push(new income_1.TaxElement(te.taxPercent, te.priority, te.price, te.priceBeforeTax, te.priceTax)); });
                    return new income_1.Income(RentalService.createDate(incomeBean.start), RentalService.createDate(incomeBean.end), incomeBean.totalIncome, incomeProductGroups, taxElements);
                };
                RentalService.createDate = function (jsonDate) {
                    return new Date(jsonDate + "T00:00:00.000Z");
                };
                RentalService.createDateTime = function (jsonDateTime) {
                    return new Date(jsonDateTime);
                };
                RentalService.prototype.getSignatureDeviceAvailableText = function () {
                    return this.signatureDeviceAvailableText;
                };
                RentalService.prototype.setSignatureDeviceAvailableText = function (available, date) {
                    console.log('setSignatureDeviceAvailableText: ' + available + ', ' + date);
                    if (available === false) {
                        console.log('    1');
                        this.signatureDeviceAvailableText = this.pp.printDateAndTime(date) + ' Signatureinrichtung ist ausgefallen - Meldung an BMF wenn länger als 48 Stunden!';
                    }
                    else {
                        console.log('    2');
                        this.signatureDeviceAvailableText = "";
                    }
                };
                RentalService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [http_1.Http, config_service_1.ConfigService, promotion_service_1.PromotionService, error_service_1.ErrorService, info_service_1.InfoService, printer_1.Printer, prettyprinter_1.PrettyPrinter, journal_service_1.JournalService])
                ], RentalService);
                return RentalService;
            }());
            exports_1("RentalService", RentalService);
        }
    }
});
