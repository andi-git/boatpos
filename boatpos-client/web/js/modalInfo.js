System.register(['angular2/core', 'angular2/common', "lib/angular2-modal", "angular2/src/facade/lang"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, common_1, angular2_modal_1, lang_1;
    var ModalInfoContext, ModalDelete;
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
            }],
        execute: function() {
            ModalInfoContext = (function () {
                function ModalInfoContext(rentalNumber, rentalService, keyBinding, pp) {
                    this.rentalNumber = rentalNumber;
                    this.rentalService = rentalService;
                    this.keyBinding = keyBinding;
                    this.pp = pp;
                }
                return ModalInfoContext;
            })();
            exports_1("ModalInfoContext", ModalInfoContext);
            ModalDelete = (function () {
                function ModalDelete(dialog, modelContentData) {
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
                            if (lang_1.isPresent(_this.rental) && _this.rental.deleted === true) {
                                _this.undoDelete();
                            }
                            else {
                                _this.delete();
                            }
                        },
                        'O': function () {
                            _this.cancel();
                        }
                    };
                    this.keyBinding.addBindingForDialogInfo(map);
                    this.keyBinding.focusDialogInfo();
                    this.rentalService.getRental(this.rentalNumber).subscribe(function (rental) {
                        _this.rental = rental;
                    }, function () {
                        _this.noRental = "Keine Vermietung mit Nummer " + _this.rentalNumber + " gefunden!";
                    });
                }
                ModalDelete.prototype.getBoatName = function () {
                    return lang_1.isPresent(this.rental) ? this.rental.boat.name : "";
                };
                ModalDelete.prototype.getCommitments = function () {
                    if (lang_1.isPresent(this.rental)) {
                        return this.pp.printCommitments(this.rental.commitments);
                    }
                    return "";
                };
                ModalDelete.prototype.getDeletedOrEmpty = function () {
                    return (lang_1.isPresent(this.rental) && this.rental.deleted === true) ? "gelöscht" : "";
                };
                ModalDelete.prototype.getDeletedJaNein = function () {
                    return (lang_1.isPresent(this.rental) && this.rental.deleted === true) ? "Ja" : "Nein";
                };
                ModalDelete.prototype.printFinishedJaNein = function () {
                    return (lang_1.isPresent(this.rental) && this.rental.finished === true) ? "Ja" : "Nein";
                };
                ModalDelete.prototype.printDeparture = function () {
                    return lang_1.isPresent(this.rental) ? this.pp.printTime(this.rental.departure) : "";
                };
                ModalDelete.prototype.printArrival = function () {
                    var result = "keine Ankunftszeit vorhanden";
                    if (lang_1.isPresent(this.rental) && lang_1.isPresent(this.rental.arrival) && this.rental.arrival.getUTCFullYear() > 1970) {
                        result = this.pp.printTime(this.rental.arrival);
                    }
                    return result;
                };
                ModalDelete.prototype.printDay = function () {
                    if (lang_1.isPresent(this.rental)) {
                        return this.pp.printDate(this.rental.day);
                    }
                };
                ModalDelete.prototype.printPricePaid = function () {
                    if (lang_1.isPresent(this.rental)) {
                        var summandA = 0;
                        if (!isNaN(this.rental.pricePaidBefore)) {
                            summandA = this.rental.pricePaidBefore;
                        }
                        var summandB = 0;
                        if (!isNaN(this.rental.pricePaidAfter)) {
                            summandB = this.rental.pricePaidAfter;
                        }
                        return this.pp.ppPrice(summandA + summandB, "€ ");
                    }
                    else {
                        return "";
                    }
                };
                ModalDelete.prototype.printPriceCalculatedBefore = function () {
                    return lang_1.isPresent(this.rental) ? this.pp.ppPrice(this.rental.priceCalculatedBefore, "€ ") : "";
                };
                ModalDelete.prototype.printPriceCalculatedAfter = function () {
                    return lang_1.isPresent(this.rental) ? this.pp.ppPrice(this.rental.priceCalculatedAfter, "€ ") : "";
                };
                ModalDelete.prototype.printPricePaidBefore = function () {
                    return lang_1.isPresent(this.rental) ? this.pp.ppPrice(this.rental.pricePaidBefore, "€ ") : "";
                };
                ModalDelete.prototype.printPricePaidAfter = function () {
                    return lang_1.isPresent(this.rental) ? this.pp.ppPrice(this.rental.pricePaidAfter, "€ ") : "";
                };
                ModalDelete.prototype.printPromotionBefore = function () {
                    var result = "keine Aktion vorhanden";
                    if (lang_1.isPresent(this.rental) && lang_1.isPresent(this.rental.promotionBefore)) {
                        result = this.rental.promotionBefore.name + " (Guthaben: " + this.rental.promotionBefore.timeCredit + " Minuten)";
                    }
                    return result;
                };
                ModalDelete.prototype.printPromotionAfter = function () {
                    var result = "keine Aktion vorhanden";
                    if (lang_1.isPresent(this.rental) && lang_1.isPresent(this.rental.promotionAfter)) {
                        result = this.rental.promotionAfter.name;
                    }
                    return result;
                };
                ModalDelete.prototype.printTimeOfTravel = function () {
                    return (lang_1.isPresent(this.rental)) ? this.rental.timeOfTravel + " Minuten" : "";
                };
                ModalDelete.prototype.close = function ($event) {
                    $event.stopPropagation();
                    //noinspection TypeScriptUnresolvedFunction
                    this.dialog.close(true);
                };
                ModalDelete.prototype.cancel = function () {
                    //noinspection TypeScriptUnresolvedFunction
                    this.dialog.dismiss();
                };
                ModalDelete.prototype.delete = function () {
                    var _this = this;
                    this.rentalService.deleteRental(this.rentalNumber).subscribe(function (rental) {
                        _this.rental = rental;
                    });
                };
                ModalDelete.prototype.undoDelete = function () {
                    var _this = this;
                    this.rentalService.undoDeleteRental(this.rentalNumber).subscribe(function (rental) {
                        _this.rental = rental;
                    });
                };
                ModalDelete = __decorate([
                    core_1.Component({
                        selector: 'modal-content',
                        directives: [common_1.NgIf],
                        template: "<div class=\"modal-header\">\n        <h2 class=\"header header-main\">Information \u00FCber Nummer {{rentalNumber}}</h2>\n        </div>\n        <div class=\"modal-body\" *ngIf=\"!noRental\">\n            <p><span class=\"text-grey\">Boot:</span> {{getBoatName()}}</p>\n            <p><span class=\"text-grey\">Einsatz:</span> {{getCommitments()}}</p>\n            <p><span class=\"text-grey\">Datum:</span> {{printDay()}}</p>\n            <p><span class=\"text-grey\">Abfahrt:</span> {{printDeparture()}}</p>\n            <p><span class=\"text-grey\">Ankunft:</span> {{printArrival()}}</p>\n            <p><span class=\"text-grey\">Fahrzeit:</span> {{printTimeOfTravel()}}</p>\n            <p><span class=\"text-grey\">Preis bezahlt:</span> {{printPricePaid()}}</p>\n            <p><span class=\"text-grey\">Preis bevor:</span> {{printPricePaidBefore()}}</p>\n            <p><span class=\"text-grey\">Preis danach:</span> {{printPricePaidAfter()}}</p>\n            <p><span class=\"text-grey\">Preis berechnet bevor:</span> {{printPriceCalculatedBefore()}}</p>\n            <p><span class=\"text-grey\">Preis berechnet danach:</span> {{printPriceCalculatedAfter()}}</p>\n            <p><span class=\"text-grey\">Aktion bevor:</span> {{printPromotionBefore()}}</p>\n            <p><span class=\"text-grey\">Aktion danach:</span> {{printPromotionAfter()}}</p>\n            <p><span class=\"text-grey\">Gel\u00F6scht:</span> {{getDeletedJaNein()}}</p>\n            <p><span class=\"text-grey\">Abgerechnet:</span> {{printFinishedJaNein()}}</p>\n        </div>\n        <div class=\"modal-body\" *ngIf=\"noRental\">\n            <p>{{noRental}}</p>\n        </div>\n        <div class=\"modal-footer\">\n            <button class=\"buttonSmall button-action\" (click)=\"delete($event)\" *ngIf=\"!getDeletedOrEmpty()\">L\u00F6schen</button>\n            <button class=\"buttonSmall button-action\" (click)=\"undoDelete($event)\" *ngIf=\"getDeletedOrEmpty()\">Wiederherstellen</button>\n            <button class=\"buttonSmall button-ok\" (click)=\"close($event)\">Schlie\u00DFen</button>\n        </div>",
                    }), 
                    __metadata('design:paramtypes', [(typeof (_a = typeof angular2_modal_1.ModalDialogInstance !== 'undefined' && angular2_modal_1.ModalDialogInstance) === 'function' && _a) || Object, (typeof (_b = typeof angular2_modal_1.ICustomModal !== 'undefined' && angular2_modal_1.ICustomModal) === 'function' && _b) || Object])
                ], ModalDelete);
                return ModalDelete;
                var _a, _b;
            })();
            exports_1("ModalDelete", ModalDelete);
        }
    }
});
