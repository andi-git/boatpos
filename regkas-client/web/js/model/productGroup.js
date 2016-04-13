System.register(["angular2/src/facade/lang"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var lang_1;
    var ProductGroup;
    return {
        setters:[
            function (lang_1_1) {
                lang_1 = lang_1_1;
            }],
        execute: function() {
            ProductGroup = (function () {
                function ProductGroup(id, name, taxPercent, priority, pictureUrl, pictureUrlThumb, keyBinding, products) {
                    var _this = this;
                    this.products = [];
                    this.id = id;
                    this.name = name;
                    this.taxPercent = taxPercent;
                    this.priority = priority;
                    this.pictureUrl = pictureUrl;
                    this.pictureUrlThumb = pictureUrlThumb;
                    this.keyBinding = keyBinding;
                    if (lang_1.isPresent(products)) {
                        // this.products.push(products);
                        products.forEach(function (p) { return _this.products.push(p); });
                    }
                }
                ProductGroup.prototype.toString = function () {
                    return JSON.stringify(this);
                };
                return ProductGroup;
            }());
            exports_1("ProductGroup", ProductGroup);
        }
    }
});
