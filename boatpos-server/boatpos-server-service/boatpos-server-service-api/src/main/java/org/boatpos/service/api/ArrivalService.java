package org.boatpos.service.api;

import org.boatpos.service.api.bean.*;
import org.regkas.service.api.bean.BillBean;

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
     * Remove all promotions-after of a rental.
     *
     * @param removePromotionsAfterBean the promotion-after
     * @return the current rental
     */
    RentalBean removePromotionsAfter(RemovePromotionsAfterBean removePromotionsAfterBean);

    /**
     * Perform a payment after the rental.
     *
     * @param paymentBean the bill of the payment
     * @return the current rental
     */
    BillBean pay(PaymentBean paymentBean);

    Boolean isSignatureDeviceAvailable();
}
