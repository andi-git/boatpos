import {Component} from "angular2/core";
//noinspection TypeScriptCheckImport
import {Modal, ModalConfig, ICustomModal, ModalDialogInstance, ICustomModalComponent} from 'lib/angular2-modal';

export class ModalCheckPrintContext {
    constructor(public type: string) {
    }
}

// this class is ugly because of a bug in angluar2-beta-0: https://github.com/angular/angular/issues/4330
@Component({
    selector: 'modal-content',
    directives: [],
    template: `
        <div class="modal-header">
            <h2 class="header header-main">Sicherheitsabfrage</h2>
        </div>
        <h2>Soll ein <u>{{type}}</u> erstellt werden?</h2>
        <div class="modal-footer">
            <button class="buttonSmall button-action" (click)="ok($event)">OK</button>
            <button class="buttonSmall button-ok" (click)="cancel($event)">Abbruch</button>
        </div>`,
})
export class ModalCheckPrint implements ICustomModalComponent {

    private dialog:ModalDialogInstance;
    private type: string;

    constructor(dialog: ModalDialogInstance, modelContentData: ICustomModal) {
        this.dialog = dialog;
        this.type = (<ModalCheckPrintContext> modelContentData).type;
    }

    ok($event) {
        $event.stopPropagation();
        //noinspection TypeScriptUnresolvedFunction
        this.dialog.close(true);
    }

    cancel($event) {
        $event.stopPropagation();
        //noinspection TypeScriptUnresolvedFunction
        this.dialog.dismiss();
    }
}
