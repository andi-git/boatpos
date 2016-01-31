System.register(['angular2/core', "./boat.service", "./info.service", "./commitment.service", "./promotion.service", "./departure", "./rental.service", "lib/angular2-modal", "angular2/core"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, boat_service_1, info_service_1, commitment_service_1, promotion_service_1, departure_1, rental_service_1, angular2_modal_1, core_2;
    var ActionComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (boat_service_1_1) {
                boat_service_1 = boat_service_1_1;
            },
            function (info_service_1_1) {
                info_service_1 = info_service_1_1;
            },
            function (commitment_service_1_1) {
                commitment_service_1 = commitment_service_1_1;
            },
            function (promotion_service_1_1) {
                promotion_service_1 = promotion_service_1_1;
            },
            function (departure_1_1) {
                departure_1 = departure_1_1;
            },
            function (rental_service_1_1) {
                rental_service_1 = rental_service_1_1;
            },
            function (angular2_modal_1_1) {
                angular2_modal_1 = angular2_modal_1_1;
            },
            function (core_2_1) {
                core_2 = core_2_1;
            }],
        execute: function() {
            ActionComponent = (function () {
                function ActionComponent(boatService, commitmentService, promotionService, infoService, rentalService, modal, elementRef, 
                    //private injector:Injector,
                    _renderer) {
                    var _this = this;
                    this.boatService = boatService;
                    this.commitmentService = commitmentService;
                    this.promotionService = promotionService;
                    this.infoService = infoService;
                    this.rentalService = rentalService;
                    this.modal = modal;
                    this.elementRef = elementRef;
                    this._renderer = _renderer;
                    new Mousetrap().bind(['K'], function () {
                        _this.cancel();
                    });
                    new Mousetrap().bind(['L'], function () {
                        _this.depart();
                    });
                    new Mousetrap().bind(['M'], function () {
                        _this.deleteRental();
                    });
                    new Mousetrap().bind(['N'], function () {
                        _this.info();
                    });
                    new Mousetrap().bind(['1', '2', '3', '4', '5', '6', '7', '8', '9', '0'], function (e) {
                        _this.addToNumber(String.fromCharCode(e.charCode));
                    });
                    this.modalConfig = new angular2_modal_1.ModalConfig("lg", false, 27);
                    this.modalData = new angular2_modal_1.YesNoModalContent('Simple Large modal', 'Press ESC or click OK / outside area to close.', true);
                }
                ActionComponent.prototype.cancel = function () {
                    this.rentalNumber = null;
                    this.resetUi();
                    this.infoService.event().emit("Aktion abgebrochen, Elemente wieder zurückgesetzt.");
                };
                ActionComponent.prototype.resetUi = function () {
                    this.rentalNumber = null;
                    this.boatService.resetSelected();
                    this.commitmentService.resetSelected();
                    this.promotionService.resetSelected();
                };
                ActionComponent.prototype.depart = function () {
                    var _this = this;
                    var boat = this.boatService.getSelectedBoat();
                    var commitments = this.commitmentService.getSelectedCommitmens();
                    var promotionBefore = this.promotionService.getSelectedPromotionsBefore();
                    if (boat != null) {
                        this.rentalService.departe(new departure_1.Departure(boat, commitments, promotionBefore)).subscribe(function (rental) {
                            _this.infoService.event().emit("Nr " + rental.dayId + " " + rental.boat.name + " " + _this.createStringForCommitments(rental.commitments) + _this.createStringForPromotion(rental.promotionBefore) + " wurde vermietet.");
                            _this.boatService.updateStats();
                            _this.resetUi();
                        });
                    }
                    else {
                        this.infoService.event().emit("Vermietung nicht möglich: es wurde kein Boot augewählt.");
                    }
                };
                ActionComponent.prototype.deleteRental = function () {
                    var _this = this;
                    if (this.rentalNumber != null) {
                        this.rentalService.deleteRental(this.rentalNumber).subscribe(function (rental) {
                            _this.boatService.updateStats();
                            _this.infoService.event().emit("Nummer " + _this.rentalNumber + " wurde gelöscht.");
                            _this.resetUi();
                        });
                    }
                    else {
                        this.infoService.event().emit("Löschen nicht möglich: es wurde keine Nummer eingegeben.");
                    }
                };
                ActionComponent.prototype.createStringForPromotion = function (promotion) {
                    return promotion != null ? ("(" + promotion.name + ")") : "";
                };
                ActionComponent.prototype.createStringForCommitments = function (commitments) {
                    var result = "";
                    if (commitments != null && commitments.length > 0) {
                        result += "(";
                        var first = true;
                        commitments.forEach(function (c) {
                            if (first === false) {
                                result += ", ";
                            }
                            if (first === true) {
                                first = false;
                            }
                            result += c.name;
                        });
                        result += ") ";
                    }
                    return result;
                };
                ;
                ActionComponent.prototype.addToNumber = function (s) {
                    this.rentalNumber = Number.parseInt((this.rentalNumber == null ? "" : this.rentalNumber) + s);
                    this.infoService.event().emit("Nummer geändert.");
                };
                ActionComponent.prototype.info = function () {
                    var _this = this;
                    console.log("info");
                    var dialog;
                    var component = angular2_modal_1.YesNoModal;
                    var bindings = core_1.Injector.resolve([
                        core_2.provide(angular2_modal_1.ICustomModal, { useValue: this.modalData }),
                        //provide(IterableDiffers, {useValue: this.injector.get(IterableDiffers)}),
                        //provide(KeyValueDiffers, {useValue: this.injector.get(KeyValueDiffers)}),
                        core_2.provide(core_1.Renderer, { useValue: this._renderer })
                    ]);
                    //noinspection TypeScriptUnresolvedFunction
                    dialog = this.modal.open(component, bindings, this.modalConfig);
                    dialog.then(function (resultPromise) {
                        return resultPromise.result.then(function (result) {
                            _this.lastModalResult = result;
                        }, function () {
                            _this.lastModalResult = 'Rejected!';
                            console.log(_this.lastModalResult);
                        });
                    });
                };
                ActionComponent = __decorate([
                    core_1.Component({
                        selector: 'action',
                        templateUrl: "action.component.html",
                        styleUrls: ["action.component.css"],
                    }), 
                    __metadata('design:paramtypes', [boat_service_1.BoatService, commitment_service_1.CommitmentService, promotion_service_1.PromotionService, info_service_1.InfoService, rental_service_1.RentalService, (typeof (_a = typeof angular2_modal_1.Modal !== 'undefined' && angular2_modal_1.Modal) === 'function' && _a) || Object, core_1.ElementRef, core_1.Renderer])
                ], ActionComponent);
                return ActionComponent;
                var _a;
            })();
            exports_1("ActionComponent", ActionComponent);
        }
    }
});
