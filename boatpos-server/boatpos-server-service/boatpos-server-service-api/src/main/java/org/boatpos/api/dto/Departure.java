package org.boatpos.api.dto;

import com.google.common.base.Objects;
import com.google.gson.annotations.Expose;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * The departure of a boat.
 */
@SuppressWarnings("unused")
public class Departure extends AbstractDto {

    /**
     * The {@link Boat} of the rental.
     */
    @NotNull
    @Valid
    @Expose
    private Boat boat;

    /**
     * All {@link Commitment}s of the rental.
     */
    @Valid
    @Expose
    private Set<Commitment> commitments;

    /**
     * The {@link Promotion} of the rental.
     */
    @Valid
    @Expose
    private Promotion promotion;

    public Departure() {
    }

    public Departure(Boat boat, Set<Commitment> commitments, Promotion promotion) {
        this.boat = boat;
        this.commitments = commitments;
        this.promotion = promotion;
    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    public Set<Commitment> getCommitments() {
        return commitments;
    }

    public void setCommitments(Set<Commitment> commitments) {
        this.commitments = commitments;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departure departure = (Departure) o;
        return Objects.equal(boat, departure.boat) &&
                Objects.equal(commitments, departure.commitments) &&
                Objects.equal(promotion, departure.promotion);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(boat, commitments, promotion);
    }
}
