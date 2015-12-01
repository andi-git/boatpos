package org.boatpos.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "promotionType", discriminatorType = DiscriminatorType.STRING, length = 1)
public abstract class Promotion extends AbstractEntity {

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
     * The rentals where this promotion is used.
     */
    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Rental> rentals;

    public Promotion() {
    }

    public Promotion(Long id, Integer version, String name, String priceCalculation, Set<Rental> rentals) {
        super(id, version);
        this.name = name;
        this.priceCalculation = priceCalculation;
        this.rentals = rentals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Rental> getRentals() {
        return rentals;
    }

    public String getPriceCalculation() {
        return priceCalculation;
    }

    public void setPriceCalculation(String priceCalculation) {
        this.priceCalculation = priceCalculation;
    }

    public void setRentals(Set<Rental> rentals) {
        this.rentals = rentals;
    }
}
