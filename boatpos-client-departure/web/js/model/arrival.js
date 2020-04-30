System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
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
            }());
            exports_1("Arrival", Arrival);
        }
    }
});
