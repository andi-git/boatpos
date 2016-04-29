package org.regkas.service.api.bean;

import java.math.BigDecimal;

/**
 * A tax-element (price, price-before-tax, tax).
 */
public class TaxElementBean {

    private Integer taxPercent;

    private Integer priority;

    private BigDecimal price;

    private BigDecimal priceBeforeTax;

    private BigDecimal priceTax;

    public TaxElementBean() {
    }

    public TaxElementBean(Integer taxPercent, Integer priority, BigDecimal price, BigDecimal priceBeforeTax, BigDecimal priceTax) {
        this.taxPercent = taxPercent;
        this.priority = priority;
        this.price = price;
        this.priceBeforeTax = priceBeforeTax;
        this.priceTax = priceTax;
    }

    public Integer getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(Integer taxPercent) {
        this.taxPercent = taxPercent;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceBeforeTax() {
        return priceBeforeTax;
    }

    public void setPriceBeforeTax(BigDecimal priceBeforeTax) {
        this.priceBeforeTax = priceBeforeTax;
    }

    public BigDecimal getPriceTax() {
        return priceTax;
    }

    public void setPriceTax(BigDecimal priceTax) {
        this.priceTax = priceTax;
    }
}
