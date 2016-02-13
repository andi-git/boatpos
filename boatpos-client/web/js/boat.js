System.register([], function(exports_1) {
    var Boat, BoatCount;
    return {
        setters:[],
        execute: function() {
            Boat = (function () {
                function Boat(id, name, enabled, priority, shortName, pictureUrlSmall, pictureUrlMedium, keyBinding) {
                    this.id = id;
                    this.name = name;
                    this.shortName = shortName;
                    this.enabled = enabled;
                    this.priority = priority;
                    this.pictureUrlSmall = pictureUrlSmall;
                    this.pictureUrlMedium = pictureUrlMedium;
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
            })();
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
                return BoatCount;
            })();
            exports_1("BoatCount", BoatCount);
        }
    }
});
