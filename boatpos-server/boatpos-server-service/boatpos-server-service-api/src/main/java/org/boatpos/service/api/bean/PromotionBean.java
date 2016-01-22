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
    private String formulaPrice;

    public PromotionBean() {
    }

    public PromotionBean(Long id, Integer version, String name, String formulaPrice, Integer priority, boolean enabled, Character keyBinding) {
        super(id, version, enabled, priority, keyBinding);
        this.name = name;
        this.formulaPrice = formulaPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormulaPrice() {
        return formulaPrice;
    }

    public void setFormulaPrice(String formulaPrice) {
        this.formulaPrice = formulaPrice;
    }
}
