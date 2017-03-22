package org.boatpos.domain.api.values;

import com.google.common.base.Objects;

import java.math.BigDecimal;

/**
 * The result of the income-query
 */
public class IncomeResult {

    private final String boatName;

    private final BigDecimal pricePaid;

    public IncomeResult(String boatName, BigDecimal pricePaid) {
        this.pricePaid = pricePaid;
        this.boatName = boatName;
    }

    public String getBoatName() {
        return boatName;
    }

    public BigDecimal getPricePaid() {
        return pricePaid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncomeResult that = (IncomeResult) o;
        return Objects.equal(boatName, that.boatName) &&
                Objects.equal(pricePaid, that.pricePaid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(boatName, pricePaid);
    }

    @Override
    public String toString() {
        return "IncomeResult{" +
                "boatName='" + boatName + '\'' +
                ", pricePaid=" + pricePaid +
                '}';
    }
}
