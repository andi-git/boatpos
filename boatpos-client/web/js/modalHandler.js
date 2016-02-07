System.register(["angular2/core", "lib/angular2-modal"], function(exports_1) {
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
    var ModalHandler;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (angular2_modal_1_1) {
                angular2_modal_1 = angular2_modal_1_1;
            }],
        execute: function() {
            ModalHandler = (function () {
                function ModalHandler(modal, renderer) {
                    this.modal = modal;
                    this.renderer = renderer;
                }
                ModalHandler.prototype.open = function (component, context) {
                    //noinspection TypeScriptUnresolvedFunction
                    return this.modal.open(component, this.createBindings(context), new angular2_modal_1.ModalConfig("lg", true, null));
                };
                ModalHandler.prototype.createBindings = function (context) {
                    return core_1.Injector.resolve([
                        core_1.provide(angular2_modal_1.ICustomModal, { useValue: context }),
                        core_1.provide(core_1.Renderer, { useValue: this.renderer })
                    ]);
                };
                ModalHandler = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [(typeof (_a = typeof angular2_modal_1.Modal !== 'undefined' && angular2_modal_1.Modal) === 'function' && _a) || Object, core_1.Renderer])
                ], ModalHandler);
                return ModalHandler;
                var _a;
            })();
            exports_1("ModalHandler", ModalHandler);
        }
    }
});
