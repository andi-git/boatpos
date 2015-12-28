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
@Table(name = "rental")
public class RentalEntity extends AbstractEntity {

    /**
     * The id of the day. Every day this id starts with 0.
     */
    @NotNull
    @Min(0)
    @Expose
    private Integer dayId;

    /**
     * The date of the {@link RentalEntity}.
     */
    @NotNull
    @Expose
    private LocalDate date;

    /**
     * The {@link BoatEntity} of the {@link RentalEntity}.
     */
    @NotNull
    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    @Expose
    private BoatEntity boat;

    /**
     * The time where the {@link RentalEntity} starts.
     */
    @NotNull
    @Expose
    private LocalDateTime departure;

    /**
     * The time where the {@link RentalEntity} ends.
     */
    @Expose
    private LocalDateTime arrival;

    /**
     * The paid price of the {@link RentalEntity}.
     */
    @Min(0)
    @Expose
    private BigDecimal pricePaid;

    /**
     * The calculated price of the {@link RentalEntity}.
     */
    @Min(0)
    @Expose
    private BigDecimal priceCalculated;

    /**
     * Check if the {@link RentalEntity} is finished.
     */
    @Expose
    private Boolean finished;

    /**
     * Check if the {@link RentalEntity} is deleted.
     */
    @Expose
    private Boolean deleted;

    /**
     * Check if a coupon was used for the {@link RentalEntity}.
     */
    @Expose
    private Boolean coupon;

    /**
     * The method of the payment.
     */
    @Expose
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    /**
     * The {@link PromotionEntity} used for the {@link RentalEntity}.
     */
    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    @Expose
    private PromotionEntity promotion;

    /**
     * The {@link CommitmentEntity} used for the {@link RentalEntity}.
     */
    @Valid
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Rental_Commitment", joinColumns = {
            @JoinColumn(name = "rental_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "commitment_id")
    })
    private Set<CommitmentEntity> commitments;

    public RentalEntity() {
    }

    public RentalEntity(Long id, Integer version, Integer dayId, LocalDate date, BoatEntity boat, LocalDateTime departure, LocalDateTime arrival, BigDecimal priceCalculated, BigDecimal pricePaid, Boolean finished, Boolean deleted, Boolean coupon, PaymentMethod paymentMethod, PromotionEntity promotion, Set<CommitmentEntity> commitments) {
        super(id, version);
        this.dayId = dayId;
        this.date = date;
        this.boat = boat;
        this.departure = departure;
        this.arrival = arrival;
        this.priceCalculated = priceCalculated;
        this.pricePaid = pricePaid;
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

    public BoatEntity getBoat() {
        return boat;
    }

    public void setBoat(BoatEntity boat) {
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

    public BigDecimal getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(BigDecimal pricePayed) {
        this.pricePaid = pricePayed;
    }

    public BigDecimal getPriceCalculated() {
        return priceCalculated;
    }

    public void setPriceCalculated(BigDecimal priceCalculated) {
        this.priceCalculated = priceCalculated;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getCoupon() {
        return coupon;
    }

    public void setCoupon(Boolean coupon) {
        this.coupon = coupon;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PromotionEntity getPromotion() {
        return promotion;
    }

    public void setPromotion(PromotionEntity promotion) {
        this.promotion = promotion;
    }

    public Set<CommitmentEntity> getCommitments() {
        return commitments;
    }

    public void setCommitments(Set<CommitmentEntity> commitments) {
        this.commitments = commitments;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private BoatEntity boat = null;
        private PromotionEntity promotion = null;
        private Set<CommitmentEntity> commitments = new HashSet<>();
        private LocalDateTime departTime;
        private Integer dayId;

        private Builder() {
        }

        public Builder setBoat(BoatEntity boat) {
            checkNotNull(boat, "boat must not be null");
            this.boat = boat;
            return this;
        }

        public Builder setPromotion(Optional<PromotionEntity> promotion) {
            checkNotNull(promotion, "promotion must not be null");
            if (promotion.isPresent()) {
                this.promotion = promotion.get();
            }
            return this;
        }

        public Builder setCommitments(Set<CommitmentEntity> commitments) {
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

        public RentalEntity build() {
            checkNotNull(boat, "boat must not be null");
            checkNotNull(dayId, "dayId must not be null");
            checkNotNull(departTime, "departTime must not be null");
            return new RentalEntity(null, null, dayId, LocalDate.from(departTime), boat, departTime, null, null, null, false, false, false, null, promotion, commitments);
        }

    }
}