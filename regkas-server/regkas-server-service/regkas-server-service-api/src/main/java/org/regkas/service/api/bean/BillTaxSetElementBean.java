package org.regkas.service.api.bean;

import com.google.gson.annotations.Expose;
import org.boatpos.common.service.api.bean.AbstractBean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class BillTaxSetElementBean extends AbstractBean {

    @NotNull
    @Size(min = 3, max = 50)
    @Expose
    private String name;

    @NotNull
    @Min(0)
    @Max(100)
    @Expose
    private Integer taxPercent;

    @NotNull
    @Min(0)
    @Expose
    private Integer amount;

    @NotNull
    @Expose
    private BigDecimal pricePreTax;

    @NotNull
    @Expose
    private BigDecimal priceAfterTax;

    @NotNull
    @Expose
    private BigDecimal priceTax;

    public BillTaxSetElementBean() {

    }

    public BillTaxSetElementBean(String name, Integer taxPercent, Integer amount, BigDecimal pricePreTax, BigDecimal priceAfterTax, BigDecimal priceTax) {
        this.name = name;
        this.taxPercent = taxPercent;
        this.amount = amount;
        this.pricePreTax = pricePreTax;
        this.priceAfterTax = priceAfterTax;
        this.priceTax = priceTax;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(Integer taxPercent) {
        this.taxPercent = taxPercent;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getPricePreTax() {
        return pricePreTax;
    }

    public void setPricePreTax(BigDecimal pricePreTax) {
        this.pricePreTax = pricePreTax;
    }

    public BigDecimal getPriceAfterTax() {
        return priceAfterTax;
    }

    public void setPriceAfterTax(BigDecimal priceAfterTax) {
        this.priceAfterTax = priceAfterTax;
    }

    public BigDecimal getPriceTax() {
        return priceTax;
    }

    public void setPriceTax(BigDecimal priceTax) {
        this.priceTax = priceTax;
    }
}
