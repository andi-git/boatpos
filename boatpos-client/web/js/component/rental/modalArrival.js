System.register(["angular2/core", "angular2/common", "lib/angular2-modal", "angular2/src/facade/lang", "../../model/payment"], function(exports_1, context_1) {
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
    var core_1, common_1, angular2_modal_1, lang_1, payment_1;
    var ModalArrivalContext, ModalArrival, InputMethod;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (common_1_1) {
                common_1 = common_1_1;
            },
            function (angular2_modal_1_1) {
                angular2_modal_1 = angular2_modal_1_1;
            },
            function (lang_1_1) {
                lang_1 = lang_1_1;
            },
            function (payment_1_1) {
                payment_1 = payment_1_1;
            }],
        execute: function() {
            ModalArrivalContext = (function () {
                function ModalArrivalContext(rentalNumber, rentalService, promotionService, keyBinding, printer, pp, printerIp) {
                    this.rentalNumber = rentalNumber;
                    this.rentalService = rentalService;
                    this.promotionService = promotionService;
                    this.keyBinding = keyBinding;
                    this.printer = printer;
                    this.pp = pp;
                    this.printerIp = printerIp;
                }
                return ModalArrivalContext;
            }());
            exports_1("ModalArrivalContext", ModalArrivalContext);
            ModalArrival = (function () {
                function ModalArrival(dialog, modelContentData) {
                    var _this = this;
                    this.commitmentReturn = false;
                    this.classInputPrice = "input input-arrival-price-selected";
                    this.classInputGetMoney = "input input-get-money";
                    this.inputMethod = InputMethod.PriceToPay;
                    this.dialog = dialog;
                    this.keyBinding = modelContentData.keyBinding;
                    this.rentalService = modelContentData.rentalService;
                    this.promotionService = modelContentData.promotionService;
                    this.rentalNumber = modelContentData.rentalNumber;
                    this.printer = modelContentData.printer;
                    this.pp = modelContentData.pp;
                    this.printerIp = modelContentData.printerIp;
                    var map = {
                        'K': function () {
                            _this.cancel();
                        },
                        'P': function () {
                            _this.payCash();
                        },
                        'Q': function () {
                            _this.payCard();
                        },
                        'R': function () {
                            _this.reset();
                        },
                        'S': function () {
                            _this.switchInputMethod();
                        },
                        '.': function () {
                            _this.addToPrice('.');
                        }
                    };
                    map[this.promotionService.getHolliKnolli().keyBinding] = function (e) {
                        _this.holliKnolli();
                    };
                    for (var i = 0; i <= 9; i++) {
                        map[i] = function (e) {
                            _this.addToPrice(String.fromCharCode(e.charCode));
                        };
                    }
                    this.keyBinding.addBindingForDialogInfo(map);
                    this.keyBinding.focusDialogInfo();
                    this.arrive();
                }
                ModalArrival.prototype.arrive = function () {
                    var _this = this;
                    this.rentalService.arrive(this.rentalNumber).subscribe(function (rental) {
                        _this.rental = rental;
                        if (_this.rental.deleted === true) {
                            _this.state = "del";
                        }
                        else if (_this.rental.finished === true) {
                            _this.state = "alf";
                        }
                        else {
                            _this.state = "ok";
                            // set price
                            _this.price = _this.pp.ppPrice(_this.rental.priceCalculatedAfter, "");
                            _this.isOriginalPrice = true;
                            _this.originalPrice = _this.pp.ppPrice(_this.rental.priceCalculatedAfter, "");
                            // add special warning if commitment has to be returned and add the return value for special
                            // commitments
                            if (lang_1.isPresent(_this.rental && lang_1.isPresent(_this.rental.commitments))) {
                                _this.rental.commitments.forEach(function (commitment) {
                                    // return commitment
                                    if (commitment.paper === true) {
                                        _this.commitmentReturn = true;
                                    }
                                    // € 50,-
                                    if (commitment.name === "EUR 50,-") {
                                        _this.getMoney = _this.pp.ppPrice(50, "");
                                        _this.calculateReturnMoney();
                                    }
                                    // € 100,-
                                    if (commitment.name === "EUR 100,-") {
                                        _this.getMoney = _this.pp.ppPrice(100, "");
                                        _this.calculateReturnMoney();
                                    }
                                });
                            }
                        }
                    }, function () {
                        _this.state = "na";
                    });
                };
                ModalArrival.prototype.addToPrice = function (s) {
                    if (this.inputMethod === InputMethod.PriceToPay) {
                        if (this.isOriginalPrice === true) {
                            this.price = "";
                            this.isOriginalPrice = false;
                        }
                        this.price = (this.price == null ? "" : this.price) + s;
                    }
                    else if (this.inputMethod === InputMethod.GetMoney) {
                        this.getMoney = (this.getMoney == null ? "" : this.getMoney) + s;
                        this.calculateReturnMoney();
                    }
                };
                ModalArrival.prototype.calculateReturnMoney = function () {
                    if (Number.parseFloat(this.getMoney) - Number.parseFloat(this.price) > 0) {
                        this.returnMoney = this.pp.ppPrice(Number.parseFloat(this.getMoney) - Number.parseFloat(this.price), "");
                    }
                };
                ModalArrival.prototype.getBoatName = function () {
                    return lang_1.isPresent(this.rental) ? this.rental.boat.name : "";
                };
                ModalArrival.prototype.getCommitments = function () {
                    if (lang_1.isPresent(this.rental)) {
                        return this.pp.printCommitments(this.rental.commitments);
                    }
                    return "";
                };
                ModalArrival.prototype.printDay = function () {
                    if (lang_1.isPresent(this.rental)) {
                        return this.pp.printDate(this.rental.day);
                    }
                };
                ModalArrival.prototype.printDeparture = function () {
                    return lang_1.isPresent(this.rental) ? this.pp.printTime(this.rental.departure) : "";
                };
                ModalArrival.prototype.printArrival = function () {
                    var result = "keine Ankunftszeit vorhanden";
                    if (lang_1.isPresent(this.rental) && lang_1.isPresent(this.rental.arrival) && this.rental.arrival.getFullYear() > 1970) {
                        result = this.pp.printTime(this.rental.arrival);
                    }
                    return result;
                };
                ModalArrival.prototype.printPricePaidBefore = function () {
                    return lang_1.isPresent(this.rental) ? this.pp.ppPrice(this.rental.pricePaidBefore, null) : "";
                };
                ModalArrival.prototype.printPromotionBefore = function () {
                    var result = "keine Aktion vorhanden";
                    if (lang_1.isPresent(this.rental) && lang_1.isPresent(this.rental.promotionBefore)) {
                        result = this.rental.promotionBefore.name + " (Guthaben: " + this.rental.promotionBefore.timeCredit + " Minuten)";
                    }
                    return result;
                };
                ModalArrival.prototype.printPromotionAfter = function () {
                    var result = "keine Aktion vorhanden";
                    if (lang_1.isPresent(this.rental) && lang_1.isPresent(this.rental.promotionAfter)) {
                        result = this.rental.promotionAfter.name;
                    }
                    return result;
                };
                ModalArrival.prototype.printTimeOfTravel = function () {
                    return (lang_1.isPresent(this.rental)) ? this.rental.ppTimeOfTravel() : "";
                };
                ModalArrival.prototype.printTimeOfTravelCalculated = function () {
                    return (lang_1.isPresent(this.rental)) ? this.rental.ppTimeOfTravelCalculated() : "";
                };
                ModalArrival.prototype.close = function ($event) {
                    $event.stopPropagation();
                    //noinspection TypeScriptUnresolvedFunction
                    this.dialog.close(true);
                };
                ModalArrival.prototype.cancel = function () {
                    //noinspection TypeScriptUnresolvedFunction
                    this.dialog.dismiss();
                };
                ModalArrival.prototype.getCommitmentReturnString = function () {
                    if (this.commitmentReturn === true) {
                        return "Einsatz retour!";
                    }
                    else {
                        return "";
                    }
                };
                ModalArrival.prototype.reset = function () {
                    this.price = this.originalPrice;
                    this.isOriginalPrice = true;
                    this.returnMoney = "";
                    this.getMoney = "";
                };
                ModalArrival.prototype.payCash = function () {
                    this.pay(new payment_1.Payment(this.rentalNumber, Number.parseFloat(this.price), "cash", "Standard-Beleg"));
                };
                ModalArrival.prototype.payCard = function () {
                    this.pay(new payment_1.Payment(this.rentalNumber, Number.parseFloat(this.price), "card", "Standard-Beleg"));
                };
                ModalArrival.prototype.pay = function (payment) {
                    var _this = this;
                    this.rentalService.payAfter(payment).subscribe(function (bill) {
                        _this.rentalService.setSignatureDeviceAvailableText(bill.signatureDeviceAvailable, bill.receiptDateAndTime);
                        _this.printer.printBill(bill, _this.printerIp);
                        //noinspection TypeScriptUnresolvedFunction
                        _this.dialog.close("ok");
                    }, function (err) {
                        _this.cancel();
                    });
                };
                ModalArrival.prototype.switchInputMethod = function () {
                    if (this.inputMethod === InputMethod.PriceToPay) {
                        this.inputMethod = InputMethod.GetMoney;
                        this.classInputPrice = "input input-arrival-price";
                        this.classInputGetMoney = "input input-get-money-selected";
                    }
                    else {
                        this.inputMethod = InputMethod.PriceToPay;
                        this.classInputPrice = "input input-arrival-price-selected";
                        this.classInputGetMoney = "input input-get-money";
                    }
                };
                ModalArrival.prototype.holliKnolli = function () {
                    var _this = this;
                    if (lang_1.isPresent(this.rental.promotionAfter)) {
                        this.rentalService.removeHolliKnolli(this.rental).subscribe(function (rental) {
                            _this.rental = rental;
                            _this.originalPrice = _this.pp.ppPrice(_this.rental.priceCalculatedAfter, "");
                            _this.reset();
                        });
                    }
                    else {
                        this.rentalService.addHolliKnolli(this.rental).subscribe(function (rental) {
                            _this.rental = rental;
                            _this.originalPrice = _this.pp.ppPrice(_this.rental.priceCalculatedAfter, "");
                            _this.reset();
                        });
                    }
                };
                ModalArrival = __decorate([
                    core_1.Component({
                        selector: 'modal-content',
                        directives: [common_1.NgIf],
                        template: "<div class=\"modal-header\">\n        <h2 class=\"header header-main\">Verrechnung Nummer {{rentalNumber}}</h2>\n        </div>\n        <div class=\"modal-body\" *ngIf=\"state === 'ok'\">\n            <p><span class=\"text-grey\">Boot:</span> {{getBoatName()}}</p>\n            <p><span class=\"text-grey\">Einsatz:</span> {{getCommitments()}} <span [hidden]=\"!commitmentReturn\" class=\"commitment-return\">{{getCommitmentReturnString()}}</span></p>\n            <p><span class=\"text-grey\">Datum:</span> {{printDay()}}</p>\n            <p><span class=\"text-grey\">Abfahrt:</span> {{printDeparture()}}</p>\n            <p><span class=\"text-grey\">Ankunft:</span> {{printArrival()}}</p>\n            <p><span class=\"text-grey\">Fahrzeit:</span> {{printTimeOfTravel()}} (verrechnet: {{printTimeOfTravelCalculated()}})</p>\n            <p><span class=\"text-grey\">Aktion bevor:</span> {{printPromotionBefore()}}</p>\n            <p><span class=\"text-grey\">Preis bereits bezahlt:</span> {{printPricePaidBefore()}}</p>\n            <p><span class=\"text-grey\">Aktion danach:</span> {{printPromotionAfter()}}</p>\n            <div class=\"container-money\">\n                <table class=\"table-no-style\">\n                    <tr class=\"table-no-style\">\n                        <td valign=\"top\" class=\"table-no-style\">\n                            <div class=\"container-price-to-pay\">\n                                <span class=\"text-normal\">Zu bezahlender Betrag:</span>\n                                <input [class]=\"classInputPrice\" [(ngModel)]=\"price\" placeholder=\"Preis\"/>\n                            </div>\n                        </td>\n                        <td class=\"table-no-style\">\n                            <div class=\"container-get-money\">\n                                <span class=\"text-small\">Bezahlter Betrag:</span>\n                                <input [class]=\"classInputGetMoney\" [(ngModel)]=\"getMoney\" placeholder=\"Bezahlt\"/>\n                            </div>\n                            <div class=\"container-return-money\">\n                                <span class=\"text-small\">Betrag retour:</span>\n                                <input class=\"input input-return-money\" [(ngModel)]=\"returnMoney\" placeholder=\"Retour\"/>\n                            </div>\n                        </td>\n                    </tr>\n                </table>\n            </div>\n        </div>\n        <div class=\"modal-body\" *ngIf=\"state === 'na'\">\n            <p>Keine Vermietung mit Nummer {{rentalNumber}} vorhanden!</p>\n        </div>\n        <div class=\"modal-body\" *ngIf=\"state === 'del'\">\n            <p>Keine Vermietung mit Nummer {{rentalNumber}} vorhanden!</p>\n        </div>\n        <div class=\"modal-body\" *ngIf=\"state === 'alf'\">\n            <p>Vermietung mit Nummer {{rentalNumber}} wurde bereits abgerechnet!</p>\n        </div>\n        <div class=\"modal-footer\">\n            <button class=\"buttonSmall button-action\" (click)=\"holliKnolli()\" *ngIf=\"state === 'ok'\">HK</button>\n            <button class=\"buttonSmall button-action\" (click)=\"reset()\" *ngIf=\"state === 'ok'\">Zur\u00FCcksetzen</button>\n            <button class=\"buttonSmall button-ok\" (click)=\"payCash()\" *ngIf=\"state === 'ok'\">Bar</button>\n            <button class=\"buttonSmall button-ok\" (click)=\"payCard()\" *ngIf=\"state === 'ok'\">Karte</button>\n            <button class=\"buttonSmall button-cancel\" (click)=\"close($event)\">Schlie\u00DFen</button>\n        </div>",
                        styles: ["\n        .input-arrival-price {\n            font-size: 7em;\n            font-weight: 900;\n            line-height: 2em;\n        }\n\n        .input-arrival-price-selected {\n            font-size: 7em;\n            font-weight: 900;\n            line-height: 2em;\n            background-color: #81BEF7;\n        }\n\n        .input-get-money {\n            font-size: 4em;\n            font-weight: 200;\n        }\n\n        .input-get-money-selected {\n            font-size: 4em;\n            font-weight: 200;\n            background-color: #81BEF7;\n        }\n\n        .input-return-money {\n            font-size: 4em;\n            font-weight: 200;\n        }\n\n        .container-money {\n        }\n\n        .container-price-to-pay {\n            margin: 0 1em 0 0;\n        }\n\n        .container-get-money {\n        }\n\n        .container-return-money {\n        }\n\n        .text-normal {\n            font-size: 2em;\n        }\n\n        .text-small {\n            font-size: 1.5em;\n        }\n\n        .commitment-return {\n            font-size: 1em;\n            color: black;\n            vertical-align: middle;\n            text-align: center;\n            padding: 0 0.5em 0 0.5em;\n            margin: 0 0 0 0;\n            background-color: white;\n            border-radius: 20px 20px 20px 20px;\n            border: 2px solid black;\n            background-color: #ff5050;\n        }\n        "],
                    }), 
                    __metadata('design:paramtypes', [(typeof (_a = typeof angular2_modal_1.ModalDialogInstance !== 'undefined' && angular2_modal_1.ModalDialogInstance) === 'function' && _a) || Object, (typeof (_b = typeof angular2_modal_1.ICustomModal !== 'undefined' && angular2_modal_1.ICustomModal) === 'function' && _b) || Object])
                ], ModalArrival);
                return ModalArrival;
                var _a, _b;
            }());
            exports_1("ModalArrival", ModalArrival);
            (function (InputMethod) {
                InputMethod[InputMethod["PriceToPay"] = 0] = "PriceToPay";
                InputMethod[InputMethod["GetMoney"] = 1] = "GetMoney";
            })(InputMethod || (InputMethod = {}));
        }
    }
});
