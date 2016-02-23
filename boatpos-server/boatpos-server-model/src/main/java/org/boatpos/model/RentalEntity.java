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
    private LocalDate day;

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
     * The paid price after the rental.
     */
    @Min(0)
    @Expose
    private BigDecimal pricePaidAfter;

    /**
     * The paid price before the rental (e.g. for promotions).
     */
    @Min(0)
    @Expose
    private BigDecimal pricePaidBefore;

    /**
     * The calculated price after the rental.
     */
    @Min(0)
    @Expose
    private BigDecimal priceCalculatedAfter;

    /**
     * The calculated price before the rental.
     */
    @Min(0)
    @Expose
    private BigDecimal priceCalculatedBefore;

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
     * The method of the payment before.
     */
    @Expose
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethodBefore;

    /**
     * The method of the payment after.
     */
    @Expose
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethodAfter;

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

    public RentalEntity(Long id, Integer version, Integer dayId, LocalDate day, BoatEntity boat, LocalDateTime departure, LocalDateTime arrival, BigDecimal priceCalculatedBefore, BigDecimal priceCalculatedAfter, BigDecimal pricePaidBefore, BigDecimal pricePaidAfter, Boolean finished, Boolean deleted, Boolean coupon, PaymentMethod paymentMethodBefore, PaymentMethod paymentMethodAfter, PromotionEntity promotion, Set<CommitmentEntity> commitments) {
        super(id, version);
        this.dayId = dayId;
        this.day = day;
        this.boat = boat;
        this.departure = departure;
        this.arrival = arrival;
        this.priceCalculatedBefore = priceCalculatedBefore;
        this.priceCalculatedAfter = priceCalculatedAfter;
        this.pricePaidBefore = pricePaidBefore;
        this.pricePaidAfter = pricePaidAfter;
        this.finished = finished;
        this.deleted = deleted;
        this.coupon = coupon;
        this.paymentMethodBefore = paymentMethodBefore;
        this.paymentMethodAfter = paymentMethodAfter;
        this.promotion = promotion;
        this.commitments = commitments;
    }

    public Integer getDayId() {
        return dayId;
    }

    public void setDayId(Integer dayId) {
        this.dayId = dayId;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
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

    public BigDecimal getPricePaidBefore() {
        return pricePaidBefore;
    }

    public void setPricePaidBefore(BigDecimal pricePaidBefore) {
        this.pricePaidBefore = pricePaidBefore;
    }

    public BigDecimal getPricePaidAfter() {
        return pricePaidAfter;
    }

    public void setPricePaidAfter(BigDecimal pricePayed) {
        this.pricePaidAfter = pricePayed;
    }

    public BigDecimal getPriceCalculatedBefore() {
        return priceCalculatedBefore;
    }

    public void setPriceCalculatedBefore(BigDecimal priceCalculatedBefore) {
        this.priceCalculatedBefore = priceCalculatedBefore;
    }

    public BigDecimal getPriceCalculatedAfter() {
        return priceCalculatedAfter;
    }

    public void setPriceCalculatedAfter(BigDecimal priceCalculated) {
        this.priceCalculatedAfter = priceCalculated;
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

    public PaymentMethod getPaymentMethodBefore() {
        return paymentMethodBefore;
    }

    public void setPaymentMethodBefore(PaymentMethod paymentMethodBefore) {
        this.paymentMethodBefore = paymentMethodBefore;
    }

    public PaymentMethod getPaymentMethodAfter() {
        return paymentMethodAfter;
    }

    public void setPaymentMethodAfter(PaymentMethod paymentMethodAfter) {
        this.paymentMethodAfter = paymentMethodAfter;
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
            return new RentalEntity(null, null, dayId, LocalDate.from(departTime), boat, departTime, null, null, null, null, null, false, false, false, null, null, promotion, commitments);
        }

    }
}
