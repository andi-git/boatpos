package org.boatpos.model;

import com.google.gson.annotations.Expose;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * A {@link PromotionEntity} that is handled before the {@link RentalEntity}.
 */
@SuppressWarnings("unused")
@Entity
@DiscriminatorValue("B")
public class PromotionBeforeEntity extends PromotionEntity {

    /**
     * The time-credit of this promotion in minutes.
     */
    @NotNull
    @Min(0)
    @Expose
    private Integer timeCredit;

    public PromotionBeforeEntity() {
    }

    public PromotionBeforeEntity(Long id, Integer version, String name, Integer timeCredit, String priceCalculation, Set<RentalEntity> rentals, Integer priority, boolean enabled, Character keyBinding, String pictureUrl, String pictureUrlThumb) {
        super(id, version, name, priceCalculation, rentals, priority, enabled, keyBinding, pictureUrl, pictureUrlThumb);
        this.timeCredit = timeCredit;
    }

    public Integer getTimeCredit() {
        return timeCredit;
    }

    public void setTimeCredit(Integer timeCredit) {
        this.timeCredit = timeCredit;
    }
}
