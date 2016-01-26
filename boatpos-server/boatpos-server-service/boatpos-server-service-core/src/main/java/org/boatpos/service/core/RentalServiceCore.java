package org.boatpos.service.core;

import org.boatpos.repository.api.model.Rental;
import org.boatpos.repository.api.repository.RentalRepository;
import org.boatpos.repository.api.values.Day;
import org.boatpos.repository.api.values.DayId;
import org.boatpos.service.api.RentalService;
import org.boatpos.service.api.bean.RentalBean;
import org.boatpos.service.api.bean.RentalDayNumberWrapper;
import org.boatpos.util.log.LogWrapper;
import org.boatpos.util.log.SLF4J;
import org.boatpos.util.qualifiers.Current;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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

    @Override
    public RentalBean get(RentalDayNumberWrapper rentalDayNumberWrapper) {
        DayId dayId = new DayId(rentalDayNumberWrapper.getDayNumber());
        return rentalRepository.loadBy(day, dayId)
                .orElseGet(new ThrowExceptionRentalNotAvailable(dayId))
                .asDto();
    }

    @Override
    public RentalBean delete(RentalDayNumberWrapper rentalDayNumberWrapper) {
        DayId dayId = new DayId(rentalDayNumberWrapper.getDayNumber());
        return rentalRepository.delete(day, dayId)
                .orElseGet(new ThrowExceptionRentalNotAvailable(dayId))
                .asDto();
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
