package org.boatpos.repository.core.repository;

import org.boatpos.model.RentalEntity;
import org.boatpos.repository.api.builder.RentalBuilder;
import org.boatpos.repository.api.model.Boat;
import org.boatpos.repository.api.model.Commitment;
import org.boatpos.repository.api.model.PromotionBefore;
import org.boatpos.repository.api.model.Rental;
import org.boatpos.repository.api.repository.RentalRepository;
import org.boatpos.repository.api.values.*;
import org.boatpos.repository.core.builder.RentalBuilderCore;
import org.boatpos.repository.core.model.RentalCore;
import org.boatpos.common.util.datetime.DateTimeHelper;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class RentalRepositoryCore extends DomainModelRepositoryCore<Rental, RentalCore, RentalEntity> implements RentalRepository {

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Override
    public RentalBuilder builder() {
        return new RentalBuilderCore();
    }

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
        if (promotion.isPresent()) {
            builder.add(promotion.get());
        }
        if (priceCalculatedBefore.isPresent()) {
            builder.add(priceCalculatedBefore.get());
        }
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
