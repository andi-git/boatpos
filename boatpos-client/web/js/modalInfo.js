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
    var ModalInfoContent, ModalDelete;
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
            ModalInfoContent = (function () {
                function ModalInfoContent(rentalNumber, rentalService) {
                    this.rentalNumber = rentalNumber;
                    this.rentalService = rentalService;
                }
                return ModalInfoContent;
            })();
            exports_1("ModalInfoContent", ModalInfoContent);
            ModalDelete = (function () {
                function ModalDelete(dialog, modelContentData) {
                    var _this = this;
                    this.dialog = dialog;
                    this.content = modelContentData;
                    this.content.rentalService.getRental(this.content.rentalNumber).subscribe(function (rental) {
                        _this.boatName = rental.boat.name;
                        _this.departure = rental.departure;
                        _this.arrival = rental.arrival;
                        _this.pricePaidBefore = rental.pricePaidBefore;
                        _this.pricePaidBefore = rental.pricePaidAfter;
                        if (lang_1.isPresent(rental.promotionBefore)) {
                            _this.promotionBefore = rental.promotionBefore.name;
                        }
                        if (lang_1.isPresent(rental.promotionAfter)) {
                            _this.promotionAfter = rental.promotionAfter.name;
                        }
                        _this.commitments = "";
                        rental.commitments.forEach(function (commitment) {
                            _this.commitments += commitment.name;
                            _this.commitments += ", ";
                        });
                    }, function () {
                        _this.noRental = "Keine Vermietung mit Nummer " + _this.content.rentalNumber + " gefunden!";
                    });
                }
                ModalDelete.prototype.close = function ($event) {
                    $event.stopPropagation();
                    //noinspection TypeScriptUnresolvedFunction
                    this.dialog.close(true);
                };
                ModalDelete.prototype.cancel = function () {
                    //noinspection TypeScriptUnresolvedFunction
                    this.dialog.dismiss();
                };
                ModalDelete = __decorate([
                    core_1.Component({
                        selector: 'modal-content',
                        directives: [common_1.NgIf],
                        template: "<div class=\"modal-header\">\n        <h2 class=\"modal-title\">Information \u00FCber Nummer {{content.rentalNumber}}</h2>\n        </div>\n        <div class=\"modal-body\" *ngIf=\"!noRental\">\n            <p>Boot: {{boatName}}</p>\n            <p>Abfahrt: {{departure}}</p>\n            <p>Ankunft: {{arrival}}</p>\n            <p>Preis bevor: {{pricePaidBefore}}</p>\n            <p>Preis danach: {{pricePaidAfter}}</p>\n            <p>Aktion bevor: {{promotionBefore}}</p>\n            <p>Aktion danach: {{promotionAfter}}</p>\n            <p>Einsatz: {{commitments}}</p>\n        </div>\n        <div class=\"modal-body\" *ngIf=\"noRental\">{{noRental}}</div>\n        <div class=\"modal-footer\">\n            <button class=\"btn btn-primary\" (click)=\"delete($event)\">L\u00F6schen</button>\n            <button class=\"btn btn-primary\" (click)=\"close($event)\">Schlie\u00DFen</button>\n        </div>",
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
