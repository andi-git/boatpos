import {Component} from "angular2/core";
import {RentalService} from "../service/rental.service";

@Component({
    selector: 'signatureDevice',
    templateUrl: "html/component/signatureDevice.component.html",
    styleUrls: ["css/component/signatureDevice.component.css"]
})
export class SignatureDeviceComponent {

    constructor(private rentalService: RentalService) {

    }

    private textForSSE() {
        return this.rentalService.getSignatureDeviceAvailableText();
    }
}