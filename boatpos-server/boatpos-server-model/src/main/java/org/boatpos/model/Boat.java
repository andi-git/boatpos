package org.boatpos.model;

import com.google.gson.annotations.Expose;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Representation of a boat.
 */
@SuppressWarnings("unused")
@Entity
public class Boat extends AbstractMasterDataEntity {

    /**
     * The name of the {@link Boat}.
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
     * The price of the {@link Boat} for 1 hour.
     */
    @NotNull
    @Min(0)
    @Digits(integer = 5, fraction = 2)
    @Expose
    private BigDecimal price;

    /**
     * The price of the {@link Boat} for 1/2 hour.
     */
    @NotNull
    @Min(0)
    @Digits(integer = 5, fraction = 2)
    @Expose
    private BigDecimal priceHalfHour;

    /**
     * The price of the {@link Boat} for 3/4 hour.
     */
    @NotNull
    @Min(0)
    @Digits(integer = 5, fraction = 2)
    @Expose
    private BigDecimal priceThreeQuaterHour;

    /**
     * The number of available {@link Boat}s.
     */
    @NotNull
    @Min(0)
    @Max(100)
    @Expose
    private Integer count;

    /**
     * All {@link Rental}s where the {@link Boat} is used.
     */
    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Rental> rentals;

    public Boat() {
    }

    public Boat(Long id, Integer version, String name, String shortName, BigDecimal price, BigDecimal priceHalfHour, BigDecimal priceThreeQuaterHour, Integer count, Integer priority, Set<Rental> rentals, boolean enabled) {
        super(id, version, enabled, priority);
        this.name = name;
        this.shortName = shortName;
        this.price = price;
        this.priceHalfHour = priceHalfHour;
        this.priceThreeQuaterHour = priceThreeQuaterHour;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceHalfHour() {
        return priceHalfHour;
    }

    public void setPriceHalfHour(BigDecimal priceHalfOur) {
        this.priceHalfHour = priceHalfOur;
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

    public Set<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(Set<Rental> rentals) {
        this.rentals = rentals;
    }
}
