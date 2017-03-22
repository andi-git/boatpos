package org.regkas.domain.api.values;

import com.google.common.base.Objects;
import org.boatpos.common.domain.api.values.DomainId;

import java.math.BigDecimal;

/**
 * The result of the income-query
 */
public class ProductGroupIncomeResult {

    private final DomainId id;

    private final Name productGroupName;

    private final Price pricePaid;

    public ProductGroupIncomeResult(Long id, String productGroupName, BigDecimal pricePaid) {
        this.pricePaid = new Price(pricePaid);
        this.productGroupName = new Name(productGroupName);
        this.id = new DomainId(id);
    }

    public DomainId getId() {
        return id;
    }

    public Name getProductGroupName() {
        return productGroupName;
    }

    public Price getPricePaid() {
        return pricePaid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductGroupIncomeResult that = (ProductGroupIncomeResult) o;
        return Objects.equal(productGroupName, that.productGroupName) &&
                Objects.equal(pricePaid, that.pricePaid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productGroupName, pricePaid);
    }

    @Override
    public String toString() {
        return "ProductGroupIncomeResult{" +
                "productGroupName='" + productGroupName + '\'' +
                ", pricePaid=" + pricePaid +
                '}';
    }
}
