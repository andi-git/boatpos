System.register(["angular2/core", "../service/rental.service"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, rental_service_1;
    var SignatureDeviceComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (rental_service_1_1) {
                rental_service_1 = rental_service_1_1;
            }],
        execute: function() {
            SignatureDeviceComponent = (function () {
                function SignatureDeviceComponent(rentalService) {
                    this.rentalService = rentalService;
                }
                SignatureDeviceComponent.prototype.textForSSE = function () {
                    return this.rentalService.getSignatureDeviceAvailableText();
                };
                SignatureDeviceComponent = __decorate([
                    core_1.Component({
                        selector: 'signatureDevice',
                        templateUrl: "html/component/signatureDevice.component.html",
                        styleUrls: ["css/component/signatureDevice.component.css"]
                    }), 
                    __metadata('design:paramtypes', [rental_service_1.RentalService])
                ], SignatureDeviceComponent);
                return SignatureDeviceComponent;
            }());
            exports_1("SignatureDeviceComponent", SignatureDeviceComponent);
        }
    }
});
