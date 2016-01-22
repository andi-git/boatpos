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
@Table(name = "promotion")
public abstract class PromotionEntity extends AbstractMasterDataEntity implements ContainsRentals {

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

    /**
     * The rentals where this promotion is used.
     */
    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RentalEntity> rentals;

    public PromotionEntity() {
    }

    public PromotionEntity(Long id, Integer version, String name, String formulaPrice, Set<RentalEntity> rentals, Integer priority, boolean enabled, Character keyBinding) {
        super(id, version, enabled, priority, keyBinding);
        this.name = name;
        this.formulaPrice = formulaPrice;
        this.rentals = rentals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Set<RentalEntity> getRentals() {
        return rentals;
    }

    public String getFormulaPrice() {
        return formulaPrice;
    }

    public void setFormulaPrice(String priceCalculation) {
        this.formulaPrice = priceCalculation;
    }

    @Override
    public void setRentals(Set<RentalEntity> rentals) {
        this.rentals = rentals;
    }
}
