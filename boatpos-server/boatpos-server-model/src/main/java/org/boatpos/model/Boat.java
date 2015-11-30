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
public class Boat extends AbstractEntity {

    @NotNull
    @Size(min = 3, max = 50)
    @Expose
    private String name;

    @NotNull
    @Size(min = 1, max = 3)
    @Expose
    private String shortName;

    @NotNull
    @Min(0)
    @Digits(integer = 5, fraction = 2)
    @Expose
    private BigDecimal price;

    @NotNull
    @Min(0)
    @Digits(integer = 5, fraction = 2)
    @Expose
    private BigDecimal priceHalfOur;

    @NotNull
    @Min(0)
    @Digits(integer = 5, fraction = 2)
    @Expose
    private BigDecimal priceThreeQuaterHour;

    @NotNull
    @Min(0)
    @Max(100)
    @Expose
    private Integer count;

    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Rental> rentals;

    public Boat() {
    }

    public Boat(Long id, Integer version, String name, String shortName, BigDecimal price, BigDecimal priceHalfOur, BigDecimal priceThreeQuaterHour, Integer count, Set<Rental> rentals) {
        super(id, version);
        this.name = name;
        this.shortName = shortName;
        this.price = price;
        this.priceHalfOur = priceHalfOur;
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

    public BigDecimal getPriceHalfOur() {
        return priceHalfOur;
    }

    public void setPriceHalfOur(BigDecimal priceHalfOur) {
        this.priceHalfOur = priceHalfOur;
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
