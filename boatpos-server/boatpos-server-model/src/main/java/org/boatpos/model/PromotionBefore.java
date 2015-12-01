package org.boatpos.model;

import com.google.gson.annotations.Expose;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * A {@link Promotion} that is handled before the {@link Rental}.
 */
@SuppressWarnings("unused")
@Entity
@DiscriminatorValue("B")
public class PromotionBefore extends Promotion {

    /**
     * The time-credit of this promotion in minutes.
     */
    @NotNull
    @Min(0)
    @Expose
    private Integer timeCredit;

    public PromotionBefore() {
    }

    public PromotionBefore(Long id, Integer version, String name, Integer timeCredit, String priceCalculation, Set<Rental> rentals) {
        super(id, version, name, priceCalculation, rentals);
        this.timeCredit = timeCredit;
    }

    public Integer getTimeCredit() {
        return timeCredit;
    }

    public void setTimeCredit(Integer timeCredit) {
        this.timeCredit = timeCredit;
    }
}
