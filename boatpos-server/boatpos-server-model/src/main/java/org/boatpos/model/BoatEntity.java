package org.boatpos.model;

import com.google.gson.annotations.Expose;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Representation of a boat.
 */
@SuppressWarnings({"unused", "JpaDataSourceORMInspection"})
@Entity
@Table(name = "boat")
public class BoatEntity extends AbstractMasterDataEntity implements ContainsRentals {

    /**
     * The name of the {@link BoatEntity}.
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
     * The price of the {@link BoatEntity} for 1 hour.
     */
    @NotNull
    @Min(0)
    @Digits(integer = 5, fraction = 2)
    @Expose
    private BigDecimal priceOneHour;

    /**
     * The price of the {@link BoatEntity} for 1/2 hour.
     */
    @NotNull
    @Min(0)
    @Digits(integer = 5, fraction = 2)
    @Expose
    private BigDecimal priceThirtyMinutes;

    /**
     * The price of the {@link BoatEntity} for 3/4 hour.
     */
    @NotNull
    @Min(0)
    @Digits(integer = 5, fraction = 2)
    @Expose
    private BigDecimal priceFortyFiveMinutes;

    /**
     * The number of available {@link BoatEntity}s.
     */
    @NotNull
    @Min(0)
    @Max(100)
    @Expose
    private Integer count;

    /**
     * All {@link RentalEntity}s where the {@link BoatEntity} is used.
     */
    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RentalEntity> rentals;

    public BoatEntity() {
    }

    public BoatEntity(Long id, Integer version, String name, String shortName, BigDecimal priceOneHour, BigDecimal priceThirtyMinutes, BigDecimal priceFortyFiveMinutes, Integer count, Integer priority, Set<RentalEntity> rentals, boolean enabled) {
        super(id, version, enabled, priority);
        this.name = name;
        this.shortName = shortName;
        this.priceOneHour = priceOneHour;
        this.priceThirtyMinutes = priceThirtyMinutes;
        this.priceFortyFiveMinutes = priceFortyFiveMinutes;
        this.count = count;
        this.rentals = rentals;
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

    public void setPriceOneHour(BigDecimal price) {
        this.priceOneHour = price;
    }

    public BigDecimal getPriceThirtyMinutes() {
        return priceThirtyMinutes;
    }

    public void setPriceThirtyMinutes(BigDecimal priceHalfOur) {
        this.priceThirtyMinutes = priceHalfOur;
    }

    public BigDecimal getPriceFortyFiveMinutes() {
        return priceFortyFiveMinutes;
    }

    public void setPriceFortyFiveMinutes(BigDecimal priceThreeQuaterHour) {
        this.priceFortyFiveMinutes = priceThreeQuaterHour;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public Set<RentalEntity> getRentals() {
        return rentals;
    }

    @Override
    public void setRentals(Set<RentalEntity> rentals) {
        this.rentals = rentals;
    }
}
