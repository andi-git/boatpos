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
 * Representation of an element of a payment.
 */
@SuppressWarnings({"unused", "JpaDataSourceORMInspection"})
@Entity
@Table(name = "company")
public class PaymentElementEntity extends AbstractEntity {

    @Valid
    @NotNull
    @Expose
    private TaxSetEntity taxSet;

    @Valid
    @Min(0)
    @Expose
    private BigDecimal amount;

    public PaymentElementEntity() {
    }

    public PaymentElementEntity(Long id, Integer version, TaxSetEntity taxSet, BigDecimal amount) {
        super(id, version);
        this.taxSet = taxSet;
        this.amount = amount;
    }

    public TaxSetEntity getTaxSet() {
        return taxSet;
    }

    public void setTaxSet(TaxSetEntity taxSet) {
        this.taxSet = taxSet;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
