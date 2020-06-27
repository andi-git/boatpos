System.register(["angular2/core", "./info.service", "../model/receiptElement", "angular2/src/facade/lang", "angular2/http", "./config.service", "../model/sale", "../model/bill", "../printer", "./error.service", "./journal.service", "../prettyprinter"], function(exports_1, context_1) {
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
    var core_1, info_service_1, receiptElement_1, lang_1, http_1, config_service_1, sale_1, bill_1, printer_1, error_service_1, journal_service_1, prettyprinter_1;
    var SaleService;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (info_service_1_1) {
                info_service_1 = info_service_1_1;
            },
            function (receiptElement_1_1) {
                receiptElement_1 = receiptElement_1_1;
            },
            function (lang_1_1) {
                lang_1 = lang_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (config_service_1_1) {
                config_service_1 = config_service_1_1;
            },
            function (sale_1_1) {
                sale_1 = sale_1_1;
            },
            function (bill_1_1) {
                bill_1 = bill_1_1;
            },
            function (printer_1_1) {
                printer_1 = printer_1_1;
            },
            function (error_service_1_1) {
                error_service_1 = error_service_1_1;
            },
            function (journal_service_1_1) {
                journal_service_1 = journal_service_1_1;
            },
            function (prettyprinter_1_1) {
                prettyprinter_1 = prettyprinter_1_1;
            }],
        execute: function() {
            SaleService = (function () {
                function SaleService(http, infoService, errorService, configService, printer, pp) {
                    this.http = http;
                    this.infoService = infoService;
                    this.errorService = errorService;
                    this.configService = configService;
                    this.printer = printer;
                    this.pp = pp;
                    this.numberInput = "";
                    this.receiptElements = [];
                    this.signatureDeviceAvailableText = "";
                }
                SaleService.prototype.getNumberInput = function () {
                    return this.numberInput;
                };
                SaleService.prototype.setNumberInput = function (numberInput) {
                    if ('<' === numberInput) {
                        if (lang_1.isPresent(this.numberInput) && this.numberInput.length > 0) {
                            this.numberInput = this.numberInput.substr(0, this.numberInput.length - 1);
                            this.infoService.event().emit("Letzte Eingabe gelöscht.");
                        }
                    }
                    else {
                        this.numberInput = (this.numberInput == null ? "" : this.numberInput) + numberInput;
                        this.infoService.event().emit("Nummer eingegeben.");
                    }
                };
                SaleService.prototype.cancelLastElement = function () {
                    this.receiptElements.pop();
                    this.infoService.event().emit("Letztes Element gelöscht.");
                };
                SaleService.prototype.cancelAllElements = function () {
                    this.receiptElements = [];
                    this.infoService.event().emit("Alle Elemente gelöscht.");
                };
                SaleService.prototype.getSignatureDeviceAvailableText = function () {
                    return this.signatureDeviceAvailableText;
                };
                SaleService.prototype.setSignatureDeviceAvailableText = function (available, date) {
                    if (available === false) {
                        this.signatureDeviceAvailableText = this.pp.printDateAndTime(date) + ' Signatureinrichtung ist ausgefallen - Meldung an BMF wenn länger als 48 Stunden!';
                    }
                    else {
                        this.signatureDeviceAvailableText = "";
                    }
                };
                SaleService.prototype.bill = function (paymentMethod) {
                    if (lang_1.isPresent(this.receiptElements) && this.receiptElements.length > 0) {
                        this.saleWithPaymentMethodType("Standard-Beleg", paymentMethod, this.receiptElements);
                    }
                    else {
                        this.errorService.event().emit("Rechnung wurde nicht gedruckt - kein Produkt ausgewählt.");
                    }
                };
                SaleService.prototype.cancelBill = function () {
                    if (lang_1.isPresent(this.receiptElements) && this.receiptElements.length > 0) {
                        this.receiptElements.forEach(function (re) {
                            re.totalPrice = (-1) * re.totalPrice;
                        });
                        this.sale("Storno-Beleg", this.receiptElements);
                    }
                    else {
                        this.errorService.event().emit("Rechnung wurde nicht gedruckt - kein Produkt ausgewählt.");
                    }
                };
                SaleService.prototype.sale = function (receiptType, receiptElements) {
                    this.saleWithPaymentMethodType(receiptType, "CASH", receiptElements);
                };
                SaleService.prototype.saleWithPaymentMethodType = function (receiptType, paymentMethodType, receiptElements) {
                    var _this = this;
                    this.http.post(this.configService.getBackendUrl() + 'rest/sale', JSON.stringify(new sale_1.Sale(paymentMethodType, receiptType, receiptElements)), { headers: this.configService.getDefaultHeader() })
                        .map(function (res) {
                        return res.json();
                    })
                        .map(function (billBean) {
                        return _this.convertBillBeanToBill(billBean);
                    }).subscribe(function (bill) {
                        _this.setSignatureDeviceAvailableText(bill.signatureDeviceAvailable, bill.receiptDateAndTime);
                        _this.printer.printBill(bill, _this.configService.getPrinterIp());
                        _this.reset();
                        _this.infoService.event().emit("Rechnung '" + bill.receiptIdentifier + "' wurde gedruckt.");
                    }, function (error) {
                        _this.reset();
                        console.log("error: " + JSON.stringify(error));
                        _this.errorService.event().emit("Rechnung konnte NICHT erstellt werden - Vorgang wurde abgebrochen!");
                    });
                };
                SaleService.prototype.startBeleg = function () {
                    var _this = this;
                    this.checkIfStarbelegMustBePrinted().subscribe(function (check) {
                        if (check === true) {
                            _this.sale("Start-Beleg");
                        }
                    });
                };
                SaleService.prototype.schlussBeleg = function () {
                    this.sale("Schluss-Beleg");
                };
                SaleService.prototype.nullBeleg = function () {
                    this.sale("Null-Beleg");
                };
                SaleService.prototype.tagesBeleg = function () {
                    this.sale("Tages-Beleg");
                };
                SaleService.prototype.monatsBeleg = function () {
                    this.sale("Monats-Beleg");
                };
                SaleService.prototype.jahresBeleg = function () {
                    this.sale("Jahres-Beleg");
                };
                SaleService.prototype.reset = function () {
                    this.deleteNumberInput();
                    this.cancelAllElements();
                };
                SaleService.prototype.getReceiptElements = function () {
                    return this.receiptElements;
                };
                SaleService.prototype.deleteNumberInput = function () {
                    this.numberInput = "";
                    this.infoService.event().emit("Eingabe gelöscht.");
                };
                SaleService.prototype.chooseProduct = function (product) {
                    if (lang_1.isPresent(product)) {
                        if (product.generic === true) {
                            if (lang_1.isPresent(this.numberInput) && this.numberInput != "") {
                                this.receiptElements.push(new receiptElement_1.ReceiptElement(Number.parseFloat(this.numberInput), 1, product));
                                this.numberInput = "";
                                this.infoService.event().emit("Produktgruppe '" + product.name + "' ausgewählt.");
                            }
                            else {
                                this.infoService.event().emit("Kein Preis für die Produktgruppe '" + product.name + "' ausgewählt.");
                            }
                        }
                        else {
                            var amount = 1;
                            if (lang_1.isPresent(this.numberInput) && this.numberInput != "") {
                                amount = Number.parseFloat(this.numberInput);
                            }
                            this.receiptElements.push(new receiptElement_1.ReceiptElement(Number.parseFloat(product.price * amount), amount, product));
                            this.numberInput = "";
                            this.infoService.event().emit("Produkt '" + product.name + "' ausgewählt.");
                        }
                    }
                    else {
                        this.infoService.event().emit("Kein Produkt ausgewählt.");
                    }
                };
                SaleService.prototype.getSum = function () {
                    var sum = 0;
                    this.receiptElements.forEach(function (re) { return sum += re.totalPrice; });
                    return sum;
                };
                SaleService.prototype.checkIfStarbelegMustBePrinted = function () {
                    // call the rest-service
                    return this.http.get(this.configService.getBackendUrl() + 'rest/receipt/start/check', { headers: this.configService.getDefaultHeader() })
                        .map(function (res) { return res.text() === 'true'; });
                };
                SaleService.prototype.convertBillBeanToBill = function (billBean) {
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
                        income = journal_service_1.JournalService.convertToIncome(billBean.incomeBean);
                    }
                    return new bill_1.Bill(billBean.cashBoxID, billBean.receiptIdentifier, new Date(billBean.receiptDateAndTime), billBean.sumTaxSetNormal, billBean.sumTaxSetErmaessigt1, billBean.sumTaxSetErmaessigt2, billBean.sumTaxSetNull, billBean.sumTaxSetBesonders, billBean.encryptedTurnoverValue, billBean.signatureCertificateSerialNumber, billBean.signatureValuePreviousReceipt, new bill_1.Company(billBean.company.name, billBean.company.street, billBean.company.zip, billBean.company.city, billBean.company.country, billBean.company.phone, billBean.company.mail, billBean.company.atu), billBean.sumTotal, taxSetElements, sammelBeleg, new Date(billBean.sammelBelegStart), new Date(billBean.sammelBelegEnd), income, tagesBeleg, monatsBeleg, jahresBeleg, billBean.receiptType, billBean.jwsCompact, billBean.signatureDeviceAvailable);
                };
                ;
                SaleService.prototype.printReceipt = function (receiptId) {
                    var _this = this;
                    // call the rest-service
                    return this.http.get(this.configService.getBackendUrl() + 'rest/receipt/id/' + receiptId, { headers: this.configService.getDefaultHeader() })
                        .map(function (res) {
                        return res.json();
                    })
                        .map(function (billBean) {
                        return _this.convertBillBeanToBill(billBean);
                    }).subscribe(function (bill) {
                        _this.printer.printBill(bill, _this.configService.getPrinterIp());
                        _this.reset();
                        _this.infoService.event().emit("Rechnung '" + bill.receiptIdentifier + "' wurde gedruckt.");
                    }, function (error) {
                        _this.reset();
                        console.log("error: " + JSON.stringify(error));
                        _this.errorService.event().emit("Rechnung konnte NICHT gedruckt werden - Vorgang wurde abgebrochen!");
                    });
                };
                SaleService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [http_1.Http, info_service_1.InfoService, error_service_1.ErrorService, config_service_1.ConfigService, printer_1.Printer, prettyprinter_1.PrettyPrinter])
                ], SaleService);
                return SaleService;
            }());
            exports_1("SaleService", SaleService);
        }
    }
});
