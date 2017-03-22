package org.boatpos.domain.api.repository;

import org.boatpos.common.domain.api.repository.DomainModelRepository;
import org.boatpos.domain.api.builder.RentalBuilder;
import org.boatpos.domain.api.model.Commitment;
import org.boatpos.domain.api.model.PromotionBefore;
import org.boatpos.domain.api.model.Rental;
import org.boatpos.domain.api.values.ArrivalTime;
import org.boatpos.domain.api.values.Day;
import org.boatpos.domain.api.values.DayId;
import org.boatpos.domain.api.values.DepartureTime;
import org.boatpos.domain.api.values.Period;
import org.boatpos.domain.api.values.PriceCalculatedBefore;
import org.boatpos.domain.api.model.Boat;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * The repository for the {@link Rental}.
 */
public interface RentalRepository extends DomainModelRepository<Rental, RentalBuilder> {

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
     * @param day                   the {@link Day} of the {@link Rental}
     * @param departureTime         the take-off-time
     * @param boat                  the {@link Boat}
     * @param commitments           the {@link Commitment}s
     * @param promotion             the optional {@link PromotionBefore}
     * @param priceCalculatedBefore the price calculated before the {@link Rental}
     * @return the persistent {@link Rental}
     */
    Rental depart(Day day, DepartureTime departureTime, Boat boat, Set<Commitment> commitments, Optional<PromotionBefore> promotion, Optional<PriceCalculatedBefore> priceCalculatedBefore);

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
     * Load all active {@link Rental}s for the current day (not finished, not deleted).
     *
     * @return a {@link List} of all active {@link Rental}s
     */
    List<Rental> loadAllActive();

    /**
     * Delete a {@link Rental} (set the flag 'deleted').
     *
     * @param day   the {@link Day} of the dayId
     * @param dayId the {@link DayId} that arrives
     * @return the deleted {@link Rental}
     */
    Optional<Rental> delete(Day day, DayId dayId);

    /**
     * Undo a deletion of a {@link Rental} (remove the flag 'deleted').
     *
     * @param day   the {@link Day} of the dayId
     * @param dayId the {@link DayId} that arrives
     * @return the reactivated {@link Rental}
     */
    Optional<Rental> undoDelete(Day day, DayId dayId);
}
