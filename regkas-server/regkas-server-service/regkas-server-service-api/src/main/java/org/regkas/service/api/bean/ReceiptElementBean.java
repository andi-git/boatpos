package org.regkas.service.api.bean;

import com.google.gson.annotations.Expose;
import org.boatpos.common.service.api.bean.AbstractBean;
import org.boatpos.common.service.api.bean.AbstractBeanBasedOnEntity;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * An element of a receipt.
 */
public class ReceiptElementBean extends AbstractBeanBasedOnEntity {

    @NotNull
    @Valid
    @Expose
    private ProductBean product;

    @NotNull
    @Min(0)
    @Expose
    private Integer amount;

    @NotNull
    @Min(0)
    @Expose
    private BigDecimal totalPrice;

    public ReceiptElementBean() {
    }

    public ReceiptElementBean(ProductBean product, Integer amount, BigDecimal totalPrice) {
        this.product = product;
        this.amount = amount;
        this.totalPrice = totalPrice;
    }

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
