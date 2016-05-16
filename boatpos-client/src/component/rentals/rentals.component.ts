import {Component} from 'angular2/core';
import {Rental} from '../../model/rental';
import {RentalService} from "../../service/rental.service";
import {ModeService} from "../../service/mode.service";
import {Mode} from "../../service/mode.service";
import {DatePicker} from "../../model/datePicker";
import {InfoService} from "../../service/info.service";

@Component({
    selector: 'rentals',
    templateUrl: "html/component/rentals/rentals.component.html",
    styleUrls: ["css/component/rentals/rentals.component.css"]
})
export class RentalsComponent {

    rentalsCurrentDay:Array<Rental>;

    datePicker:DatePicker = new DatePicker();

    constructor(private rentalService:RentalService, private modeService:ModeService, private info:InfoService) {
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

    dayChange(day:number) {
        this.datePicker.setCurrentDay(day);
    }

    monthChange(month:string) {
        this.datePicker.setCurrentMonth(month);
    }

    yearChange(year:number) {
        this.datePicker.setCurrentYear(year);
    }

    private selectDate() {
        this.rentalService.loadAllFor(this.datePicker.getCurrentYear(), this.datePicker.getCurrentMonthAsNumber(), this.datePicker.getCurrentDay()).subscribe((rentals:Array<Rental>) => {
            this.rentalsCurrentDay = rentals;
        });
        this.info.event().emit("Datum " +
            this.datePicker.getCurrentDay() + ". " +
            this.datePicker.getCurrentMonthAsString() + " " +
            this.datePicker.getCurrentYear() + " ausgewählt.");
    }

    private resetDate() {
        this.info.event().emit("Datum auf den heutigen Tag zurückgesetzt.");
        this.datePicker.reset();
        this.updateRentalsCurrentDay();
    }
}