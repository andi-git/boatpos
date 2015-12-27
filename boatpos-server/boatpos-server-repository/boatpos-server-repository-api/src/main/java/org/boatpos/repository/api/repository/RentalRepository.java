package org.boatpos.repository.api.repository;

import org.boatpos.model.PaymentMethod;
import org.boatpos.repository.api.builder.RentalBuilder;
import org.boatpos.repository.api.model.*;
import org.boatpos.repository.api.values.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * The repository for the {@link Rental}.
 */
public interface RentalRepository extends DomainModelRepository<Rental> {

    /**
     * Get the {@link RentalBuilder}.
     *
     * @return the {@link RentalBuilder}
     */
    RentalBuilder builder();

    /**
     * Get a {@link Rental} by it's {@link DayId} for the assigned day.
     *
     * @param day   the {@link Day} of the {@link DayId}
     * @param dayId the {@link DayId} of the {@link Rental}
     * @return a {@link Rental} with the {@link DayId} for the assigned day
     */
    Optional<Rental> loadBy(Day day, DayId dayId);

    /**
     * Get all {@link Rental}s for the current day.
     *
     * @return all {@link Rental}s for the current day
     */
    List<Rental> loadAllForCurrentDay();

    /**
     * Get all {@link Rental}s for a specific {@link Period}.
     *
     * @param period the {@link Period} to get the {@link Rental}s for
     * @return all {@link Rental}s for the specific date
     */
    List<Rental> loadAll(Period period);

    /**
     * Get the next day-id for the {@link Rental}s.
     * <p>
     * <u>Warning</u>: to get the next {@code dayId} via this method can cause problems when there are multiple
     * instances of the backend available or multiple rentals per second. Then it is not guaranteed that this id is
     * unique. It will be better to have database-functionality to fill this id. For the current usage (one instance of
     * the backend and a rental-transaction every minute) it is totally ok.
     *
     * @param day the current {@link Day}
     * @return the next day-id
     */
    DayId nextDayId(Day day);

    /**
     * Departure of a {@link Boat}.
     *
     * @param day           the {@link Day} of the {@link Rental}
     * @param departureTime the take-off-time
     * @param boat          the {@link Boat}
     * @param commitments   the {@link Commitment}s
     * @param promotion     the optional {@link PromotionBefore}
     * @return the persistent {@link Rental}
     */
    Rental depart(Day day, DepartureTime departureTime, Boat boat, Set<Commitment> commitments, Optional<PromotionBefore> promotion);

    /**
     * A {@link Boat} arrives.
     *
     * @param day         the {@link Day} of the dayId
     * @param dayId       the {@link DayId} that arrives
     * @param arrivalTime the time of the arrival
     * @return the finished {@link Rental}
     */
    Rental arrive(Day day, DayId dayId, ArrivalTime arrivalTime);

    /**
     * The price to be paid.
     *
     * @param day       the {@link Day} of the dayId
     * @param dayId     the {@link DayId} that arrives
     * @param promotion the {@link PromotionAfter} of the {@link Rental}
     * @param pricePaid the {@link PricePaid} to be paid
     * @return the finished {@link Rental} including the price
     */
    Rental pay(Day day, DayId dayId, PricePaid pricePaid, Optional<PromotionAfter> promotion, PaymentMethod paymentMethod);
}
