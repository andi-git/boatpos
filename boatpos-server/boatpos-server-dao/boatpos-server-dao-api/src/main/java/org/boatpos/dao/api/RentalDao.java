package org.boatpos.dao.api;

import org.boatpos.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * The DAO for {@link Rental}.
 */
public interface RentalDao extends GenericDao<Rental> {

    /**
     * Get a {@link Rental} by it's {@link Rental#dayId} for the assigned day.
     *
     * @param dayId the {@link Rental#dayId} of the {@link Rental}
     * @param day   the day of the dayId
     * @return a {@link Rental} with the {@link Rental#dayId} for the assigned day
     */
    Optional<Rental> getByDayId(Integer dayId, LocalDate day);

    /**
     * Get all {@link Rental}s for the current day.
     *
     * @return all {@link Rental}s for the current day
     */
    List<Rental> getAllForCurrentDay();

    /**
     * Get all {@link Rental}s for a specific {@link Period}.
     *
     * @param day    the specific day as {@link LocalDate}
     * @param period the {@link Period} to get the {@link Rental}s for
     * @return all {@link Rental}s for the specific day
     */
    List<Rental> getAllFor(LocalDate day, Period period);

    /**
     * Get the next {@link Rental#dayId}.
     * <p>
     * <u>Warning</u>: to get the next {@code dayId} via this method can cause problems when there are multiple
     * instances of the backend available or multiple rentals per second. Then it is not guaranteed that this id is
     * unique. It will be better to have database-functionality to fill this id. For the current usage (one instance of
     * the backend and a rental-transaction every minute) it is totally ok.
     *
     * @param date the current day
     * @return the next {@link Rental#dayId}
     */
    Integer nextDayId(LocalDate date);

    /**
     * Departure of a {@link Boat}.
     *
     * @param boat        the {@link Boat}
     * @param commitments the {@link Commitment}s
     * @param promotion   the optional {@link Promotion}
     * @param time        the take-off-time
     * @return the persistent {@link Rental}
     */
    Rental depart(Boat boat, Set<Commitment> commitments, Optional<Promotion> promotion, LocalDateTime time);

    /**
     * The {@link Rental#dayId} that arrives.
     *
     * @param dayId     {@link Rental#dayId} that arrives
     * @param time      the time of the arrival
     * @param promotion the possible {@link Promotion}
     * @return the finished {@link Rental}
     */
    Rental arrive(Integer dayId, LocalDateTime time, Optional<Promotion> promotion);

    /**
     * The price to be paid.
     *
     * @param dayId  {@link Rental#dayId} that arrives
     * @param day    the day of the dayId
     * @param price  the price to be paid
     * @param coupon check if a coupon was used
     * @return the finished {@link Rental} including the price
     */
    Rental pay(Integer dayId, LocalDate day, BigDecimal price, PaymentMethod paymentMethod, boolean coupon);

    /**
     * Delete a {@link Rental}.
     *
     * @param dayId the {@link Rental#dayId} of the {@link Rental} to delete
     * @param day   the day of the dayId
     */
    void delete(Integer dayId, LocalDate day);

    /**
     * Undo the delete a {@link Rental}.
     *
     * @param dayId the {@link Rental#dayId} of the {@link Rental} to undo the delete
     * @param day   the day of the dayId
     */
    void undoDelete(Integer dayId, LocalDate day);
}
