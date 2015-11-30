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

@SuppressWarnings("unused")
@Entity
public class Rental extends AbstractEntity {

    @NotNull
    @Min(0)
    @Expose
    private Integer dayId;

    @NotNull
    @Expose
    private LocalDate date;

    @NotNull
    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    @Expose
    private Boat boat;

    @NotNull
    @Expose
    private LocalDateTime departure;

    @Expose
    private LocalDateTime arrival;

    @Min(0)
    @Expose
    private BigDecimal price;

    @Expose
    private boolean finished;

    @Expose
    private boolean deleted;

    @Expose
    private boolean coupon;

    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    @Expose
    private Promotion promotion;

    @Expose
    private BigDecimal pricePromotion;

    @Expose
    private boolean holliknolli;

    public Rental() {

    }

    public Rental(Long id, Integer version, Integer dayId, LocalDate date, Boat boat, LocalDateTime departure, LocalDateTime arrival, BigDecimal price, boolean finished, boolean deleted, boolean coupon, Promotion promotion, BigDecimal pricePromotion, boolean holliknolli) {
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
        this.pricePromotion = pricePromotion;
        this.holliknolli = holliknolli;
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

    public BigDecimal getPricePromotion() {
        return pricePromotion;
    }

    public void setPricePromotion(BigDecimal pricePromotion) {
        this.pricePromotion = pricePromotion;
    }

    public boolean isHolliknolli() {
        return holliknolli;
    }

    public void setHolliknolli(boolean holliknolli) {
        this.holliknolli = holliknolli;
    }
}
