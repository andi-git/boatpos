package org.boatpos.service.api.bean;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * A {@link PromotionBean} that is handled before the rental.
 */
@SuppressWarnings("unused")
public class PromotionBeforeBean extends PromotionBean {

    /**
     * The time-credit of this promotion in minutes.
     */
    @NotNull
    @Min(0)
    @Expose
    private Integer timeCredit;

    public PromotionBeforeBean() {
    }

    public PromotionBeforeBean(Long id, Integer version, String name, Integer timeCredit, String priceCalculation, Integer priority, boolean enabled, Character keyBinding, String pictureUrl, String pictureUrlThumb) {
        super(id, version, name, priceCalculation, priority, enabled, keyBinding, pictureUrl, pictureUrlThumb);
        this.timeCredit = timeCredit;
    }

    public Integer getTimeCredit() {
        return timeCredit;
    }

    public void setTimeCredit(Integer timeCredit) {
        this.timeCredit = timeCredit;
    }
}
