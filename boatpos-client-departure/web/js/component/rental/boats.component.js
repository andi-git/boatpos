System.register(['angular2/core', "../../service/boat.service", "../../service/commitment.service", "../../model/departure", "../../service/rental.service", "../../service/info.service", "../../printer", "../../service/config.service"], function(exports_1, context_1) {
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
    var core_1, boat_service_1, commitment_service_1, departure_1, rental_service_1, info_service_1, printer_1, config_service_1;
    var BoatsComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (boat_service_1_1) {
                boat_service_1 = boat_service_1_1;
            },
            function (commitment_service_1_1) {
                commitment_service_1 = commitment_service_1_1;
            },
            function (departure_1_1) {
                departure_1 = departure_1_1;
            },
            function (rental_service_1_1) {
                rental_service_1 = rental_service_1_1;
            },
            function (info_service_1_1) {
                info_service_1 = info_service_1_1;
            },
            function (printer_1_1) {
                printer_1 = printer_1_1;
            },
            function (config_service_1_1) {
                config_service_1 = config_service_1_1;
            }],
        execute: function() {
            BoatsComponent = (function () {
                function BoatsComponent(boatService, commitmentService, rentalService, infoService, configService, printer) {
                    this.boatService = boatService;
                    this.commitmentService = commitmentService;
                    this.rentalService = rentalService;
                    this.infoService = infoService;
                    this.configService = configService;
                    this.printer = printer;
                    this.selectedBoat = null;
                }
                BoatsComponent.prototype.getBoats = function () {
                    return this.boatService.getBoats();
                };
                BoatsComponent.prototype.click = function (boatCount) {
                    if (this.selectedBoat == boatCount.shortName) {
                        this.selectedBoat = null;
                        this.depart(this.boatService.getBoatByShortName(boatCount.shortName), [], null);
                    }
                    else {
                        this.selectedBoat = boatCount.shortName;
                    }
                };
                BoatsComponent.prototype.clickWithIdentityCard = function (boatCount) {
                    if (this.selectedBoat == (boatCount.shortName + "_id")) {
                        this.selectedBoat = null;
                        this.depart(this.boatService.getBoatByShortName(boatCount.shortName), [this.commitmentService.getCommitmentByName('Ausweis')], null);
                    }
                    else {
                        this.selectedBoat = boatCount.shortName + "_id";
                    }
                };
                BoatsComponent.prototype.depart = function (boat, commitments, promotionBefore) {
                    var _this = this;
                    this.rentalService.depart(new departure_1.Departure(boat, commitments, promotionBefore)).subscribe(function (rental) {
                        _this.printer.printDepart(rental, _this.configService.getPrinterIp());
                        _this.infoService.event().emit("Nr " + rental.dayId + " " + rental.boat.name + " " + _this.createStringForCommitments(rental.commitments) + _this.createStringForPromotion(rental.promotionBefore) + " wurde vermietet.");
                        _this.boatService.updateStats();
                    });
                };
                //noinspection JSMethodCanBeStatic
                BoatsComponent.prototype.createStringForPromotion = function (promotion) {
                    return promotion != null ? ("(" + promotion.name + ")") : "";
                };
                BoatsComponent.prototype.createStringForCommitments = function (commitments) {
                    var result = "";
                    if (commitments != null && commitments.length > 0) {
                        result += "(";
                        var first_1 = true;
                        commitments.forEach(function (c) {
                            if (first_1 === false) {
                                result += ", ";
                            }
                            if (first_1 === true) {
                                first_1 = false;
                            }
                            result += c.name;
                        });
                        result += ") ";
                    }
                    return result;
                };
                BoatsComponent.prototype.getBoatCounts = function () {
                    console.log("get boat counts");
                    return this.boatService.getBoatCounts();
                };
                BoatsComponent = __decorate([
                    core_1.Component({
                        selector: 'boats',
                        templateUrl: "html/component/rental/boats.component.html",
                        styleUrls: ["css/component/rental/boats.component.css"]
                    }), 
                    __metadata('design:paramtypes', [boat_service_1.BoatService, commitment_service_1.CommitmentService, rental_service_1.RentalService, info_service_1.InfoService, config_service_1.ConfigService, printer_1.Printer])
                ], BoatsComponent);
                return BoatsComponent;
            }());
            exports_1("BoatsComponent", BoatsComponent);
        }
    }
});
