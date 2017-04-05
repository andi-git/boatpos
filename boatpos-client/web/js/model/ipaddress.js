System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var IpAddress;
    return {
        setters:[],
        execute: function() {
            IpAddress = (function () {
                function IpAddress(ipAddress) {
                    this.ipAddress = ipAddress;
                }
                IpAddress.prototype.toString = function () {
                    return JSON.stringify(this);
                };
                return IpAddress;
            }());
            exports_1("IpAddress", IpAddress);
        }
    }
});
