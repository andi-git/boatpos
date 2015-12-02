package org.boatpos.api.dto;

/**
 * A {@link Promotion} that is handled before the rental.
 */
@SuppressWarnings("unused")
public class PromotionAfter extends Promotion {

    public PromotionAfter() {
    }

    public PromotionAfter(Long id, Integer version, String name, String priceCalculation, Integer priority) {
        super(id, version, name, priceCalculation, priority);
    }
}
