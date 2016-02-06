import {Injectable} from 'angular2/core';

@Injectable()
export class KeyBindingService {

    private mouseTrap:MousetrapStatic;

    // cache all key-bindings for main-dialog
    private keyBindingsForMain:{K: (Function)} = {K: Function};

    // cache all key-bindings for main-dialog
    private keyBindingsForDialogInfo:{K: (Function)} = {K: Function};

    constructor() {
        this.mouseTrap = new Mousetrap();
    }

    private bind(key:string, callback:(e:ExtendedKeyboardEvent, combo:string) => any, action?:string) {
        this.mouseTrap.bind(key, callback, action);
    }

    /**
     * Add key-bindings for the main-dialog
     * @param keyBindings
     */
    addBindingForMain(keyBindings:{K: (Function)}):void {
        for (var key in keyBindings) {
            this.keyBindingsForMain[key] = keyBindings[key];
            // on init the main-dialog is active, so add the bindings to mousetrap
            this.bind(key, keyBindings[key]);
        }
    }

    addBindingForDialogInfo(keyBindings:{K: (Function)}):void {
        for (var key in keyBindings) {
            this.bind(key, keyBindings[key]);
        }
    }

    private setKeyBindings(keyBindings:{K: (Function)}) {
        this.mouseTrap.reset();
        for (var key in keyBindings) {
            this.bind(key, keyBindings[key]);
        }
    }

    focusMain() {
        this.setKeyBindings((this.keyBindingsForMain));
    }

    focusDialogInfo() {
        this.setKeyBindings(this.keyBindingsForDialogInfo);
    }
}
