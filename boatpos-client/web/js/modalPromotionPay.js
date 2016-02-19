System.register(['angular2/core', 'angular2/common', "lib/angular2-modal", "./payment"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, common_1, angular2_modal_1, payment_1;
    var ModalPromotionPayContext, ModalPromotionPay;
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
            function (payment_1_1) {
                payment_1 = payment_1_1;
            }],
        execute: function() {
            ModalPromotionPayContext = (function () {
                function ModalPromotionPayContext(rental, rentalService, pp, keyBinding) {
                    this.rental = rental;
                    this.rentalService = rentalService;
                    this.pp = pp;
                    this.keyBinding = keyBinding;
                }
                return ModalPromotionPayContext;
            })();
            exports_1("ModalPromotionPayContext", ModalPromotionPayContext);
            ModalPromotionPay = (function () {
                function ModalPromotionPay(dialog, context) {
                    var _this = this;
                    this.error = null;
                    this.dialog = dialog;
                    this.rental = context.rental;
                    this.rentalService = context.rentalService;
                    this.pp = context.pp;
                    this.keyBinding = context.keyBinding;
                    var map = {
                        'O': function () {
                            _this.paid();
                        },
                        'K': function () {
                            _this.deleteRental();
                        }
                    };
                    this.keyBinding.addBindingForDialogPromotionPay(map);
                    this.keyBinding.focusDialogPromotionPay();
                }
                ModalPromotionPay.prototype.deleteRental = function () {
                    var _this = this;
                    this.rentalService.deleteRental(this.rental.dayId).subscribe(function (rental) {
                        _this.rental = rental;
                        _this.closeOk();
                    }, function () {
                        _this.error = "Fehler beim Löschen von Vermietung mit Nummer " + _this.rental.dayId + "!";
                        _this.cancel();
                    });
                };
                ModalPromotionPay.prototype.paid = function () {
                    var _this = this;
                    var payment = new payment_1.Payment(this.rental.dayId, this.rental.priceCalculatedBefore);
                    this.rentalService.payBefore(payment).subscribe(function (rental) {
                        _this.rental = rental;
                        _this.closeOk(rental);
                    }, function () {
                        _this.error = "Fehler beim Zahlen der Aktion von Vermietung mit Nummer " + _this.rental.dayId + "!";
                        _this.cancel();
                    });
                };
                ModalPromotionPay.prototype.getPriceCalculatedBefore = function () {
                    return this.pp.ppPrice(this.rental.priceCalculatedBefore, "€ ");
                };
                ModalPromotionPay.prototype.getPromotionBefore = function () {
                    return this.rental.promotionBefore.name;
                };
                ModalPromotionPay.prototype.close = function ($event) {
                    $event.stopPropagation();
                    //noinspection TypeScriptUnresolvedFunction
                    this.dialog.close(true);
                };
                ModalPromotionPay.prototype.closeOk = function (result) {
                    //noinspection TypeScriptUnresolvedFunction
                    this.dialog.close(result);
                };
                ModalPromotionPay.prototype.cancel = function () {
                    //noinspection TypeScriptUnresolvedFunction
                    this.dialog.dismiss();
                };
                ModalPromotionPay = __decorate([
                    core_1.Component({
                        selector: 'modal-content',
                        directives: [common_1.NgIf],
                        template: "<div class=\"modal-header\">\n        <h2 class=\"header header-main\">Aktion '{{getPromotionBefore()}}' bezahlen!</h2>\n        </div>\n        <div class=\"modal-body\">\n            <p><span class=\"text-grey\">Preis f\u00FCr</span> {{getPromotionBefore()}} <span class=\"text-grey\">von</span> {{getPriceCalculatedBefore()}} <span class=\"text-grey\">erhalten?</span></p>\n        <div class=\"modal-footer\">\n            <button class=\"buttonSmall button-cancel\" (click)=\"deleteRental()\">L\u00F6schen</button>\n            <button class=\"buttonSmall button-ok\" (click)=\"paid()\">Bezahlt</button>\n        </div>",
                    }), 
                    __metadata('design:paramtypes', [(typeof (_a = typeof angular2_modal_1.ModalDialogInstance !== 'undefined' && angular2_modal_1.ModalDialogInstance) === 'function' && _a) || Object, (typeof (_b = typeof angular2_modal_1.ICustomModal !== 'undefined' && angular2_modal_1.ICustomModal) === 'function' && _b) || Object])
                ], ModalPromotionPay);
                return ModalPromotionPay;
                var _a, _b;
            })();
            exports_1("ModalPromotionPay", ModalPromotionPay);
        }
    }
});
