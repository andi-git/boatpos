package org.boatpos.service.api;

import org.boatpos.service.api.bean.RentalBean;
import org.boatpos.service.api.bean.RentalDayNumberWrapper;

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
}
