package org.boatpos.service.api.bean;

import com.google.common.base.Objects;

import java.math.BigDecimal;

/**
 * An item of the {@link JournalReportBean}.
 */
public class JournalReportItemBean extends AbstractBean {

    private String boatName;

    private BigDecimal pricePaidBeforeCash;

    private BigDecimal pricePaidBeforeCard;

    private BigDecimal pricePaidAfterCash;

    private BigDecimal pricePaidAfterCard;

    private Integer count;

    public JournalReportItemBean() {}

    public JournalReportItemBean(String boatName, BigDecimal pricePaidBeforeCash, BigDecimal pricePaidBeforeCard, BigDecimal pricePaidAfterCash, BigDecimal pricePaidAfterCard, Integer count) {
        this.boatName = boatName;
        this.pricePaidBeforeCash = pricePaidBeforeCash;
        this.pricePaidBeforeCard = pricePaidBeforeCard;
        this.pricePaidAfterCash = pricePaidAfterCash;
        this.pricePaidAfterCard = pricePaidAfterCard;
        this.count = count;
    }

    public String getBoatName() {
        return boatName;
    }

    public void setBoatName(String boatName) {
        this.boatName = boatName;
    }

    public BigDecimal getPricePaidBeforeCash() {
        return pricePaidBeforeCash;
    }

    public void setPricePaidBeforeCash(BigDecimal pricePaidBeforeCash) {
        this.pricePaidBeforeCash = pricePaidBeforeCash;
    }

    public BigDecimal getPricePaidBeforeCard() {
        return pricePaidBeforeCard;
    }

    public void setPricePaidBeforeCard(BigDecimal pricePaidBeforeCard) {
        this.pricePaidBeforeCard = pricePaidBeforeCard;
    }

    public BigDecimal getPricePaidAfterCash() {
        return pricePaidAfterCash;
    }

    public void setPricePaidAfterCash(BigDecimal pricePaidAfterCash) {
        this.pricePaidAfterCash = pricePaidAfterCash;
    }

    public BigDecimal getPricePaidAfterCard() {
        return pricePaidAfterCard;
    }

    public void setPricePaidAfterCard(BigDecimal pricePaidAfterCard) {
        this.pricePaidAfterCard = pricePaidAfterCard;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JournalReportItemBean that = (JournalReportItemBean) o;
        return Objects.equal(boatName, that.boatName) &&
                Objects.equal(pricePaidBeforeCash, that.pricePaidBeforeCash) &&
                Objects.equal(pricePaidBeforeCard, that.pricePaidBeforeCard) &&
                Objects.equal(pricePaidAfterCash, that.pricePaidAfterCash) &&
                Objects.equal(pricePaidAfterCard, that.pricePaidAfterCard) &&
                Objects.equal(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(boatName, pricePaidBeforeCash, pricePaidBeforeCard, pricePaidAfterCash, pricePaidAfterCard, count);
    }

    @Override
    public String toString() {
        return "JournalReportItemBean{" +
                "boatName='" + boatName + '\'' +
                ", pricePaidBeforeCash=" + pricePaidBeforeCash +
                ", pricePaidBeforeCard=" + pricePaidBeforeCard +
                ", pricePaidAfterCash=" + pricePaidAfterCash +
                ", pricePaidAfterCard=" + pricePaidAfterCard +
                ", count=" + count +
                '}';
    }
}
