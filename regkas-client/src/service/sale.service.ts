import {Injectable} from "angular2/core";
import {InfoService} from "./info.service";
import {ReceiptElement} from "../model/receiptElement";

@Injectable()
export class SaleService {

    private numberInput:string;

    private receiptElements:Array<ReceiptElement> = [];

    constructor(private infoService:InfoService) {

    }

    getNumberInput():string {
        return this.numberInput;
    }

    setNumberInput(numberInput:string) {
        if ('<' === numberInput) {
            this.numberInput.substr(0, this.numberInput.length - 1);
        } else {
            this.numberInput = (this.numberInput == null ? "" : this.numberInput) + numberInput;
        }
        this.infoService.event().emit("Nummer eingegeben.");
    }

    cancelLastElement() {
        this.receiptElements.pop();
        this.infoService.event().emit("Letztes Element gelöscht.");
    }

    cancelAllElements() {
        this.receiptElements = [];
        this.infoService.event().emit("Alle Elemente gelöscht.");
    }

    bill() {

    }

    getReceiptElements():Array<ReceiptElement> {
        return this.receiptElements;
    }
}