System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var Config;
    return {
        setters:[],
        execute: function() {
            Config = (function () {
                function Config(backendUrl, username, password) {
                    this.backendUrl = backendUrl;
                    this.username = username;
                    this.password = password;
                }
                Config.prototype.toString = function () {
                    return JSON.stringify(this);
                };
                return Config;
            }());
            exports_1("Config", Config);
        }
    }
});
