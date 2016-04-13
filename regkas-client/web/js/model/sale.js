System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var Sale;
    return {
        setters:[],
        execute: function() {
            Sale = (function () {
                function Sale(paymentMethod, receiptType, saleElements) {
                    this.paymentMethod = paymentMethod;
                    this.receiptType = receiptType;
                    this.saleElements = saleElements;
                }
                Sale.prototype.toString = function () {
                    return JSON.stringify(this);
                };
                return Sale;
            }());
            exports_1("Sale", Sale);
        }
    }
});
