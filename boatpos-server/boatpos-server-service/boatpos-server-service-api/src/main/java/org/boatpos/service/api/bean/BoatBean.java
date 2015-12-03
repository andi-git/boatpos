package org.boatpos.service.api.bean;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Representation of a b boat.
 */
@SuppressWarnings("unused")
public class BoatBean extends AbstractDtoBasedOnEntity {

    /**
     * The name of the {@link BoatBean}.
     */
    @NotNull
    @Size(min = 3, max = 50)
    @Expose
    private String name;

    /**
     * The sort-name of the boat.
     */
    @NotNull
    @Size(min = 1, max = 3)
    @Expose
    private String shortName;

    /**
     * The price of the {@link BoatBean} for 1 hour.
     */
    @NotNull
    @Min(0)
    @Expose
    private BigDecimal price;

    /**
     * The price of the {@link BoatBean} for 1/2 hour.
     */
    @NotNull
    @Min(0)
    @Expose
    private BigDecimal priceHalfHour;

    /**
     * The price of the {@link BoatBean} for 3/4 hour.
     */
    @NotNull
    @Min(0)
    @Expose
    private BigDecimal priceThreeQuaterHour;

    /**
     * The number of available {@link BoatBean}s.
     */
    @NotNull
    @Min(0)
    @Max(100)
    @Expose
    private Integer count;

    /**
     * The priority of this {@link BoatBean}.
     */
    @NotNull
    @Min(0)
    private Integer priority;

    public BoatBean() { }

    public BoatBean(Long id, Integer version, String shortName, String name, BigDecimal price, BigDecimal priceHalfHour, BigDecimal priceThreeQuaterHour, Integer count, Integer priority) {
        super(id, version);
        this.shortName = shortName;
        this.name = name;
        this.price = price;
        this.priceHalfHour = priceHalfHour;
        this.priceThreeQuaterHour = priceThreeQuaterHour;
        this.count = count;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceHalfHour() {
        return priceHalfHour;
    }

    public void setPriceHalfHour(BigDecimal priceHalfHour) {
        this.priceHalfHour = priceHalfHour;
    }

    public BigDecimal getPriceThreeQuaterHour() {
        return priceThreeQuaterHour;
    }

    public void setPriceThreeQuaterHour(BigDecimal priceThreeQuaterHour) {
        this.priceThreeQuaterHour = priceThreeQuaterHour;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
