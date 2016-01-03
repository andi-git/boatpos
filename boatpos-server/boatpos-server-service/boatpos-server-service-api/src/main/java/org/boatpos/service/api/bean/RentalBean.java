package org.boatpos.service.api.bean;

import com.google.gson.annotations.Expose;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Representation of a boat-rental.
 */
@SuppressWarnings("unused")
public class RentalBean extends AbstractBeanBasedOnEntity {

    /**
     * The id of the day. Every day this id starts with 0.
     */
    @NotNull
    @Min(0)
    @Expose
    private Integer dayId;

    /**
     * The date of the {@link RentalBean}.
     */
    @NotNull
    @Expose
    private LocalDate date;

    /**
     * The {@link BoatBean} of the {@link RentalBean}.
     */
    @NotNull
    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    @Expose
    private BoatBean boatBean;

    /**
     * The time where the {@link RentalBean} starts.
     */
    @NotNull
    @Expose
    private LocalDateTime departure;

    /**
     * The time where the {@link RentalBean} ends.
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
     * The paid price before the rental.
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
     * Check if the {@link RentalBean} is finished.
     */
    @Expose
    private boolean finished;

    /**
     * Check if the {@link RentalBean} is deleted.
     */
    @Expose
    private boolean deleted;

    /**
     * Check if a coupon was used for the {@link RentalBean}.
     */
    @Expose
    private boolean coupon;

    /**
     * The {@link PromotionBean} used for the {@link RentalBean}.
     */
    @Valid
    @Expose
    private PromotionBean promotionBean;

    /**
     * The {@link CommitmentBean} used for the {@link RentalBean}.
     */
    @Valid
    private Set<CommitmentBean> commitmentBeans;

    public RentalBean() {
    }

    public RentalBean(Long id, Integer version, Integer dayId, LocalDate date, BoatBean boatBean, LocalDateTime departure, LocalDateTime arrival, BigDecimal priceCalculatedBefore, BigDecimal priceCalculatedAfter, BigDecimal pricePaidBefore, BigDecimal pricePaidAfter, boolean finished, boolean deleted, boolean coupon, PromotionBean promotionBean, Set<CommitmentBean> commitmentBeans) {
        super(id, version);
        this.dayId = dayId;
        this.date = date;
        this.boatBean = boatBean;
        this.departure = departure;
        this.arrival = arrival;
        this.priceCalculatedBefore = priceCalculatedBefore;
        this.priceCalculatedAfter = priceCalculatedAfter;
        this.pricePaidBefore = pricePaidBefore;
        this.pricePaidAfter = pricePaidAfter;
        this.finished = finished;
        this.deleted = deleted;
        this.coupon = coupon;
        this.promotionBean = promotionBean;
        this.commitmentBeans = commitmentBeans;
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

    public BoatBean getBoatBean() {
        return boatBean;
    }

    public void setBoatBean(BoatBean boatBean) {
        this.boatBean = boatBean;
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

    public void setPricePaidAfter(BigDecimal pricePaidAfter) {
        this.pricePaidAfter = pricePaidAfter;
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

    public void setPriceCalculatedAfter(BigDecimal priceCalculatedAfter) {
        this.priceCalculatedAfter = priceCalculatedAfter;
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

    public PromotionBean getPromotionBean() {
        return promotionBean;
    }

    public void setPromotionBean(PromotionBean promotionBean) {
        this.promotionBean = promotionBean;
    }

    public Set<CommitmentBean> getCommitmentBeans() {
        return commitmentBeans;
    }

    public void setCommitmentBeans(Set<CommitmentBean> commitmentBeans) {
        this.commitmentBeans = commitmentBeans;
    }
}
