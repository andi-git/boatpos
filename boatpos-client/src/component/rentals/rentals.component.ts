import {Component, NgZone} from 'angular2/core';
import {Rental} from '../../model/rental';
import {RentalService} from "../../service/rental.service";
import {ModeService} from "../../service/mode.service";
import {Mode} from "../../service/mode.service";

@Component({
    selector: 'rentals',
    templateUrl: "html/component/rentals/rentals.component.html",
    styleUrls: ["css/component/rentals/rentals.component.css"]
})
export class RentalsComponent {

    rentalsCurrentDay:Array<Rental>;

    constructor(private rentalService:RentalService, private modeService:ModeService) {
        console.log("constructor of RentalsComponent");
        modeService.event().subscribe((mode) => {
            if (Mode[Mode[mode]] === Mode.RENTALS) {
                console.log("mode-change - RentalsComponent: " + Mode[mode]);
                this.updateRentalsCurrentDay();
            }
        });
        this.updateRentalsCurrentDay();
    }

    private updateRentalsCurrentDay() {
        this.rentalService.loadAllForCurrentDay().subscribe((rentals:Array<Rental>) => {
            this.rentalsCurrentDay = rentals;
        });
    }
}