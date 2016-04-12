System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var ReceiptElement;
    return {
        setters:[],
        execute: function() {
            ReceiptElement = (function () {
                function ReceiptElement(price, amount, product) {
                    this.price = price;
                    this.amount = amount;
                    this.product = product;
                }
                ReceiptElement.prototype.toString = function () {
                    return JSON.stringify(this);
                };
                return ReceiptElement;
            }());
            exports_1("ReceiptElement", ReceiptElement);
        }
    }
});
