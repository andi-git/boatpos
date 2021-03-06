package org.boatpos.domain.core.repository;

import org.boatpos.common.domain.api.values.Deleted;
import org.boatpos.common.domain.core.respository.DomainModelRepositoryCore;
import org.boatpos.domain.api.values.ArrivalTime;
import org.boatpos.domain.api.values.Day;
import org.boatpos.domain.api.values.DayId;
import org.boatpos.domain.api.values.DepartureTime;
import org.boatpos.domain.api.values.Period;
import org.boatpos.domain.api.values.PriceCalculatedBefore;
import org.boatpos.domain.core.model.RentalCore;
import org.boatpos.model.RentalEntity;
import org.boatpos.domain.api.builder.RentalBuilder;
import org.boatpos.domain.api.model.Boat;
import org.boatpos.domain.api.model.Commitment;
import org.boatpos.domain.api.model.PromotionBefore;
import org.boatpos.domain.api.model.Rental;
import org.boatpos.domain.api.repository.RentalRepository;
import org.boatpos.domain.core.builder.RentalBuilderCore;
import org.boatpos.common.util.datetime.DateTimeHelper;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class RentalRepositoryCore extends DomainModelRepositoryCore<Rental, RentalCore, RentalEntity, RentalBuilder, RentalBuilderCore> implements RentalRepository {

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Override
    public Optional<Rental> loadBy(Day day, DayId dayId) {
        checkNotNull(day, "'day' must not be null");
        checkNotNull(dayId, "'dayId' must not be null");
        return loadByParameter("rental.getByDayId", (query) -> query
                .setParameter("dayId", dayId.get())
                .setParameter("day", day.get()));
    }

    @Override
    public List<Rental> loadAllForCurrentDay() {
        return loadAll(Period.day(dateTimeHelper.currentDate()));
    }

    @Override
    public List<Rental> loadAll(Period period) {
        checkNotNull(period, "'period' must not be null");
        return loadAll("rental.getBetweenDate", RentalCore::new,
                (query) -> query
                        .setParameter("start", period.getStart())
                        .setParameter("end", period.getEnd()));
    }

    @Override
    public DayId nextDayId(Day day) {
        checkNotNull(day, "'day' must not be null");
        Optional<Integer> maxId = jpaHelper().getSingleResult(
                jpaHelper().createNamedQuery("rental.maxDayId", Integer.class)
                        .setParameter("day", day.get())
                        .getResultList());
        return new DayId(maxId.orElseGet(() -> 0) + 1);
    }

    @Override
    public Rental depart(Day day, DepartureTime departureTime, Boat boat, Set<Commitment> commitments, Optional<PromotionBefore> promotion, Optional<PriceCalculatedBefore> priceCalculatedBefore) {
        checkNotNull(day, "'day' must not be null");
        checkNotNull(boat, "'boat' must not be null");
        checkNotNull(promotion, "'promotion' must not be null");
        checkNotNull(priceCalculatedBefore, "'priceCalculatedBefore' must not be null");
        RentalBuilder builder = builder()
                .add(day)
                .add(nextDayId(day))
                .add(departureTime)
                .add(boat)
                .add(commitments);
        promotion.ifPresent(builder::add);
        priceCalculatedBefore.ifPresent(builder::add);
        return builder.build().persist();
    }

    @Override
    public Rental arrive(Day day, DayId dayId, ArrivalTime arrivalTime) {
        checkNotNull(dayId, "'dayId' must not be null");
        checkNotNull(day, "'day' must not be null");
        checkNotNull(arrivalTime, "'arrivalTime' must not be null");
        return loadRentalByDayIdOrThrowException(day, dayId)
                .setArrivalTime(arrivalTime)
                .persist();
    }

    @Override
    public List<Rental> loadAllActive() {
        return loadAll("rental.getAllActive", (r) -> new RentalBuilderCore().from(r), (q) -> q.setParameter("day", dateTimeHelper.currentDate()));
    }

    @Override
    public Optional<Rental> delete(Day day, DayId dayId) {
        Optional<Rental> rentalOptional = loadBy(day, dayId);
        return rentalOptional.isPresent() ? Optional.of(rentalOptional.get().setDeleted(Deleted.TRUE).persist()) : Optional.empty();
    }

    @Override
    public Optional<Rental> undoDelete(Day day, DayId dayId) {
        Optional<Rental> rentalOptional = loadBy(day, dayId);
        return rentalOptional.isPresent() ? Optional.of(rentalOptional.get().setDeleted(Deleted.FALSE).persist()) : Optional.empty();
    }

    private Rental loadRentalByDayIdOrThrowException(Day day, DayId dayId) {
        return loadBy(day, dayId).orElseGet(() -> {
            throw new RuntimeException("unable to get " + Rental.class.getName() + " with for day " + day + " with id " + dayId);
        });
    }
}
