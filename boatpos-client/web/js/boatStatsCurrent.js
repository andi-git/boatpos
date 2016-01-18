System.register([], function(exports_1) {
    var BoatStatsCurrent;
    return {
        setters:[],
        execute: function() {
            BoatStatsCurrent = (function () {
                function BoatStatsCurrent(id, name, enabled, priority, shortName, pictureUrlSmall, pictureUrlMedium) {
                    this.id = id;
                    this.name = name;
                    this.shortName = shortName;
                    this.enabled = enabled;
                    this.priority = priority;
                    this.pictureUrlSmall = pictureUrlSmall;
                    this.pictureUrlMedium = pictureUrlMedium;
                }
                return BoatStatsCurrent;
            })();
            exports_1("BoatStatsCurrent", BoatStatsCurrent);
        }
    }
});
