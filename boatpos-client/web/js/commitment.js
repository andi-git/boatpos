System.register([], function(exports_1) {
    var Commitment;
    return {
        setters:[],
        execute: function() {
            Commitment = (function () {
                function Commitment(id, name, enabled, priority) {
                    this.id = id;
                    this.name = name;
                    this.enabled = enabled;
                    this.priority = priority;
                }
                return Commitment;
            })();
            exports_1("Commitment", Commitment);
        }
    }
});
