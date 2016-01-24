System.register([], function(exports_1) {
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
                return Departure;
            })();
            exports_1("Departure", Departure);
        }
    }
});
