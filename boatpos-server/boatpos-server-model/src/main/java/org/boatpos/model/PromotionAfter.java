package org.boatpos.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Set;

/**
 * A {@link Promotion} that is handled before the {@link Rental}.
 */
@SuppressWarnings("unused")
@Entity
@DiscriminatorValue("A")
public class PromotionAfter extends Promotion {

    public PromotionAfter() {
    }

    public PromotionAfter(Long id, Integer version, String name, String priceCalculation, Set<Rental> rentals) {
        super(id, version, name, priceCalculation, rentals);
    }
}
