package org.boatpos.service.api;

import org.boatpos.service.api.bean.RentalBean;
import org.boatpos.service.api.bean.RentalDayNumberWrapper;

import java.time.LocalDate;
import java.util.List;

/**
 * Service for rentals.
 */
public interface RentalService {

    /**
     * Get a rental of the current day by it's day-id.
     *
     * @param rentalDayNumberWrapper the day-id to get the concrete rental of the current day
     * @return the rental
     */
    RentalBean get(RentalDayNumberWrapper rentalDayNumberWrapper);

    /**
     * Delete a rental.
     *
     * @param rentalDayNumberWrapper the day-id to get the concrete rental of the current day
     * @return the deleted rental
     */
    RentalBean delete(RentalDayNumberWrapper rentalDayNumberWrapper);

    /**
     * Undo the deletion of a rental.
     *
     * @param rentalDayNumberWrapper the day-id to get the concrete rental of the current day
     * @return the reactivated rental
     */
    RentalBean undoDelete(RentalDayNumberWrapper rentalDayNumberWrapper);

    /**
     * Get the next day-id.
     *
     * @return the next day-id
     */
    RentalDayNumberWrapper nextDayId();

    /**
     * Get all rentals for the current day.
     *
     * @return all rentals of the current day
     */
    List<RentalBean> getAllCurrentDay();

    /**
     * Get all rentals for a specified date.
     *
     * @param date the date
     * @return all rentals for the specified date
     */
    List<RentalBean> getAll(LocalDate date);
}
