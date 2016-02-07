System.register(['angular2/core', "./boat.service", "./info.service", "./commitment.service", "./promotion.service", "./departure", "./rental.service", "./modalInfo", "angular2/src/facade/lang", "./keybinding.service", "./modalHandler", "./modalDeleted", "./prettyprinter"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, boat_service_1, info_service_1, commitment_service_1, promotion_service_1, departure_1, rental_service_1, modalInfo_1, lang_1, keybinding_service_1, modalHandler_1, modalDeleted_1, modalDeleted_2, prettyprinter_1;
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
            function (modalInfo_1_1) {
                modalInfo_1 = modalInfo_1_1;
            },
            function (lang_1_1) {
                lang_1 = lang_1_1;
            },
            function (keybinding_service_1_1) {
                keybinding_service_1 = keybinding_service_1_1;
            },
            function (modalHandler_1_1) {
                modalHandler_1 = modalHandler_1_1;
            },
            function (modalDeleted_1_1) {
                modalDeleted_1 = modalDeleted_1_1;
                modalDeleted_2 = modalDeleted_1_1;
            },
            function (prettyprinter_1_1) {
                prettyprinter_1 = prettyprinter_1_1;
            }],
        execute: function() {
            ActionComponent = (function () {
                function ActionComponent(boatService, commitmentService, promotionService, infoService, rentalService, keyBinding, modalHandler, pp) {
                    var _this = this;
                    this.boatService = boatService;
                    this.commitmentService = commitmentService;
                    this.promotionService = promotionService;
                    this.infoService = infoService;
                    this.rentalService = rentalService;
                    this.keyBinding = keyBinding;
                    this.modalHandler = modalHandler;
                    this.pp = pp;
                    var map = {
                        'K': function () {
                            _this.cancel();
                        },
                        'L': function () {
                            _this.depart();
                        },
                        'M': function () {
                            _this.deleteRental();
                        },
                        'N': function () {
                            _this.info();
                        }
                    };
                    for (var i = 0; i <= 9; i++) {
                        map[i] = function (e) {
                            _this.addToNumber(String.fromCharCode(e.charCode));
                        };
                    }
                    this.keyBinding.addBindingForMain(map);
                }
                ActionComponent.prototype.cancel = function () {
                    this.rentalNumber = null;
                    this.resetUi();
                    this.infoService.event().emit("Aktion abgebrochen, Elemente wieder zurückgesetzt.");
                };
                ActionComponent.prototype.resetUi = function () {
                    this.rentalNumber = null;
                    this.boatService.reset();
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
                            var deletedInfo = "Vermietung mit Nummer " + _this.rentalNumber + " wurde gelöscht.";
                            _this.showDialogDeleted(deletedInfo);
                            _this.boatService.updateStats();
                            _this.resetUi();
                            _this.infoService.event().emit(deletedInfo);
                        }, function () {
                            var deletedInfo = "Keine Vermietung mit Nummer  " + _this.rentalNumber + " gefunden.";
                            _this.showDialogDeleted(deletedInfo);
                            _this.boatService.updateStats();
                            _this.resetUi();
                            _this.infoService.event().emit(deletedInfo);
                        });
                    }
                    else {
                        this.infoService.event().emit("Löschen nicht möglich: keine Nummer eingegeben.");
                    }
                };
                ActionComponent.prototype.showDialogDeleted = function (deletedInfo) {
                    var _this = this;
                    this.modalHandler.open(modalDeleted_2.ModalDeleted, new modalDeleted_1.ModalDeletedContext(deletedInfo, this.keyBinding)).then(function (resultPromise) {
                        //noinspection TypeScriptUnresolvedVariable
                        return resultPromise.result.then(function (result) {
                            _this.lastModalResult = result;
                            _this.resetUi();
                            _this.keyBinding.focusMain();
                        }, function () {
                            _this.lastModalResult = 'Rejected!';
                            _this.resetUi();
                            _this.keyBinding.focusMain();
                        });
                    });
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
                    this.infoService.event().emit("Nummer eingegeben.");
                };
                ActionComponent.prototype.info = function () {
                    var _this = this;
                    if (!lang_1.isPresent(this.rentalNumber)) {
                        this.infoService.event().emit("Information anzeigen nicht möglich: keine Nummer eingegeben.");
                    }
                    else {
                        this.infoService.event().emit("Information über Nummer " + this.rentalNumber + " wird angezeigt.");
                        this.modalHandler.open(modalInfo_1.ModalDelete, new modalInfo_1.ModalInfoContext(this.rentalNumber, this.rentalService, this.keyBinding, this.pp)).then(function (resultPromise) {
                            //noinspection TypeScriptUnresolvedVariable
                            return resultPromise.result.then(function (result) {
                                _this.lastModalResult = result;
                                _this.resetUi();
                                _this.keyBinding.focusMain();
                            }, function () {
                                _this.lastModalResult = 'Rejected!';
                                _this.resetUi();
                                _this.keyBinding.focusMain();
                            });
                        });
                    }
                };
                ActionComponent = __decorate([
                    core_1.Component({
                        selector: 'action',
                        templateUrl: "action.component.html",
                        styleUrls: ["action.component.css"],
                    }), 
                    __metadata('design:paramtypes', [boat_service_1.BoatService, commitment_service_1.CommitmentService, promotion_service_1.PromotionService, info_service_1.InfoService, rental_service_1.RentalService, keybinding_service_1.KeyBindingService, modalHandler_1.ModalHandler, prettyprinter_1.PrettyPrinter])
                ], ActionComponent);
                return ActionComponent;
            })();
            exports_1("ActionComponent", ActionComponent);
        }
    }
});
