package org.boatpos.api.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("unused")
public abstract class Promotion extends AbstractDtoBasedOnEntity {

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

    /**
     * The priority of this {@link Promotion}.
     */
    @NotNull
    @Min(0)
    private Integer priority;

    public Promotion() {
    }

    public Promotion(Long id, Integer version, String name, String priceCalculation, Integer priority) {
        super(id, version);
        this.name = name;
        this.priceCalculation = priceCalculation;
        this.priority = priority;
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
