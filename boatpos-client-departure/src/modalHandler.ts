import {Renderer, provide, Injector, Injectable} from "angular2/core";
//noinspection TypeScriptCheckImport
import {Modal, ModalConfig, ICustomModal, ModalDialogInstance} from "lib/angular2-modal";

@Injectable()
export class ModalHandler {

    private dialog:Promise<ModalDialogInstance>;

    constructor(private modal:Modal,
                private renderer:Renderer) {
    }

    open(component:any, context:any):Promise<ModalDialogInstance> {
        //noinspection TypeScriptUnresolvedFunction
        return this.modal.open(<any>component, this.createBindings(context), new ModalConfig("lg", true, null));
    }

    private createBindings(context:any):any {
        return Injector.resolve([
            provide(ICustomModal, {useValue: context}),
            provide(Renderer, {useValue: this.renderer})
        ]);
    }
}