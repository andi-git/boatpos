System.register(["angular2/core", "./info.service", "../model/receiptElement", "angular2/src/facade/lang", "angular2/http", "./config.service", "../model/sale", "../model/bill", "../printer"], function(exports_1, context_1) {
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
    var core_1, info_service_1, receiptElement_1, lang_1, http_1, config_service_1, sale_1, bill_1, printer_1;
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
            }],
        execute: function() {
            SaleService = (function () {
                function SaleService(http, infoService, configService, printer) {
                    this.http = http;
                    this.infoService = infoService;
                    this.configService = configService;
                    this.printer = printer;
                    this.numberInput = "";
                    this.receiptElements = [];
                }
                SaleService.prototype.getNumberInput = function () {
                    return this.numberInput;
                };
                SaleService.prototype.setNumberInput = function (numberInput) {
                    if ('<' === numberInput) {
                        if (lang_1.isPresent(this.numberInput) && this.numberInput.length > 0) {
                            this.numberInput = this.numberInput.substr(0, this.numberInput.length - 1);
                            this.infoService.event().emit("Letzte Einfabe gelöscht.");
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
                SaleService.prototype.bill = function () {
                    var _this = this;
                    if (lang_1.isPresent(this.receiptElements) && this.receiptElements.length > 0) {
                        this.http.post(this.configService.getBackendUrl() + 'rest/sale', JSON.stringify(new sale_1.Sale("CASH", "Standard-Beleg", this.receiptElements)), { headers: this.configService.getDefaultHeader() })
                            .map(function (res) { return res.json(); })
                            .map(function (billBean) {
                            return _this.convertBillBeanToBill(billBean);
                        }).subscribe(function (bill) {
                            console.log("print bill");
                            _this.printer.printBill(bill, _this.configService.getPrinterIp());
                            _this.reset();
                            _this.infoService.event().emit("Rechnung '" + bill.receiptIdentifier + "' wurde gedruckt.");
                        });
                    }
                    else {
                        this.infoService.event().emit("Rechnung wurde nicht gedruckt - kein Produkt ausgewählt.");
                    }
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
                SaleService.prototype.convertBillBeanToBill = function (billBean) {
                    var taxSetElements = [];
                    billBean.billTaxSetElements.forEach(function (tse) {
                        taxSetElements.push(new bill_1.TaxSetElement(tse.name, tse.taxPercent, tse.amount, tse.pricePreTax, tse.priceAfterTax, tse.priceTax));
                    });
                    return new bill_1.Bill(billBean.cashBoxID, billBean.receiptIdentifier, new Date(billBean.receiptDateAndTime), billBean.sumTaxSetNormal, billBean.sumTaxSetErmaessigt1, billBean.sumTaxSetErmaessigt2, billBean.sumTaxSetNull, billBean.sumTaxSetBesonders, billBean.encryptedTurnoverValue, billBean.signatureCertificateSerialNumber, billBean.signatureValuePreviousReceipt, new bill_1.Company(billBean.company.name, billBean.company.street, billBean.company.zip, billBean.company.city, billBean.company.country, billBean.company.phone, billBean.company.mail, billBean.company.atu), billBean.sumTotal, taxSetElements);
                };
                ;
                SaleService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [http_1.Http, info_service_1.InfoService, config_service_1.ConfigService, printer_1.Printer])
                ], SaleService);
                return SaleService;
            }());
            exports_1("SaleService", SaleService);
        }
    }
});
