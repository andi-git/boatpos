package org.boatpos.model;

import com.google.common.base.Objects;
import com.google.gson.GsonBuilder;

import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * Representation of a boat.
 */
@Entity
public class Boat extends AbstractEntity {

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @Size(min = 1, max = 3)
    private String shortName;

    @NotNull
    @Min(0)
    @Digits(integer = 5, fraction = 2)
    private BigDecimal price;

    @NotNull
    @Min(0)
    @Digits(integer = 5, fraction = 2)
    private BigDecimal priceHalfOur;

    @NotNull
    @Min(0)
    @Digits(integer = 5, fraction = 2)
    private BigDecimal priceThreeQuaterHour;

    @NotNull
    @Min(0)
    @Max(100)
    private Integer count;

    public Boat() {
    }

    public Boat(String name, String shortName, BigDecimal price, BigDecimal priceHalfOur, BigDecimal priceThreeQuaterHour, Integer count) {
        this.name = name;
        this.shortName = shortName;
        this.price = price;
        this.priceHalfOur = priceHalfOur;
        this.priceThreeQuaterHour = priceThreeQuaterHour;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boat boat = (Boat) o;
        return Objects.equal(name, boat.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this);
    }
}
