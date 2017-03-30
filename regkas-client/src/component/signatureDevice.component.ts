import {Component} from "angular2/core";
import {SaleService} from "../service/sale.service";

@Component({
    selector: 'signatureDevice',
    templateUrl: "html/component/signatureDevice.component.html",
    styleUrls: ["css/component/signatureDevice.component.css"]
})
export class SignatureDeviceComponent {

    constructor(private saleService: SaleService) {

    }

    private textForSSE() {
        return this.saleService.getSignatureDeviceAvailableText();
    }
}