System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var Boat, BoatCount;
    return {
        setters:[],
        execute: function() {
            Boat = (function () {
                function Boat(id, name, enabled, priority, shortName, pictureUrl, pictureUrlThumb, keyBinding) {
                    this.id = id;
                    this.name = name;
                    this.shortName = shortName;
                    this.enabled = enabled;
                    this.priority = priority;
                    this.pictureUrl = pictureUrl;
                    this.pictureUrlThumb = pictureUrlThumb;
                    this.keyBinding = keyBinding;
                }
                Boat.prototype.isSelected = function () {
                    return this.selected;
                };
                Boat.prototype.setSelected = function (selected) {
                    this.selected = selected;
                };
                Boat.prototype.toString = function () {
                    return JSON.stringify(this);
                };
                return Boat;
            }());
            exports_1("Boat", Boat);
            BoatCount = (function () {
                function BoatCount(id, name, shortName, count, max) {
                    this.id = id;
                    this.name = name;
                    this.shortName = shortName;
                    this.count = count;
                    this.max = max;
                    if (count >= max) {
                        this.style = "max";
                    }
                }
                BoatCount.prototype.toString = function () {
                    return JSON.stringify(this);
                };
                BoatCount.prototype.mediumImage = function () {
                    return "https://www.eppel-boote.at/boatpos/images/boat/medium/" + this.shortName + ".jpg";
                };
                BoatCount.prototype.mediumImageWithIdentityCard = function () {
                    return "https://www.eppel-boote.at/boatpos/images/boat/medium/" + this.shortName + "_id.jpg";
                };
                BoatCount.prototype.click = function () {
                    return "click('" + this.shortName + "')";
                };
                BoatCount.prototype.clickWithIdentityCard = function () {
                    return "clickWithIdentityCard('" + this.shortName + "')";
                };
                return BoatCount;
            }());
            exports_1("BoatCount", BoatCount);
        }
    }
});
