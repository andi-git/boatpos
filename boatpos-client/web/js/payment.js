System.register([], function(exports_1) {
    var Payment;
    return {
        setters:[],
        execute: function() {
            Payment = (function () {
                function Payment(dayNumber, value) {
                    this.dayNumber = dayNumber;
                    this.value = value;
                }
                Payment.prototype.toString = function () {
                    return JSON.stringify(this);
                };
                return Payment;
            })();
            exports_1("Payment", Payment);
        }
    }
});
