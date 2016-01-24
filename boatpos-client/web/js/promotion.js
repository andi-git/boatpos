System.register([], function(exports_1) {
    var PromotionBefore, PromotionAfter;
    return {
        setters:[],
        execute: function() {
            PromotionBefore = (function () {
                function PromotionBefore(id, name, timeCredit, enabled, priority, keyBinding) {
                    this.id = id;
                    this.name = name;
                    this.timeCredit = timeCredit;
                    this.enabled = enabled;
                    this.priority = priority;
                    this.keyBinding = keyBinding;
                }
                return PromotionBefore;
            })();
            exports_1("PromotionBefore", PromotionBefore);
            PromotionAfter = (function () {
                function PromotionAfter(id, name, enabled, priority, keyBinding) {
                    this.id = id;
                    this.name = name;
                    this.enabled = enabled;
                    this.priority = priority;
                    this.keyBinding = keyBinding;
                }
                return PromotionAfter;
            })();
            exports_1("PromotionAfter", PromotionAfter);
        }
    }
});
