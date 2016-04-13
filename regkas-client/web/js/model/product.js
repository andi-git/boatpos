System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var Product;
    return {
        setters:[],
        execute: function() {
            Product = (function () {
                function Product(id, name, price, priority, pictureUrl, pictureUrlThumb, keyBinding, generic) {
                    this.id = id;
                    this.name = name;
                    this.price = price;
                    this.priority = priority;
                    this.pictureUrl = pictureUrl;
                    this.pictureUrlThumb = pictureUrlThumb;
                    this.keyBinding = keyBinding;
                    this.generic = generic;
                }
                Product.prototype.toString = function () {
                    return JSON.stringify(this);
                };
                return Product;
            }());
            exports_1("Product", Product);
        }
    }
});
