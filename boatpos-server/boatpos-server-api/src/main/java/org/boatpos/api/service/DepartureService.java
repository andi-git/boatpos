package org.boatpos.api.service;

import org.boatpos.api.dto.Departure;
import org.boatpos.api.dto.Rental;

/**
 * Service to perform a departure.
 */
public interface DepartureService {

    /**
     * Depart a boat, i .e. start a rental.
     *
     * @param departure the {@link Departure}
     * @return the started {@link Rental}
     */
    Rental depart(Departure departure);
}
