package org.boatpos.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Representation of a boat-rental.
 */
@SuppressWarnings({"unused", "JpaDataSourceORMInspection"})
@Entity
public class Rental extends AbstractEntity {

    /**
     * The id of the day. Every day this id starts with 0.
     */
    @NotNull
    @Min(0)
    @Expose
    private Integer dayId;

    /**
     * The date of the {@link Rental}.
     */
    @NotNull
    @Expose
    private LocalDate date;

    /**
     * The {@link Boat} of the {@link Rental}.
     */
    @NotNull
    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    @Expose
    private Boat boat;

    /**
     * The time where the {@link Rental} starts.
     */
    @NotNull
    @Expose
    private LocalDateTime departure;

    /**
     * The time where the {@link Rental} ends.
     */
    @Expose
    private LocalDateTime arrival;

    /**
     * The paid price of the {@link Rental}.
     */
    @Min(0)
    @Expose
    private BigDecimal price;

    /**
     * Check if the {@link Rental} is finished.
     */
    @Expose
    private boolean finished;

    /**
     * Check if the {@link Rental} is deleted.
     */
    @Expose
    private boolean deleted;

    /**
     * Check if a coupon was used for the {@link Rental}.
     */
    @Expose
    private boolean coupon;

    @Expose
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    /**
     * The {@link Promotion} used for the {@link Rental}.
     */
    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    @Expose
    private Promotion promotion;

    /**
     * The {@link Commitment} used for the {@link Rental}.
     */
    @Valid
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Rental_Commitment", joinColumns = {
            @JoinColumn(name = "rental_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "commitment_id")
    })
    private Set<Commitment> commitments;

    public Rental() {
    }

    public Rental(Long id, Integer version, Integer dayId, LocalDate date, Boat boat, LocalDateTime departure, LocalDateTime arrival, BigDecimal price, boolean finished, boolean deleted, boolean coupon, PaymentMethod paymentMethod, Promotion promotion, Set<Commitment> commitments) {
        super(id, version);
        this.dayId = dayId;
        this.date = date;
        this.boat = boat;
        this.departure = departure;
        this.arrival = arrival;
        this.price = price;
        this.finished = finished;
        this.deleted = deleted;
        this.coupon = coupon;
        this.paymentMethod = paymentMethod;
        this.promotion = promotion;
        this.commitments = commitments;
    }

    public Integer getDayId() {
        return dayId;
    }

    public void setDayId(Integer dayId) {
        this.dayId = dayId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isCoupon() {
        return coupon;
    }

    public void setCoupon(boolean coupon) {
        this.coupon = coupon;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Set<Commitment> getCommitments() {
        return commitments;
    }

    public void setCommitments(Set<Commitment> commitments) {
        this.commitments = commitments;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Boat boat = null;
        private Promotion promotion = null;
        private Set<Commitment> commitments = new HashSet<>();
        private LocalDateTime departTime;
        private Integer dayId;

        private Builder() {
        }

        public Builder setBoat(Boat boat) {
            checkNotNull(boat, "boat must not be null");
            this.boat = boat;
            return this;
        }

        public Builder setPromotion(Optional<Promotion> promotion) {
            checkNotNull(promotion, "promotion must not be null");
            if (promotion.isPresent()) {
                this.promotion = promotion.get();
            }
            return this;
        }

        public Builder setCommitments(Set<Commitment> commitments) {
            checkNotNull(commitments, "commitments must not be null");
            this.commitments.clear();
            this.commitments.addAll(commitments);
            return this;
        }

        public Builder setDayId(Integer dayId) {
            checkNotNull(dayId, "dayId must not be null");
            this.dayId = dayId;
            return this;
        }

        public Builder setDepartTime(LocalDateTime departTime) {
            checkNotNull(departTime, "departTime must not be null");
            this.departTime = departTime;
            return this;
        }

        public Rental build() {
            checkNotNull(boat, "boat must not be null");
            checkNotNull(dayId, "dayId must not be null");
            checkNotNull(departTime, "departTime must not be null");
            return new Rental(null, null, dayId, LocalDate.from(departTime), boat, departTime, null, null, false, false, false, null, promotion, commitments);
        }

    }
}
