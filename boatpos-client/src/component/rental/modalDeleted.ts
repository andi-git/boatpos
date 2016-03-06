import {Component, provide, ElementRef, Injector} from 'angular2/core';
import {NgIf} from 'angular2/common';
//noinspection TypeScriptCheckImport
import {Modal, ModalConfig, ICustomModal, ModalDialogInstance, ICustomModalComponent} from "lib/angular2-modal";
import {KeyBindingService} from "service/keybinding.service";

export class ModalDeletedContext {
    constructor(public deletedInfo:string, public keyBinding:KeyBindingService) {
    }
}

@Component({
    selector: 'modal-content',
    directives: [NgIf],
    template: `<div class="modal-header">
        <h2 class="header header-main">Gelöscht!</h2>
        </div>
        <div class="modal-body">
            <p>{{deletedInfo}}</p>
        <div class="modal-footer">
            <button class="buttonSmall button-ok" (click)="close($event)">Schließen</button>
        </div>`,
})
export class ModalDeleted implements ICustomModalComponent {

    private dialog:ModalDialogInstance;
    private deletedInfo:string;
    private keyBinding:KeyBindingService;

    constructor(dialog:ModalDialogInstance, context:ICustomModal) {
        this.dialog = dialog;
        this.deletedInfo = (<ModalDeletedContext>context).deletedInfo;
        this.keyBinding = (<ModalDeletedContext>context).keyBinding;
        let map:{[key: string] : ((e:ExtendedKeyboardEvent, combo:string) => any)} = {
            'O': () => {
                this.cancel();
            }
        };
        this.keyBinding.addBindingForDialogDeleted(map);
        this.keyBinding.focusDialogDeleted();
    }

    close($event) {
        $event.stopPropagation();
        //noinspection TypeScriptUnresolvedFunction
        this.dialog.close(true);
    }

    cancel() {
        //noinspection TypeScriptUnresolvedFunction
        this.dialog.dismiss();
    }
}
