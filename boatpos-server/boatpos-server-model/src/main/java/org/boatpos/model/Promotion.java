package org.boatpos.model;

import com.google.gson.annotations.Expose;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@SuppressWarnings("unused")
@Entity
public class Promotion extends AbstractEntity {

    /**
     * The name for this promotion.
     */
    @NotNull
    @Size(min = 3)
    @Expose
    private String name;

    /**
     * The time-credit of this promotion in minutes.
     */
    @NotNull
    @Min(0)
    @Expose
    private Integer timeCredit;

    /**
     * A formula how to calculate the price.
     */
    @NotNull
    @Size(min = 3)
    @Expose
    private String priceCalculation;

    /**
     * The rentals where this promotion is used.
     */
    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Rental> rentals;

    public Promotion() {
    }

    public Promotion(Long id, Integer version, String name, Integer timeCredit, String priceCalculation, Set<Rental> rentals) {
        super(id, version);
        this.name = name;
        this.timeCredit = timeCredit;
        this.priceCalculation = priceCalculation;
        this.rentals = rentals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTimeCredit() {
        return timeCredit;
    }

    public void setTimeCredit(Integer timeCredit) {
        this.timeCredit = timeCredit;
    }

    public String getPriceCalculation() {
        return priceCalculation;
    }

    public void setPriceCalculation(String priceCalculation) {
        this.priceCalculation = priceCalculation;
    }

    public Set<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(Set<Rental> rentals) {
        this.rentals = rentals;
    }
}
