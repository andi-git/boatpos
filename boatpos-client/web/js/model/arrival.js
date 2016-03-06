System.register([], function(exports_1) {
    var Arrival;
    return {
        setters:[],
        execute: function() {
            Arrival = (function () {
                function Arrival(dayNumber) {
                    this.dayNumber = dayNumber;
                }
                Arrival.prototype.toString = function () {
                    return JSON.stringify(this);
                };
                return Arrival;
            })();
            exports_1("Arrival", Arrival);
        }
    }
});
