System.register(['angular2/core', "../../service/rental.service", "../../service/mode.service", "../../model/datePicker", "../../service/info.service"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, rental_service_1, mode_service_1, mode_service_2, datePicker_1, info_service_1;
    var RentalsComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (rental_service_1_1) {
                rental_service_1 = rental_service_1_1;
            },
            function (mode_service_1_1) {
                mode_service_1 = mode_service_1_1;
                mode_service_2 = mode_service_1_1;
            },
            function (datePicker_1_1) {
                datePicker_1 = datePicker_1_1;
            },
            function (info_service_1_1) {
                info_service_1 = info_service_1_1;
            }],
        execute: function() {
            RentalsComponent = (function () {
                function RentalsComponent(rentalService, modeService, info) {
                    var _this = this;
                    this.rentalService = rentalService;
                    this.modeService = modeService;
                    this.info = info;
                    this.datePicker = new datePicker_1.DatePicker();
                    console.log("constructor of RentalsComponent");
                    modeService.event().subscribe(function (mode) {
                        if (mode_service_2.Mode[mode_service_2.Mode[mode]] === mode_service_2.Mode.RENTALS) {
                            console.log("mode-change - RentalsComponent: " + mode_service_2.Mode[mode]);
                            _this.updateRentalsCurrentDay();
                        }
                    });
                    this.updateRentalsCurrentDay();
                }
                RentalsComponent.prototype.updateRentalsCurrentDay = function () {
                    var _this = this;
                    this.rentalService.loadAllForCurrentDay().subscribe(function (rentals) {
                        _this.rentalsCurrentDay = rentals;
                    });
                };
                RentalsComponent.prototype.dayChange = function (day) {
                    this.datePicker.setCurrentDay(day);
                };
                RentalsComponent.prototype.monthChange = function (month) {
                    this.datePicker.setCurrentMonth(month);
                };
                RentalsComponent.prototype.yearChange = function (year) {
                    this.datePicker.setCurrentYear(year);
                };
                RentalsComponent.prototype.selectDate = function () {
                    var _this = this;
                    this.rentalService.loadAllFor(this.datePicker.getCurrentYear(), this.datePicker.getCurrentMonthAsNumber(), this.datePicker.getCurrentDay()).subscribe(function (rentals) {
                        _this.rentalsCurrentDay = rentals;
                    });
                    this.info.event().emit("Datum " +
                        this.datePicker.getCurrentDay() + ". " +
                        this.datePicker.getCurrentMonthAsString() + " " +
                        this.datePicker.getCurrentYear() + " ausgewählt.");
                };
                RentalsComponent.prototype.resetDate = function () {
                    this.info.event().emit("Datum auf den heutigen Tag zurückgesetzt.");
                    this.datePicker.reset();
                    this.updateRentalsCurrentDay();
                };
                RentalsComponent = __decorate([
                    core_1.Component({
                        selector: 'rentals',
                        templateUrl: "html/component/rentals/rentals.component.html",
                        styleUrls: ["css/component/rentals/rentals.component.css"]
                    }), 
                    __metadata('design:paramtypes', [rental_service_1.RentalService, mode_service_1.ModeService, info_service_1.InfoService])
                ], RentalsComponent);
                return RentalsComponent;
            })();
            exports_1("RentalsComponent", RentalsComponent);
        }
    }
});
