System.register(['angular2/core', "./boat.service", "./info.service", "./commitment.service", "./promotion.service", "./departe"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, boat_service_1, info_service_1, commitment_service_1, promotion_service_1, departe_1;
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
            function (departe_1_1) {
                departe_1 = departe_1_1;
            }],
        execute: function() {
            ActionComponent = (function () {
                function ActionComponent(boatService, commitmentService, promotionService, infoService) {
                    var _this = this;
                    this.boatService = boatService;
                    this.commitmentService = commitmentService;
                    this.promotionService = promotionService;
                    this.infoService = infoService;
                    new Mousetrap().bind(['K'], function () {
                        _this.cancel();
                    });
                    new Mousetrap().bind(['L'], function () {
                        _this.departe();
                    });
                    new Mousetrap().bind(['1', '2', '3', '4', '5', '6', '7', '8', '9', '0'], function (e) {
                        _this.addToNumber(String.fromCharCode(e.charCode));
                    });
                }
                ActionComponent.prototype.cancel = function () {
                    this.rentalNumber = null;
                    this.resetUi();
                    this.infoService.event().emit("Aktion abgebrochen, Elemente wieder zurückgesetzt.");
                };
                ActionComponent.prototype.resetUi = function () {
                    this.boatService.resetSelected();
                    this.commitmentService.resetSelected();
                    this.promotionService.resetSelected();
                };
                ActionComponent.prototype.departe = function () {
                    var boat = this.boatService.getSelectedBoat();
                    var commitments = this.commitmentService.getSelectedCommitmens();
                    var promotionBefore = this.promotionService.getSelectedPromotionsBefore();
                    if (boat != null) {
                        var departe = new departe_1.Departe(boat, commitments, promotionBefore);
                        this.resetUi();
                        this.infoService.event().emit("'" + boat.name + "' wurde vermietet.");
                    }
                    else {
                        this.infoService.event().emit("Vermietung nicht möglich: es wurde kein Boot augewählt");
                    }
                };
                ActionComponent.prototype.addToNumber = function (s) {
                    this.rentalNumber = (this.rentalNumber == null ? "" : this.rentalNumber) + s;
                    this.infoService.event().emit("Nummer geändert.");
                };
                ActionComponent = __decorate([
                    core_1.Component({
                        selector: 'action',
                        templateUrl: "action.component.html",
                        styleUrls: ["action.component.css"],
                    }), 
                    __metadata('design:paramtypes', [boat_service_1.BoatService, commitment_service_1.CommitmentService, promotion_service_1.PromotionService, info_service_1.InfoService])
                ], ActionComponent);
                return ActionComponent;
            })();
            exports_1("ActionComponent", ActionComponent);
        }
    }
});
