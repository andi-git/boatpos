package org.boatpos.service.api;

import org.boatpos.service.api.bean.ArrivalBean;
import org.boatpos.service.api.bean.RentalBean;

/**
 * Service to perform an arrival.
 */
public interface ArrivalService {

    /**
     * Arrive a boat, i .e. end a rental.
     *
     * @param arrivalBean the {@link ArrivalBean}
     * @return the ended {@link RentalBean}
     */
    RentalBean arrive(ArrivalBean arrivalBean);
}
