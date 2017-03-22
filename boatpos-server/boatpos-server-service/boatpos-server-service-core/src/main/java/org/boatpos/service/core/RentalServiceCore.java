package org.boatpos.service.core;

import org.boatpos.common.util.datetime.DateTimeHelper;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.boatpos.common.util.qualifiers.Current;
import org.boatpos.domain.api.model.Rental;
import org.boatpos.domain.api.repository.RentalRepository;
import org.boatpos.domain.api.values.Day;
import org.boatpos.domain.api.values.DayId;
import org.boatpos.domain.api.values.Period;
import org.boatpos.service.api.ArrivalService;
import org.boatpos.service.api.RentalService;
import org.boatpos.service.api.bean.ArrivalBean;
import org.boatpos.service.api.bean.RentalBean;
import org.boatpos.service.api.bean.RentalDayNumberWrapper;
import org.boatpos.service.core.util.RentalBeanEnrichment;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
    private RentalBeanEnrichment rentalBeanEnrichment;

    @Inject
    private ArrivalService arrivalService;

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Override
    public RentalBean get(RentalDayNumberWrapper rentalDayNumberWrapper) {
        DayId dayId = new DayId(rentalDayNumberWrapper.getDayNumber());
        Rental rental = rentalRepository.loadBy(day, dayId).orElseGet(new ThrowExceptionRentalNotAvailable(dayId));
        if (!rental.isFinished().get() && !rental.isDeleted().get()) {
            arrivalService.arrive(new ArrivalBean(rental.getDayId().get()));
        }
        return rentalBeanEnrichment.asDto(rental);
    }

    @Override
    public RentalBean delete(RentalDayNumberWrapper rentalDayNumberWrapper) {
        DayId dayId = new DayId(rentalDayNumberWrapper.getDayNumber());
        return rentalBeanEnrichment.asDto(rentalRepository.delete(day, dayId)
                .orElseGet(new ThrowExceptionRentalNotAvailable(dayId)));
    }

    @Override
    public RentalBean undoDelete(RentalDayNumberWrapper rentalDayNumberWrapper) {
        DayId dayId = new DayId(rentalDayNumberWrapper.getDayNumber());
        return rentalBeanEnrichment.asDto(rentalRepository.undoDelete(day, dayId)
                .orElseGet(new ThrowExceptionRentalNotAvailable(dayId)));
    }

    @Override
    public RentalDayNumberWrapper nextDayId() {
        return new RentalDayNumberWrapper(rentalRepository.nextDayId(day).get());
    }

    @Override
    public List<RentalBean> getAllCurrentDay() {
        return rentalRepository.loadAll(Period.day(dateTimeHelper.currentDate())).stream().map(rental -> rentalBeanEnrichment.asDto(rental)).collect(Collectors.toList());
    }

    @Override
    public List<RentalBean> getAll(LocalDate date) {
        return rentalRepository.loadAll(Period.day(date)).stream().map(rental -> rentalBeanEnrichment.asDto(rental)).collect(Collectors.toList());
    }

    @Override
    public RentalBean get(LocalDate date, RentalDayNumberWrapper rentalDayNumberWrapper) {
        DayId dayId = new DayId(rentalDayNumberWrapper.getDayNumber());
        return rentalBeanEnrichment
                .asDto(rentalRepository.loadBy(new Day(date), dayId)
                        .orElseGet(new ThrowExceptionRentalNotAvailable(dayId)));
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
