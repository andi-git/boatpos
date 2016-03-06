System.register([], function(exports_1) {
    var Payment;
    return {
        setters:[],
        execute: function() {
            Payment = (function () {
                function Payment(dayNumber, value, paymentMethod) {
                    this.dayNumber = dayNumber;
                    this.value = value;
                    this.paymentMethod = paymentMethod;
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
