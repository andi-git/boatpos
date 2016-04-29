package org.boatpos.service.api.bean;

import com.google.common.base.Objects;
import com.google.gson.annotations.Expose;
import org.boatpos.common.service.api.bean.AbstractBean;

import java.math.BigDecimal;

/**
 * An item of the {@link JournalReportBean}.
 */
public class JournalReportItemBean extends AbstractBean {

    @Expose
    private String boatName;

    @Expose
    private BigDecimal pricePaidBeforeCash;

    @Expose
    private BigDecimal pricePaidBeforeCashBeforeTax;

    @Expose
    private BigDecimal pricePaidBeforeCashTax;

    @Expose
    private BigDecimal pricePaidBeforeCard;

    @Expose
    private BigDecimal pricePaidBeforeCardBeforeTax;

    @Expose
    private BigDecimal pricePaidBeforeCardTax;

    @Expose
    private BigDecimal pricePaidAfterCash;

    @Expose
    private BigDecimal pricePaidAfterCashBeforeTax;

    @Expose
    private BigDecimal pricePaidAfterCashTax;

    @Expose
    private BigDecimal pricePaidAfterCard;

    @Expose
    private BigDecimal pricePaidAfterCardBeforeTax;

    @Expose
    private BigDecimal pricePaidAfterCardTax;

    @Expose
    private Integer count;

    public JournalReportItemBean() {
    }

    public JournalReportItemBean(String boatName,
                                 BigDecimal pricePaidBeforeCash, BigDecimal pricePaidBeforeCashBeforeTax, BigDecimal pricePaidBeforeCashTax,
                                 BigDecimal pricePaidBeforeCard, BigDecimal pricePaidBeforeCardBeforeTax, BigDecimal pricePaidBeforeCardTax,
                                 BigDecimal pricePaidAfterCash, BigDecimal pricePaidAfterCashBeforeTax, BigDecimal pricePaidAfterCashTax,
                                 BigDecimal pricePaidAfterCard, BigDecimal pricePaidAfterCardBeforeTax, BigDecimal pricePaidAfterCardTax,
                                 Integer count) {
        this.boatName = boatName;
        this.pricePaidBeforeCash = pricePaidBeforeCash;
        this.pricePaidBeforeCashBeforeTax = pricePaidBeforeCashBeforeTax;
        this.pricePaidBeforeCashTax = pricePaidBeforeCashTax;
        this.pricePaidBeforeCard = pricePaidBeforeCard;
        this.pricePaidBeforeCardBeforeTax = pricePaidBeforeCardBeforeTax;
        this.pricePaidBeforeCardTax = pricePaidBeforeCardTax;
        this.pricePaidAfterCash = pricePaidAfterCash;
        this.pricePaidAfterCashBeforeTax = pricePaidAfterCashBeforeTax;
        this.pricePaidAfterCashTax = pricePaidAfterCashTax;
        this.pricePaidAfterCard = pricePaidAfterCard;
        this.pricePaidAfterCardBeforeTax = pricePaidAfterCardBeforeTax;
        this.pricePaidAfterCardTax = pricePaidAfterCardTax;
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

    public BigDecimal getPricePaidBeforeCashBeforeTax() {
        return pricePaidBeforeCashBeforeTax;
    }

    public void setPricePaidBeforeCashBeforeTax(BigDecimal pricePaidBeforeCashBeforeTax) {
        this.pricePaidBeforeCashBeforeTax = pricePaidBeforeCashBeforeTax;
    }

    public BigDecimal getPricePaidBeforeCashTax() {
        return pricePaidBeforeCashTax;
    }

    public void setPricePaidBeforeCashTax(BigDecimal pricePaidBeforeCashTax) {
        this.pricePaidBeforeCashTax = pricePaidBeforeCashTax;
    }

    public BigDecimal getPricePaidBeforeCardBeforeTax() {
        return pricePaidBeforeCardBeforeTax;
    }

    public void setPricePaidBeforeCardBeforeTax(BigDecimal pricePaidBeforeCardBeforeTax) {
        this.pricePaidBeforeCardBeforeTax = pricePaidBeforeCardBeforeTax;
    }

    public BigDecimal getPricePaidBeforeCardTax() {
        return pricePaidBeforeCardTax;
    }

    public void setPricePaidBeforeCardTax(BigDecimal pricePaidBeforeCardTax) {
        this.pricePaidBeforeCardTax = pricePaidBeforeCardTax;
    }

    public BigDecimal getPricePaidAfterCashBeforeTax() {
        return pricePaidAfterCashBeforeTax;
    }

    public void setPricePaidAfterCashBeforeTax(BigDecimal pricePaidAfterCashBeforeTax) {
        this.pricePaidAfterCashBeforeTax = pricePaidAfterCashBeforeTax;
    }

    public BigDecimal getPricePaidAfterCashTax() {
        return pricePaidAfterCashTax;
    }

    public void setPricePaidAfterCashTax(BigDecimal pricePaidAfterCashTax) {
        this.pricePaidAfterCashTax = pricePaidAfterCashTax;
    }

    public BigDecimal getPricePaidAfterCardBeforeTax() {
        return pricePaidAfterCardBeforeTax;
    }

    public void setPricePaidAfterCardBeforeTax(BigDecimal pricePaidAfterCardBeforeTax) {
        this.pricePaidAfterCardBeforeTax = pricePaidAfterCardBeforeTax;
    }

    public BigDecimal getPricePaidAfterCardTax() {
        return pricePaidAfterCardTax;
    }

    public void setPricePaidAfterCardTax(BigDecimal pricePaidAfterCardTax) {
        this.pricePaidAfterCardTax = pricePaidAfterCardTax;
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
}
