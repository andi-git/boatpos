System.register([], function(exports_1) {
    var Rental;
    return {
        setters:[],
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
                return Rental;
            })();
            exports_1("Rental", Rental);
        }
    }
});
