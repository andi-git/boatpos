package org.regkas.model;

import com.google.gson.annotations.Expose;
import org.boatpos.common.model.AbstractEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Representation of an element of a receipt.
 */
@SuppressWarnings({"unused", "JpaDataSourceORMInspection"})
@Entity
@Table(name = "receiptelement")
public class ReceiptElementEntity extends AbstractEntity {

    @Valid
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @Expose
    private ProductGroupEntity productGroup;

    @Valid
    @Min(0)
    @Expose
    private BigDecimal amount;

    @NotNull
    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    @Expose
    private ReceiptEntity receipt;

    public ReceiptElementEntity() {
    }

    public ReceiptElementEntity(Long id, Integer version, ProductGroupEntity productGroup, ReceiptEntity receipt, BigDecimal amount) {
        super(id, version);
        this.productGroup = productGroup;
        this.receipt = receipt;
        this.amount = amount;
    }

    public ProductGroupEntity getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroupEntity productGroup) {
        this.productGroup = productGroup;
    }

    public ReceiptEntity getReceipt() {
        return receipt;
    }

    public void setReceipt(ReceiptEntity receipt) {
        this.receipt = receipt;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
