System.register([], function(exports_1) {
    var Bill;
    return {
        setters:[],
        execute: function() {
            Bill = (function () {
                function Bill() {
                }
                Bill.prototype.toString = function () {
                    return JSON.stringify(this);
                };
                return Bill;
            })();
            exports_1("Bill", Bill);
        }
    }
});
