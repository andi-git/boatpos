package org.boatpos.model;

import com.google.gson.annotations.Expose;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Representation of a commitment.
 */
@SuppressWarnings({"unused", "JpaDataSourceORMInspection"})
@Entity
@Table(name = "commitment")
public class CommitmentEntity extends AbstractMasterDataEntity implements ContainsRentals {

    /**
     * The name of the commitment.
     */
    @NotNull
    @Size(min = 3)
    @Expose
    private String name;

    /**
     * Check if a paper is needed when used for a {@link RentalEntity}.
     */
    @Expose
    private boolean paper;

    /**
     * All {@link RentalEntity}s where this {@link CommitmentEntity} is used.
     */
    @Valid
    @ManyToMany(mappedBy = "commitments", cascade = CascadeType.ALL)
    private Set<RentalEntity> rentals;

    public CommitmentEntity() {
    }

    public CommitmentEntity(Long id, Integer version, String name, boolean paper, Set<RentalEntity> rentals, Integer priority, boolean enabled) {
        super(id, version, enabled, priority);
        this.name = name;
        this.paper = paper;
        this.rentals = rentals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPaper() {
        return paper;
    }

    public void setPaper(boolean paper) {
        this.paper = paper;
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
