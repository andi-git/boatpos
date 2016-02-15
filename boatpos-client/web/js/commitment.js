System.register([], function(exports_1) {
    var Commitment;
    return {
        setters:[],
        execute: function() {
            Commitment = (function () {
                function Commitment(id, name, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb) {
                    this.id = id;
                    this.name = name;
                    this.enabled = enabled;
                    this.priority = priority;
                    this.keyBinding = keyBinding;
                    this.pictureUrl = pictureUrl;
                    this.pictureUrlThumb = pictureUrlThumb;
                }
                return Commitment;
            })();
            exports_1("Commitment", Commitment);
        }
    }
});
