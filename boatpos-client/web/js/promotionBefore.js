System.register([], function(exports_1) {
    var PromotionBefore;
    return {
        setters:[],
        execute: function() {
            PromotionBefore = (function () {
                function PromotionBefore(id, name, timeCredit, enabled, priority) {
                    this.id = id;
                    this.name = name;
                    this.timeCredit = timeCredit;
                    this.enabled = enabled;
                    this.priority = priority;
                }
                return PromotionBefore;
            })();
            exports_1("PromotionBefore", PromotionBefore);
        }
    }
});
