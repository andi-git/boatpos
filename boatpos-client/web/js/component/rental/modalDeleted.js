System.register(['angular2/core', 'angular2/common', "lib/angular2-modal"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, common_1, angular2_modal_1;
    var ModalDeletedContext, ModalDeleted;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (common_1_1) {
                common_1 = common_1_1;
            },
            function (angular2_modal_1_1) {
                angular2_modal_1 = angular2_modal_1_1;
            }],
        execute: function() {
            ModalDeletedContext = (function () {
                function ModalDeletedContext(deletedInfo, keyBinding) {
                    this.deletedInfo = deletedInfo;
                    this.keyBinding = keyBinding;
                }
                return ModalDeletedContext;
            })();
            exports_1("ModalDeletedContext", ModalDeletedContext);
            ModalDeleted = (function () {
                function ModalDeleted(dialog, context) {
                    var _this = this;
                    this.dialog = dialog;
                    this.deletedInfo = context.deletedInfo;
                    this.keyBinding = context.keyBinding;
                    var map = {
                        'O': function () {
                            _this.cancel();
                        }
                    };
                    this.keyBinding.addBindingForDialogDeleted(map);
                    this.keyBinding.focusDialogDeleted();
                }
                ModalDeleted.prototype.close = function ($event) {
                    $event.stopPropagation();
                    //noinspection TypeScriptUnresolvedFunction
                    this.dialog.close(true);
                };
                ModalDeleted.prototype.cancel = function () {
                    //noinspection TypeScriptUnresolvedFunction
                    this.dialog.dismiss();
                };
                ModalDeleted = __decorate([
                    core_1.Component({
                        selector: 'modal-content',
                        directives: [common_1.NgIf],
                        template: "<div class=\"modal-header\">\n        <h2 class=\"header header-main\">Gel\u00F6scht!</h2>\n        </div>\n        <div class=\"modal-body\">\n            <p>{{deletedInfo}}</p>\n        <div class=\"modal-footer\">\n            <button class=\"buttonSmall button-ok\" (click)=\"close($event)\">Schlie\u00DFen</button>\n        </div>",
                    }), 
                    __metadata('design:paramtypes', [(typeof (_a = typeof angular2_modal_1.ModalDialogInstance !== 'undefined' && angular2_modal_1.ModalDialogInstance) === 'function' && _a) || Object, (typeof (_b = typeof angular2_modal_1.ICustomModal !== 'undefined' && angular2_modal_1.ICustomModal) === 'function' && _b) || Object])
                ], ModalDeleted);
                return ModalDeleted;
                var _a, _b;
            })();
            exports_1("ModalDeleted", ModalDeleted);
        }
    }
});
