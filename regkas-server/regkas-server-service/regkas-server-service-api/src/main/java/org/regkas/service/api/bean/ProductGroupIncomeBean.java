package org.regkas.service.api.bean;

import com.google.gson.annotations.Expose;
import org.boatpos.common.service.api.bean.AbstractBean;

import java.math.BigDecimal;

public class ProductGroupIncomeBean extends AbstractBean {

    @Expose
    private String name;

    @Expose
    private BigDecimal income;

    @Expose
    private Integer taxPercent;

    @Expose
    private Integer priority;

    public ProductGroupIncomeBean() {
    }

    public ProductGroupIncomeBean(String name, BigDecimal income, Integer taxPercent, Integer priority) {
        this.name = name;
        this.income = income;
        this.taxPercent = taxPercent;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
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
}
