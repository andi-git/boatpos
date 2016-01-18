System.register([], function(exports_1) {
    var Boat;
    return {
        setters:[],
        execute: function() {
            Boat = (function () {
                function Boat(id, name, enabled, priority, shortName, pictureUrlSmall, pictureUrlMedium) {
                    this.id = id;
                    this.name = name;
                    this.shortName = shortName;
                    this.enabled = enabled;
                    this.priority = priority;
                    this.pictureUrlSmall = pictureUrlSmall;
                    this.pictureUrlMedium = pictureUrlMedium;
                }
                return Boat;
            })();
            exports_1("Boat", Boat);
        }
    }
});
