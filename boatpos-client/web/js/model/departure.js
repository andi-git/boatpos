System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var Departure;
    return {
        setters:[],
        execute: function() {
            Departure = (function () {
                function Departure(boat, commitments, promotion) {
                    var _this = this;
                    this.commitmentIds = [];
                    if (boat != null) {
                        this.boatId = boat.id;
                    }
                    if (commitments != null) {
                        commitments.forEach(function (commitment) { return _this.commitmentIds.push(commitment.id); });
                    }
                    if (promotion != null) {
                        this.promotionId = promotion.id;
                    }
                }
                Departure.prototype.toString = function () {
                    return JSON.stringify(this);
                };
                return Departure;
            }());
            exports_1("Departure", Departure);
        }
    }
});
