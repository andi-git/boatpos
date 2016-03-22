package org.regkas.service.api.bean;

import com.google.gson.annotations.Expose;
import org.boatpos.common.service.api.bean.AbstractBean;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * An element of a sale.
 */
public class SaleElementBean extends AbstractBean {

    @NotNull
    @Valid
    @Expose
    private ProductGroupBean productGroup;

    @NotNull
    @Min(0)
    @Expose
    private BigDecimal amount;

    public SaleElementBean() {
    }

    public SaleElementBean(ProductGroupBean productGroup, BigDecimal amount) {
        this.productGroup = productGroup;
        this.amount = amount;
    }

    public ProductGroupBean getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroupBean productGroup) {
        this.productGroup = productGroup;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
