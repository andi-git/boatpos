System.register([], function(exports_1) {
    var Commitment;
    return {
        setters:[],
        execute: function() {
            Commitment = (function () {
                function Commitment(id, name, enabled, priority, keyBinding) {
                    this.id = id;
                    this.name = name;
                    this.enabled = enabled;
                    this.priority = priority;
                    this.keyBinding = keyBinding;
                }
                return Commitment;
            })();
            exports_1("Commitment", Commitment);
        }
    }
});
