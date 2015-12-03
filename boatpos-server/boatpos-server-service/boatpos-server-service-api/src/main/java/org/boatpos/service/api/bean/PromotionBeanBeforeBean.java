package org.boatpos.service.api.bean;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * A {@link PromotionBean} that is handled before the rental.
 */
@SuppressWarnings("unused")
public class PromotionBeanBeforeBean extends PromotionBean {

    /**
     * The time-credit of this promotion in minutes.
     */
    @NotNull
    @Min(0)
    @Expose
    private Integer timeCredit;

    public PromotionBeanBeforeBean() {
    }

    public PromotionBeanBeforeBean(Long id, Integer version, String name, Integer timeCredit, String priceCalculation, Integer priority) {
        super(id, version, name, priceCalculation, priority);
        this.timeCredit = timeCredit;
    }

    public Integer getTimeCredit() {
        return timeCredit;
    }

    public void setTimeCredit(Integer timeCredit) {
        this.timeCredit = timeCredit;
    }
}
