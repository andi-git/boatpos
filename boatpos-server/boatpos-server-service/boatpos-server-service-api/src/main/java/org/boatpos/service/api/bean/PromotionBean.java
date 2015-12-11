package org.boatpos.service.api.bean;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("unused")
public abstract class PromotionBean extends AbstractMasterDataBean {

    /**
     * The name for this promotion.
     */
    @NotNull
    @Size(min = 3)
    @Expose
    private String name;

    /**
     * A formula how to calculate the price.
     */
    @NotNull
    @Size(min = 3)
    @Expose
    private String priceCalculation;

    public PromotionBean() {
    }

    public PromotionBean(Long id, Integer version, String name, String priceCalculation, Integer priority, boolean enabled) {
        super(id, version, enabled, priority);
        this.name = name;
        this.priceCalculation = priceCalculation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriceCalculation() {
        return priceCalculation;
    }

    public void setPriceCalculation(String priceCalculation) {
        this.priceCalculation = priceCalculation;
    }
}
