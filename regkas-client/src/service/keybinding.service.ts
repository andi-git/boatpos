import {Injectable} from "angular2/core";

@Injectable()
export class KeyBindingService {

    private mouseTrap:MousetrapStatic;

    // cache all key-bindings for main-dialog
    private keyBindingsForMain:{[key:string]:(e:ExtendedKeyboardEvent, combo:string) => any} = {};

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
    addBindingForMain(keyBindings:{[key:string]:((e:ExtendedKeyboardEvent, combo:string) => any)}):void {
        for (var key in keyBindings) {
            this.keyBindingsForMain[key] = keyBindings[key];
            // on init the main-dialog is active, so add the bindings to mousetrap
            this.bind(key, keyBindings[key]);
        }
    }

    private setKeyBindings(keyBindings:{[key:string]:((e:ExtendedKeyboardEvent, combo:string) => any)}) {
        this.mouseTrap.reset();
        for (var key in keyBindings) {
            this.bind(key, keyBindings[key]);
        }
    }

    focusMain() {
        this.setKeyBindings((this.keyBindingsForMain));
    }
}
