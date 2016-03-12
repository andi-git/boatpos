package org.boatpos.service.api.bean;

import com.google.gson.annotations.Expose;
import org.boatpos.common.service.api.bean.AbstractMasterDataBean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Representation of a b boat.
 */
@SuppressWarnings("unused")
public class BoatBean extends AbstractMasterDataBean {

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
     * The priceOneHour of the {@link BoatBean} for 1 hour.
     */
    @NotNull
    @Min(0)
    @Expose
    private BigDecimal priceOneHour;

    /**
     * The priceOneHour of the {@link BoatBean} for 1/2 hour.
     */
    @NotNull
    @Min(0)
    @Expose
    private BigDecimal priceThirtyMinutes;

    /**
     * The priceOneHour of the {@link BoatBean} for 3/4 hour.
     */
    @NotNull
    @Min(0)
    @Expose
    private BigDecimal priceFortyFiveMinutes;

    /**
     * The number of available {@link BoatBean}s.
     */
    @NotNull
    @Min(0)
    @Max(100)
    @Expose
    private Integer count;

    public BoatBean() {
    }

    public BoatBean(Long id, Integer version, String shortName, String name, BigDecimal priceOneHour, BigDecimal priceThirtyMinutes, BigDecimal priceFortyFiveMinutes, Integer count, Integer priority, boolean enabled, Character keyBinding, String pictureUrl, String pictureUrlThumb) {
        super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
        this.shortName = shortName;
        this.name = name;
        this.priceOneHour = priceOneHour;
        this.priceThirtyMinutes = priceThirtyMinutes;
        this.priceFortyFiveMinutes = priceFortyFiveMinutes;
        this.count = count;
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

    public BigDecimal getPriceOneHour() {
        return priceOneHour;
    }

    public void setPriceOneHour(BigDecimal priceOneHour) {
        this.priceOneHour = priceOneHour;
    }

    public BigDecimal getPriceThirtyMinutes() {
        return priceThirtyMinutes;
    }

    public void setPriceThirtyMinutes(BigDecimal priceThirtyMinutes) {
        this.priceThirtyMinutes = priceThirtyMinutes;
    }

    public BigDecimal getPriceFortyFiveMinutes() {
        return priceFortyFiveMinutes;
    }

    public void setPriceFortyFiveMinutes(BigDecimal priceFortyFiveMinutes) {
        this.priceFortyFiveMinutes = priceFortyFiveMinutes;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
