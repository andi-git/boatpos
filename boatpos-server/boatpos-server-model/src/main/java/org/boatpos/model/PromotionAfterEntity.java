package org.boatpos.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Set;

/**
 * A {@link PromotionEntity} that is handled before the {@link RentalEntity}.
 */
@SuppressWarnings("unused")
@Entity
@DiscriminatorValue("A")
public class PromotionAfterEntity extends PromotionEntity {

    public PromotionAfterEntity() {
    }

    public PromotionAfterEntity(Long id, Integer version, String name, String priceCalculation, Set<RentalEntity> rentals, Integer priority, boolean enabled) {
        super(id, version, name, priceCalculation, rentals, priority, enabled);
    }
}
