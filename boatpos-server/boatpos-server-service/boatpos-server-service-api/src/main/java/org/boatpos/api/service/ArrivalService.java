package org.boatpos.api.service;

import org.boatpos.api.dto.Arrival;
import org.boatpos.api.dto.Rental;

/**
 * Service to perform an arrival.
 */
public interface ArrivalService {

    /**
     * Arrive a boat, i .e. end a rental.
     *
     * @param arrival the {@link Arrival}
     * @return the ended {@link Rental}
     */
    Rental depart(Arrival arrival);
}
