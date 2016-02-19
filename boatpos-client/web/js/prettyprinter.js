System.register(['angular2/core', "angular2/src/facade/lang"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, lang_1, lang_2;
    var PrettyPrinter;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (lang_1_1) {
                lang_1 = lang_1_1;
                lang_2 = lang_1_1;
            }],
        execute: function() {
            PrettyPrinter = (function () {
                function PrettyPrinter() {
                }
                PrettyPrinter.prototype.pp2Pos = function (number) {
                    var result = "";
                    if (number < 10) {
                        result += "0";
                    }
                    result += number;
                    return result;
                };
                PrettyPrinter.prototype.pp3Pos = function (number) {
                    var result = "";
                    if (number < 100) {
                        result += "0";
                    }
                    result += this.pp2Pos(number);
                    return result;
                };
                PrettyPrinter.prototype.ppPrice = function (price, prefix) {
                    var result = lang_1.isPresent(prefix) ? prefix : "€ ";
                    if (lang_1.isPresent(price) && lang_2.isNumber(price) && !isNaN(price)) {
                        result += price.toFixed(2);
                    }
                    else {
                        result += "0.00";
                    }
                    return result;
                };
                PrettyPrinter.prototype.printCommitments = function (commitments) {
                    var commitmentString = "";
                    if (lang_1.isPresent(commitments)) {
                        var first = true;
                        commitments.forEach(function (commitment) {
                            if (!first) {
                                commitmentString += ", ";
                            }
                            commitmentString += commitment.name;
                            first = false;
                        });
                    }
                    return commitmentString;
                };
                PrettyPrinter.prototype.printTime = function (date) {
                    var timeString = "";
                    if (lang_1.isPresent(date) && date.getUTCFullYear() > 1970) {
                        return this.pp2Pos(date.getUTCHours()) + ":" + this.pp2Pos(date.getUTCMinutes()) + " Uhr";
                    }
                    return timeString;
                };
                PrettyPrinter.prototype.printDate = function (date) {
                    var dateString = "";
                    if (lang_1.isPresent(date) && date.getUTCFullYear() > 1970) {
                        return this.pp2Pos(date.getUTCDate()) + ". " + this.pp2Pos(date.getUTCMonth()) + ". " + date.getUTCFullYear();
                    }
                    return dateString;
                };
                PrettyPrinter = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [])
                ], PrettyPrinter);
                return PrettyPrinter;
            })();
            exports_1("PrettyPrinter", PrettyPrinter);
        }
    }
});
