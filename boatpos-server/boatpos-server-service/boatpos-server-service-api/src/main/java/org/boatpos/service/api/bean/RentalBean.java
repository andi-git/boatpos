package org.boatpos.service.api.bean;

import com.google.gson.annotations.Expose;
import org.boatpos.common.service.api.bean.AbstractBeanBasedOnEntity;
import org.boatpos.common.service.api.bean.LocalDateAdapter;
import org.boatpos.common.service.api.bean.LocalDateTimeAdapter;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate day;

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
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime departure;

    /**
     * The time where the {@link RentalBean} ends.
     */
    @Expose
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime arrival;

    @Expose
    private Integer timeOfTravel;

    @Expose
    private Integer timeOfTravelCalculated;

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
     * The {@link PromotionBeforeBean} used for the {@link RentalBean}.
     */
    @Valid
    @Expose
    private PromotionBeforeBean promotionBeforeBean;

    /**
     * The {@link PromotionAfterBean} used for the {@link RentalBean}.
     */
    @Valid
    @Expose
    private PromotionAfterBean promotionAfterBean;

    /**
     * The {@link CommitmentBean} used for the {@link RentalBean}.
     */
    @Valid
    @Expose
    private Set<CommitmentBean> commitmentBeans;

    @NotNull
    @Expose
    private String paymentMethodBefore;

    @NotNull
    @Expose
    private String paymentMethodAfter;

    @Expose
    private String receiptId;

    @Expose
    private String myRentalId;

    public RentalBean() {
    }

    public RentalBean(Long id, Integer version, Integer dayId, LocalDate day, BoatBean boatBean, LocalDateTime departure, LocalDateTime arrival, BigDecimal priceCalculatedBefore, BigDecimal priceCalculatedAfter, BigDecimal pricePaidBefore, BigDecimal pricePaidAfter, boolean finished, boolean deleted, boolean coupon, PromotionBeforeBean promotionBeforeBean, PromotionAfterBean promotionAfterBean, Set<CommitmentBean> commitmentBeans, Integer timeOfTravel, Integer timeOfTravelCalculated, String paymentMethodBefore, String paymentMethodAfter, String receiptId, String myRentalId) {
        super(id, version);
        this.dayId = dayId;
        this.day = day;
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
        this.promotionBeforeBean = promotionBeforeBean;
        this.promotionAfterBean = promotionAfterBean;
        this.commitmentBeans = commitmentBeans;
        this.timeOfTravel = timeOfTravel;
        this.timeOfTravelCalculated = timeOfTravelCalculated;
        this.paymentMethodBefore = paymentMethodBefore;
        this.paymentMethodAfter = paymentMethodAfter;
        this.receiptId = receiptId;
        this.myRentalId = myRentalId;
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

    public PromotionBeforeBean getPromotionBeforeBean() {
        return promotionBeforeBean;
    }

    public void setPromotionBeforeBean(PromotionBeforeBean promotionBeforeBean) {
        this.promotionBeforeBean = promotionBeforeBean;
    }

    public PromotionAfterBean getPromotionAfterBean() {
        return promotionAfterBean;
    }

    public void setPromotionAfterBean(PromotionAfterBean promotionAfterBean) {
        this.promotionAfterBean = promotionAfterBean;
    }

    public Set<CommitmentBean> getCommitmentBeans() {
        return commitmentBeans;
    }

    public void setCommitmentBeans(Set<CommitmentBean> commitmentBeans) {
        this.commitmentBeans = commitmentBeans;
    }

    public Integer getTimeOfTravel() {
        return timeOfTravel;
    }

    public void setTimeOfTravel(Integer timeOfTravel) {
        this.timeOfTravel = timeOfTravel;
    }

    public Integer getTimeOfTravelCalculated() {
        return timeOfTravelCalculated;
    }

    public void setTimeOfTravelCalculated(Integer timeOfTravelCalculated) {
        this.timeOfTravelCalculated = timeOfTravelCalculated;
    }

    public String getPaymentMethodBefore() {
        return paymentMethodBefore;
    }

    public void setPaymentMethodBefore(String paymentMethodBefore) {
        this.paymentMethodBefore = paymentMethodBefore;
    }

    public String getPaymentMethodAfter() {
        return paymentMethodAfter;
    }

    public void setPaymentMethodAfter(String paymentMethodAfter) {
        this.paymentMethodAfter = paymentMethodAfter;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public String getMyRentalId() {
        return myRentalId;
    }

    public void setMyRentalId(String myRentalId) {
        this.myRentalId = myRentalId;
    }
}
