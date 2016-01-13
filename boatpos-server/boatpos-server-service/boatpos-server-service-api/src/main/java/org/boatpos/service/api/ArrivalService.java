package org.boatpos.service.api;

import org.boatpos.service.api.bean.*;

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

    /**
     * Add a promotion after the rental.
     *
     * @param addPromotionBean the promotion to add
     * @return the current rental
     */
    RentalBean addPromotion(AddPromotionBean addPromotionBean);

    /**
     * Perform a payment after the rental.
     *
     * @param paymentBean the bill of the payment
     * @return the current rental
     */
    BillBean pay(PaymentBean paymentBean);
}
