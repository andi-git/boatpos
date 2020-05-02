System.register(['angular2/core', "angular2/src/facade/lang"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
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
    var PrettyPrinter, Align;
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
                //noinspection JSMethodCanBeStatic
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
                //noinspection JSMethodCanBeStatic
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
                        var first_1 = true;
                        commitments.forEach(function (commitment) {
                            if (!first_1) {
                                commitmentString += ", ";
                            }
                            commitmentString += commitment.name;
                            first_1 = false;
                        });
                    }
                    return commitmentString;
                };
                //noinspection JSMethodCanBeStatic
                PrettyPrinter.prototype.printPromotions = function (promotionBefore, promotionAfter) {
                    var promotionsString = "";
                    if (lang_1.isPresent(promotionBefore)) {
                        promotionsString += promotionBefore.name;
                    }
                    if (lang_1.isPresent(promotionBefore) && lang_1.isPresent(promotionAfter)) {
                        promotionsString += ", ";
                    }
                    if (lang_1.isPresent(promotionAfter)) {
                        promotionsString += promotionAfter.name;
                    }
                    return promotionsString;
                };
                PrettyPrinter.prototype.printTime = function (date) {
                    var timeString = "";
                    if (lang_1.isPresent(date) && date.getFullYear() > 1970) {
                        return this.pp2Pos(date.getHours()) + ":" + this.pp2Pos(date.getMinutes()) + ":" + this.pp2Pos(date.getSeconds());
                    }
                    return timeString;
                };
                PrettyPrinter.prototype.printDate = function (date) {
                    var dateString = "";
                    if (lang_1.isPresent(date) && date.getFullYear() > 1970) {
                        return this.pp2Pos(date.getDate()) + "." + this.pp2Pos(date.getMonth() + 1) + "." + date.getFullYear();
                    }
                    return dateString;
                };
                PrettyPrinter.prototype.printDateAndTime = function (date) {
                    return this.printDate(date) + " " + this.printTime(date);
                };
                //noinspection JSMethodCanBeStatic
                PrettyPrinter.prototype.ppFixLength = function (text, length, align) {
                    var result = text;
                    if (text.length > length) {
                        result = text.substr(0, length);
                    }
                    else {
                        if (align === Align.LEFT || align === Align.CENTER) {
                            for (var i = text.length; i < length; i++) {
                                result += " ";
                            }
                        }
                        else {
                            for (var i = text.length; i < length; i++) {
                                result = " " + result;
                            }
                        }
                    }
                    return result;
                };
                PrettyPrinter = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [])
                ], PrettyPrinter);
                return PrettyPrinter;
            }());
            exports_1("PrettyPrinter", PrettyPrinter);
            (function (Align) {
                Align[Align["LEFT"] = 0] = "LEFT";
                Align[Align["CENTER"] = 1] = "CENTER";
                Align[Align["RIGHT"] = 2] = "RIGHT";
            })(Align || (Align = {}));
            exports_1("Align", Align);
        }
    }
});
