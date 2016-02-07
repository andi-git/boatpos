System.register(['angular2/core'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1;
    var KeyBindingService;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            }],
        execute: function() {
            KeyBindingService = (function () {
                function KeyBindingService() {
                    // cache all key-bindings for main-dialog
                    this.keyBindingsForMain = {};
                    // cache all key-bindings for dialog 'info'
                    this.keyBindingsForDialogInfo = {};
                    // cache all key-bindings for dialog 'deleted'
                    this.keyBindingsForDialogDeleted = {};
                    this.mouseTrap = new Mousetrap();
                }
                KeyBindingService.prototype.bind = function (key, callback, action) {
                    this.mouseTrap.bind(key, callback, action);
                };
                /**
                 * Add key-bindings for the main-dialog
                 * @param keyBindings
                 */
                KeyBindingService.prototype.addBindingForMain = function (keyBindings) {
                    for (var key in keyBindings) {
                        this.keyBindingsForMain[key] = keyBindings[key];
                        // on init the main-dialog is active, so add the bindings to mousetrap
                        this.bind(key, keyBindings[key]);
                    }
                };
                KeyBindingService.prototype.addBindingForDialogInfo = function (keyBindings) {
                    for (var key in keyBindings) {
                        this.keyBindingsForDialogInfo[key] = keyBindings[key];
                    }
                };
                KeyBindingService.prototype.addBindingForDialogDeleted = function (keyBindings) {
                    for (var key in keyBindings) {
                        this.keyBindingsForDialogDeleted[key] = keyBindings[key];
                    }
                };
                KeyBindingService.prototype.setKeyBindings = function (keyBindings) {
                    this.mouseTrap.reset();
                    for (var key in keyBindings) {
                        this.bind(key, keyBindings[key]);
                    }
                };
                KeyBindingService.prototype.focusMain = function () {
                    this.setKeyBindings((this.keyBindingsForMain));
                };
                KeyBindingService.prototype.focusDialogInfo = function () {
                    this.setKeyBindings(this.keyBindingsForDialogInfo);
                };
                KeyBindingService.prototype.focusDialogDeleted = function () {
                    this.setKeyBindings(this.keyBindingsForDialogDeleted);
                };
                KeyBindingService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [])
                ], KeyBindingService);
                return KeyBindingService;
            })();
            exports_1("KeyBindingService", KeyBindingService);
        }
    }
});
