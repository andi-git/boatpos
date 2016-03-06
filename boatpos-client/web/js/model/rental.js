System.register(["../prettyprinter", "angular2/src/facade/lang"], function(exports_1) {
    var prettyprinter_1, lang_1;
    var Rental;
    return {
        setters:[
            function (prettyprinter_1_1) {
                prettyprinter_1 = prettyprinter_1_1;
            },
            function (lang_1_1) {
                lang_1 = lang_1_1;
            }],
        execute: function() {
            Rental = (function () {
                function Rental(dayId, day, boat, departure, arrival, pricePaidAfter, pricePaidBefore, priceCalculatedAfter, priceCalculatedBefore, finished, deleted, coupon, promotionBefore, promotionAfter, commitments, timeOfTravel, timeOfTravelCalculated) {
                    var _this = this;
                    this.commitments = [];
                    this.dayId = dayId;
                    this.day = day;
                    this.boat = boat;
                    this.departure = departure;
                    this.arrival = arrival;
                    this.pricePaidAfter = pricePaidAfter;
                    this.pricePaidBefore = pricePaidBefore;
                    this.priceCalculatedAfter = priceCalculatedAfter;
                    this.priceCalculatedBefore = priceCalculatedBefore;
                    this.finished = finished;
                    this.deleted = deleted;
                    this.coupon = coupon;
                    this.promotionBefore = promotionBefore;
                    this.promotionAfter = promotionAfter;
                    if (commitments != null) {
                        commitments.forEach(function (commitment) { return _this.commitments.push(commitment); });
                    }
                    this.timeOfTravel = timeOfTravel;
                    this.timeOfTravelCalculated = timeOfTravelCalculated;
                }
                Rental.fromDepart = function (dayId, day, boat, departure, commitments, promotionBefore, coupon, priceCalculatedBefore) {
                    return new Rental(dayId, day, boat, departure, null, null, null, null, priceCalculatedBefore, null, null, coupon, promotionBefore, null, commitments, null, null);
                };
                Rental.prototype.toString = function () {
                    return JSON.stringify(this);
                };
                Rental.prototype.style = function () {
                    if (this.deleted === true) {
                        return "rental-deleted";
                    }
                    else if (this.finished === true) {
                        return "rental-finished";
                    }
                    else {
                        return "rental-default";
                    }
                };
                Rental.prototype.ppDayId = function () {
                    return new prettyprinter_1.PrettyPrinter().pp3Pos(this.dayId);
                };
                Rental.prototype.ppCommitments = function () {
                    return new prettyprinter_1.PrettyPrinter().printCommitments(this.commitments);
                };
                Rental.prototype.ppDeparture = function () {
                    return new prettyprinter_1.PrettyPrinter().printTime(this.departure);
                };
                Rental.prototype.ppArrival = function () {
                    if (this.finished === true) {
                        return new prettyprinter_1.PrettyPrinter().printTime(this.arrival);
                    }
                    else {
                        return "";
                    }
                };
                Rental.prototype.ppPricePaidComplete = function () {
                    return new prettyprinter_1.PrettyPrinter().ppPrice(this.pricePaidBefore + this.pricePaidAfter, null);
                };
                Rental.prototype.ppPromotionComplete = function () {
                    return new prettyprinter_1.PrettyPrinter().printPromotions(this.promotionBefore, this.promotionAfter);
                };
                Rental.prototype.ppTimeOfTravel = function () {
                    console.log("tot: " + this.timeOfTravel);
                    var result = "";
                    if (lang_1.isPresent(this.timeOfTravel) || this.timeOfTravel > 0) {
                        if (this.timeOfTravel > 60) {
                            result += Math.floor(this.timeOfTravel / 60);
                            result += " Std ";
                        }
                        result += this.timeOfTravel % 60;
                        result += " Min";
                    }
                    return result;
                };
                return Rental;
            })();
            exports_1("Rental", Rental);
        }
    }
});
