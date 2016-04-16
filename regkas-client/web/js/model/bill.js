System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var Bill, Company, TaxSetElement;
    return {
        setters:[],
        execute: function() {
            Bill = (function () {
                function Bill(cashBoxId, receiptIdentifier, receiptDateAndTime, sumTaxSetNormal, sumTaxSetErmaessigt1, sumTaxSetErmaessigt2, sumTaxSetNull, sumTaxSetBesonders, encryptedTurnoverValue, signatureCertificateSerialNumber, signatureValuePreviousReceipt, company, sumTotal, taxSetElements) {
                    var _this = this;
                    this.taxSetElements = [];
                    this.cashBoxId = cashBoxId;
                    this.receiptIdentifier = receiptIdentifier;
                    this.receiptDateAndTime = receiptDateAndTime;
                    this.sumTaxSetNormal = sumTaxSetNormal;
                    this.sumTaxSetErmaessigt1 = sumTaxSetErmaessigt1;
                    this.sumTaxSetErmaessigt2 = sumTaxSetErmaessigt2;
                    this.sumTaxSetNull = sumTaxSetNull;
                    this.sumTaxSetBesonders = sumTaxSetBesonders;
                    this.encryptedTurnoverValue = encryptedTurnoverValue;
                    this.signatureCertificateSerialNumber = signatureCertificateSerialNumber;
                    this.signatureValuePreviousReceipt = signatureValuePreviousReceipt;
                    this.company = company;
                    this.sumTotal = sumTotal;
                    if (taxSetElements != null) {
                        taxSetElements.forEach(function (taxSetElement) { return _this.taxSetElements.push(taxSetElement); });
                    }
                }
                Bill.prototype.toString = function () {
                    return JSON.stringify(this);
                };
                return Bill;
            }());
            exports_1("Bill", Bill);
            Company = (function () {
                function Company(name, street, zip, city, country, phone, mail, atu) {
                    this.name = name;
                    this.street = street;
                    this.zip = zip;
                    this.city = city;
                    this.country = country;
                    this.phone = phone;
                    this.mail = mail;
                    this.atu = atu;
                }
                Company.prototype.toString = function () {
                    return JSON.stringify(this);
                };
                return Company;
            }());
            exports_1("Company", Company);
            TaxSetElement = (function () {
                function TaxSetElement(name, taxPercent, amount, pricePreTax, priceAfterTax, priceTax) {
                    this.name = name;
                    this.taxPercent = taxPercent;
                    this.amount = amount;
                    this.pricePreTax = pricePreTax;
                    this.priceAfterTax = priceAfterTax;
                    this.priceTax = priceTax;
                }
                TaxSetElement.prototype.toString = function () {
                    return JSON.stringify(this);
                };
                return TaxSetElement;
            }());
            exports_1("TaxSetElement", TaxSetElement);
        }
    }
});
