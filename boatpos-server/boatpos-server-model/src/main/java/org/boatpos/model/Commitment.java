package org.boatpos.model;

import com.google.gson.annotations.Expose;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Representation of a commitment.
 */
@SuppressWarnings("unused")
@Entity
public class Commitment extends AbstractEntity {

    /**
     * The name of the commitment.
     */
    @NotNull
    @Size(min = 3)
    @Expose
    private String name;

    /**
     * Check if a paper is needed when used for a {@link Rental}.
     */
    @Expose
    private boolean paper;

    /**
     * All {@link Rental}s where this {@link Commitment} is used.
     */
    @Valid
    @ManyToMany(mappedBy = "commitments", cascade = CascadeType.ALL)
    private Set<Rental> rentals;

    /**
     * The priority of the {@link Commitment}.
     */
    @NotNull
    @Min(0)
    private Integer priority;

    public Commitment() {
    }

    public Commitment(Long id, Integer version, String name, boolean paper, Set<Rental> rentals, Integer priority) {
        super(id, version);
        this.name = name;
        this.paper = paper;
        this.rentals = rentals;
        this.priority = priority;
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

    public Set<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(Set<Rental> rentals) {
        this.rentals = rentals;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
