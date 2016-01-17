System.register([], function(exports_1) {
    var BoatCompact;
    return {
        setters:[],
        execute: function() {
            BoatCompact = (function () {
                function BoatCompact(id, name, shortName, pictureUrlMedium) {
                    this.id = id;
                    this.name = name;
                    this.shortName = shortName;
                    this.pictureUrlMedium = pictureUrlMedium;
                }
                return BoatCompact;
            })();
            exports_1("BoatCompact", BoatCompact);
        }
    }
});
