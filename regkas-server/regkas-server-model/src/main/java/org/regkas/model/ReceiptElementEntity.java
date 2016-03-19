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
@Table(name = "receiptElement")
public class ReceiptElementEntity extends AbstractEntity {

    @Valid
    @NotNull
    @Expose
    private ProductGroupEntity productGroup;

    @Valid
    @Min(0)
    @Expose
    private BigDecimal amount;

    public ReceiptElementEntity() {
    }

    public ReceiptElementEntity(Long id, Integer version, ProductGroupEntity productGroup, BigDecimal amount) {
        super(id, version);
        this.productGroup = productGroup;
        this.amount = amount;
    }

    public ProductGroupEntity getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroupEntity productGroup) {
        this.productGroup = productGroup;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
