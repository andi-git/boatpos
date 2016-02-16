System.register([], function(exports_1) {
    var PromotionBefore, PromotionAfter;
    return {
        setters:[],
        execute: function() {
            PromotionBefore = (function () {
                function PromotionBefore(id, name, timeCredit, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb) {
                    this.style = "enabled";
                    this.id = id;
                    this.name = name;
                    this.timeCredit = timeCredit;
                    this.enabled = enabled;
                    this.priority = priority;
                    this.keyBinding = keyBinding;
                    this.pictureUrl = pictureUrl;
                    this.pictureUrlThumb = pictureUrlThumb;
                    if (this.enabled === false) {
                        this.style = "disabled";
                    }
                }
                PromotionBefore.prototype.toString = function () {
                    return JSON.stringify(this);
                };
                return PromotionBefore;
            })();
            exports_1("PromotionBefore", PromotionBefore);
            PromotionAfter = (function () {
                function PromotionAfter(id, name, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb) {
                    this.id = id;
                    this.name = name;
                    this.enabled = enabled;
                    this.priority = priority;
                    this.keyBinding = keyBinding;
                    this.pictureUrl = pictureUrl;
                    this.pictureUrlThumb = pictureUrlThumb;
                }
                PromotionAfter.prototype.toString = function () {
                    return JSON.stringify(this);
                };
                return PromotionAfter;
            })();
            exports_1("PromotionAfter", PromotionAfter);
        }
    }
});
