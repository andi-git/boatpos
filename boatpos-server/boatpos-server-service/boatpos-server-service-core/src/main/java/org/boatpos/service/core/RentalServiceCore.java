package org.boatpos.service.core;

import org.boatpos.repository.api.model.Rental;
import org.boatpos.repository.api.repository.RentalRepository;
import org.boatpos.repository.api.values.ArrivalTime;
import org.boatpos.repository.api.values.Day;
import org.boatpos.repository.api.values.DayId;
import org.boatpos.service.api.RentalService;
import org.boatpos.service.api.bean.RentalBean;
import org.boatpos.service.api.bean.RentalDayNumberWrapper;
import org.boatpos.util.datetime.DateTimeHelper;
import org.boatpos.util.log.LogWrapper;
import org.boatpos.util.log.SLF4J;
import org.boatpos.util.qualifiers.Current;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.function.Supplier;

@RequestScoped
public class RentalServiceCore implements RentalService {

    @Inject
    @SLF4J
    private LogWrapper log;

    @Inject
    private RentalRepository rentalRepository;

    @Inject
    @Current
    private Day day;

    @Inject
    private PriceCalculator priceCalculator;

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Override
    public RentalBean get(RentalDayNumberWrapper rentalDayNumberWrapper) {
        DayId dayId = new DayId(rentalDayNumberWrapper.getDayNumber());
        return asDto(rentalRepository.loadBy(day, dayId)
                .orElseGet(new ThrowExceptionRentalNotAvailable(dayId)));
    }

    @Override
    public RentalBean delete(RentalDayNumberWrapper rentalDayNumberWrapper) {
        DayId dayId = new DayId(rentalDayNumberWrapper.getDayNumber());
        return asDto(rentalRepository.delete(day, dayId)
                .orElseGet(new ThrowExceptionRentalNotAvailable(dayId)));
    }

    @Override
    public RentalBean undoDelete(RentalDayNumberWrapper rentalDayNumberWrapper) {
        DayId dayId = new DayId(rentalDayNumberWrapper.getDayNumber());
        return asDto(rentalRepository.undoDelete(day, dayId)
                .orElseGet(new ThrowExceptionRentalNotAvailable(dayId)));
    }

    private RentalBean asDto(Rental rental) {
        RentalBean rentalBean = rental.asDto();

        // add time of travel
        ArrivalTime tmpArrivalTime = new ArrivalTime(dateTimeHelper.currentTime());
        if (rental.getArrivalTime().isPresent()) {
            tmpArrivalTime = rental.getArrivalTime();
        }
        int timeOfTravel = (int) rental.getDepartureTime().get().until(tmpArrivalTime.get(), ChronoUnit.MINUTES);
        int timeOfTravelCalculated = timeOfTravel > PriceCalculator.TIME_BONUS_IN_MINUTES ? (int) (timeOfTravel - PriceCalculator.TIME_BONUS_IN_MINUTES) : 0;
        rentalBean.setTimeOfTravel(timeOfTravel);
        rentalBean.setTimeOfTravelCalculated(timeOfTravelCalculated);

        // calculate price if not available
        if (!rental.getPriceCalculatedAfter().isPresent()) {
            rentalBean.setPriceCalculatedAfter(priceCalculator.calculate(rental.getDepartureTime(), tmpArrivalTime, rental.getBoat(), rental.getPromotion()).get());
        }
        return rentalBean;
    }


    @Override
    public RentalDayNumberWrapper nextDayId() {
        return new RentalDayNumberWrapper(rentalRepository.nextDayId(day).get());
    }

    private static class ThrowExceptionRentalNotAvailable implements Supplier<Rental> {

        private final DayId dayId;

        public ThrowExceptionRentalNotAvailable(DayId dayId) {
            this.dayId = dayId;
        }

        @Override
        public Rental get() {
            throw new RuntimeException("unable to get " + Rental.class.getName() + " for current day with id: " + dayId);
        }
    }
}
