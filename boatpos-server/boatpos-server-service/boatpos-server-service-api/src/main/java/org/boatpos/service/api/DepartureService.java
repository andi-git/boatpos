package org.boatpos.service.api;

import org.boatpos.service.api.bean.DepartureBean;
import org.boatpos.service.api.bean.RentalBean;

/**
 * Service to perform a departure.
 */
public interface DepartureService {

    /**
     * Depart a boat, i .e. start a rental.
     *
     * @param departureBean the {@link DepartureBean}
     * @return the started {@link RentalBean}
     */
    RentalBean depart(DepartureBean departureBean);
}
