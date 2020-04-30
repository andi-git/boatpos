System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var Payment;
    return {
        setters:[],
        execute: function() {
            Payment = (function () {
                function Payment(dayNumber, value, paymentMethod, receiptType) {
                    this.dayNumber = dayNumber;
                    this.value = value;
                    this.paymentMethod = paymentMethod;
                    this.receiptType = receiptType;
                }
                Payment.prototype.toString = function () {
                    return JSON.stringify(this);
                };
                return Payment;
            }());
            exports_1("Payment", Payment);
        }
    }
});
