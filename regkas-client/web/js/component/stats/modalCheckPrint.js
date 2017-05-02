System.register(["angular2/core", 'lib/angular2-modal'], function(exports_1, context_1) {
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
    var core_1, angular2_modal_1;
    var ModalCheckPrintContext, ModalCheckPrint;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (angular2_modal_1_1) {
                angular2_modal_1 = angular2_modal_1_1;
            }],
        execute: function() {
            ModalCheckPrintContext = (function () {
                function ModalCheckPrintContext(type) {
                    this.type = type;
                }
                return ModalCheckPrintContext;
            }());
            exports_1("ModalCheckPrintContext", ModalCheckPrintContext);
            // this class is ugly because of a bug in angluar2-beta-0: https://github.com/angular/angular/issues/4330
            ModalCheckPrint = (function () {
                function ModalCheckPrint(dialog, modelContentData) {
                    this.dialog = dialog;
                    this.type = modelContentData.type;
                }
                ModalCheckPrint.prototype.ok = function ($event) {
                    console.log('ok');
                    $event.stopPropagation();
                    //noinspection TypeScriptUnresolvedFunction
                    this.dialog.close(true);
                };
                ModalCheckPrint.prototype.cancel = function ($event) {
                    console.log('cancel');
                    $event.stopPropagation();
                    //noinspection TypeScriptUnresolvedFunction
                    this.dialog.dismiss();
                };
                ModalCheckPrint = __decorate([
                    core_1.Component({
                        selector: 'modal-content',
                        directives: [],
                        template: "\n        <div class=\"modal-header\">\n            <h2 class=\"header header-main\">Sicherheitsabfrage</h2>\n        </div>\n        <h2>Soll ein <u>{{type}}</u> erstellt werden?</h2>\n        <div class=\"modal-footer\">\n            <button class=\"buttonSmall button-action\" (click)=\"ok($event)\">OK</button>\n            <button class=\"buttonSmall button-ok\" (click)=\"cancel($event)\">Abbruch</button>\n        </div>",
                    }), 
                    __metadata('design:paramtypes', [(typeof (_a = typeof angular2_modal_1.ModalDialogInstance !== 'undefined' && angular2_modal_1.ModalDialogInstance) === 'function' && _a) || Object, (typeof (_b = typeof angular2_modal_1.ICustomModal !== 'undefined' && angular2_modal_1.ICustomModal) === 'function' && _b) || Object])
                ], ModalCheckPrint);
                return ModalCheckPrint;
                var _a, _b;
            }());
            exports_1("ModalCheckPrint", ModalCheckPrint);
        }
    }
});
