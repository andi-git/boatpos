package org.boatpos.service.core;

import org.boatpos.repository.api.model.Rental;
import org.boatpos.repository.api.repository.RentalRepository;
import org.boatpos.repository.api.values.Day;
import org.boatpos.repository.api.values.DayId;
import org.boatpos.repository.api.values.Period;
import org.boatpos.service.api.ArrivalService;
import org.boatpos.service.api.RentalService;
import org.boatpos.service.api.bean.ArrivalBean;
import org.boatpos.service.api.bean.RentalBean;
import org.boatpos.service.api.bean.RentalDayNumberWrapper;
import org.boatpos.service.core.util.ModelDtoConverter;
import org.boatpos.service.core.util.RentalBeanEnrichment;
import org.boatpos.util.datetime.DateTimeHelper;
import org.boatpos.util.log.LogWrapper;
import org.boatpos.util.log.SLF4J;
import org.boatpos.util.qualifiers.Current;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
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
    private RentalBeanEnrichment rentalBeanEnrichment;

    @Inject
    private ArrivalService arrivalService;

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Inject
    private ModelDtoConverter modelDtoConverter;

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
        return modelDtoConverter.convert(rentalRepository.loadAll(Period.day(dateTimeHelper.currentDate())));
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
