package org.boatpos.model;

import com.google.gson.annotations.Expose;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Representation of a boat-rental.
 */
@SuppressWarnings("unused")
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
    @ManyToOne(cascade = CascadeType.ALL)
    @Expose
    private Commitment commitment;

    public Rental() {

    }

    public Rental(Long id, Integer version, Integer dayId, LocalDate date, Boat boat, LocalDateTime departure, LocalDateTime arrival, BigDecimal price, boolean finished, boolean deleted, boolean coupon, Promotion promotion, Commitment commitment) {
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
        this.promotion = promotion;
        this.commitment = commitment;
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

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Commitment getCommitment() {
        return commitment;
    }

    public void setCommitment(Commitment commitment) {
        this.commitment = commitment;
    }
}
