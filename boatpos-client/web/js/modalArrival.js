System.register(['angular2/core', 'angular2/common', "lib/angular2-modal", "angular2/src/facade/lang", "./payment"], function(exports_1) {
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
    var ModalArrivalContext, ModalArrival;
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
                function ModalArrivalContext(rentalNumber, rentalService, keyBinding, pp) {
                    this.rentalNumber = rentalNumber;
                    this.rentalService = rentalService;
                    this.keyBinding = keyBinding;
                    this.pp = pp;
                }
                return ModalArrivalContext;
            })();
            exports_1("ModalArrivalContext", ModalArrivalContext);
            ModalArrival = (function () {
                function ModalArrival(dialog, modelContentData) {
                    var _this = this;
                    this.dialog = dialog;
                    this.keyBinding = modelContentData.keyBinding;
                    this.rentalService = modelContentData.rentalService;
                    this.rentalNumber = modelContentData.rentalNumber;
                    this.pp = modelContentData.pp;
                    var map = {
                        'K': function () {
                            _this.cancel();
                        },
                        'M': function () {
                            _this.reset();
                        },
                        'O': function () {
                            _this.pay();
                        },
                        '.': function () {
                            _this.addToPrice('.');
                        }
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
                            _this.price = _this.pp.ppPrice(_this.rental.priceCalculatedAfter, "");
                            _this.isOriginalPrice = true;
                            _this.originalPrice = _this.pp.ppPrice(_this.rental.priceCalculatedAfter, "");
                        }
                    }, function () {
                        _this.state = "na";
                    });
                };
                ModalArrival.prototype.addToPrice = function (s) {
                    if (this.isOriginalPrice === true) {
                        this.price = "";
                        this.isOriginalPrice = false;
                    }
                    this.price = (this.price == null ? "" : this.price) + s;
                };
                ModalArrival.prototype.getBoatName = function () {
                    return lang_1.isPresent(this.rental) ? this.rental.boat.name : "";
                };
                ModalArrival.prototype.getCommitments = function () {
                    var commitments = "";
                    if (lang_1.isPresent(this.rental)) {
                        var first = true;
                        this.rental.commitments.forEach(function (commitment) {
                            if (!first) {
                                commitments += ",";
                            }
                            commitments += commitment.name;
                            first = false;
                        });
                    }
                    return commitments;
                };
                ModalArrival.prototype.printDeparture = function () {
                    return lang_1.isPresent(this.rental) ? this.printDate(this.rental.departure) : "";
                };
                ModalArrival.prototype.printArrival = function () {
                    var result = "keine Ankunftszeit vorhanden";
                    if (lang_1.isPresent(this.rental) && lang_1.isPresent(this.rental.arrival) && this.rental.arrival.getUTCFullYear() > 1970) {
                        result = this.printDate(this.rental.arrival);
                    }
                    return result;
                };
                ModalArrival.prototype.printDate = function (date) {
                    var dateString = "";
                    if (lang_1.isPresent(date) && date.getUTCFullYear() > 1970) {
                        return this.pp.pp2Pos(date.getUTCHours()) + ":" + this.pp.pp2Pos(date.getUTCMinutes()) + " Uhr";
                    }
                    return dateString;
                };
                ModalArrival.prototype.printPricePaidBefore = function () {
                    return lang_1.isPresent(this.rental) ? this.pp.ppPrice(this.rental.pricePaidBefore, "") : "";
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
                    return (lang_1.isPresent(this.rental)) ? this.rental.timeOfTravel + " Minuten" : "";
                };
                ModalArrival.prototype.printTimeOfTravelCalculated = function () {
                    return (lang_1.isPresent(this.rental)) ? this.rental.timeOfTravelCalculated + " Minuten" : "";
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
                ModalArrival.prototype.reset = function () {
                    this.price = this.originalPrice;
                    this.isOriginalPrice = true;
                };
                ModalArrival.prototype.pay = function () {
                    var _this = this;
                    var payment = new payment_1.Payment(this.rentalNumber, Number.parseFloat(this.price));
                    this.rentalService.payAfter(payment).subscribe(function (rental) {
                        _this.rental = rental;
                        //noinspection TypeScriptUnresolvedFunction
                        _this.dialog.close("ok");
                    }, function () {
                        _this.cancel();
                    });
                };
                ModalArrival = __decorate([
                    core_1.Component({
                        selector: 'modal-content',
                        directives: [common_1.NgIf],
                        template: "<div class=\"modal-header\">\n        <h2 class=\"header header-main\">Verrechnung Nummer {{rentalNumber}}</h2>\n        </div>\n        <div class=\"modal-body\" *ngIf=\"state === 'ok'\">\n            <p><span class=\"text-grey\">Boot:</span> {{getBoatName()}}</p>\n            <p><span class=\"text-grey\">Einsatz:</span> {{getCommitments()}}</p>\n            <p><span class=\"text-grey\">Abfahrt:</span> {{printDeparture()}}</p>\n            <p><span class=\"text-grey\">Ankunft:</span> {{printArrival()}}</p>\n            <p><span class=\"text-grey\">Fahrzeit:</span> {{printTimeOfTravel()}} (verrechnet: {{printTimeOfTravelCalculated()}})</p>\n            <p><span class=\"text-grey\">Aktion bevor:</span> {{printPromotionBefore()}}</p>\n            <p><span class=\"text-grey\">Preis bereits bezahlt:</span> {{printPricePaidBefore()}}</p>\n            <p><span class=\"text-grey\">Aktion danach:</span> {{printPromotionAfter()}}</p>\n            <p><span class=\"text-grey\">Zu bezahlender Betrag:</span></p>\n            <input class=\"input input-arrival-price\" [(ngModel)]=\"price\" placeholder=\"Preis\"/>\n        </div>\n        <div class=\"modal-body\" *ngIf=\"state === 'na'\">\n            <p>Keine Vermietung mit Nummer {{rentalNumber}} vorhanden!</p>\n        </div>\n        <div class=\"modal-body\" *ngIf=\"state === 'del'\">\n            <p>Keine Vermietung mit Nummer {{rentalNumber}} vorhanden!</p>\n        </div>\n        <div class=\"modal-body\" *ngIf=\"state === 'alf'\">\n            <p>Vermietung mit Nummer {{rentalNumber}} wurde bereits abgerechnet!</p>\n        </div>\n        <div class=\"modal-footer\">\n            <button class=\"buttonSmall button-action\" (click)=\"reset()\" *ngIf=\"state === 'ok'\">Zur\u00FCcksetzen</button>\n            <button class=\"buttonSmall button-ok\" (click)=\"pay()\" *ngIf=\"state === 'ok'\">Bezahlen</button>\n            <button class=\"buttonSmall button-cancel\" (click)=\"close($event)\">Schlie\u00DFen</button>\n        </div>",
                        styles: ["\n        .input-arrival-price {\n            font-size: 7em;\n            font-weight: 900;\n        }\n        "],
                    }), 
                    __metadata('design:paramtypes', [(typeof (_a = typeof angular2_modal_1.ModalDialogInstance !== 'undefined' && angular2_modal_1.ModalDialogInstance) === 'function' && _a) || Object, (typeof (_b = typeof angular2_modal_1.ICustomModal !== 'undefined' && angular2_modal_1.ICustomModal) === 'function' && _b) || Object])
                ], ModalArrival);
                return ModalArrival;
                var _a, _b;
            })();
            exports_1("ModalArrival", ModalArrival);
        }
    }
});
