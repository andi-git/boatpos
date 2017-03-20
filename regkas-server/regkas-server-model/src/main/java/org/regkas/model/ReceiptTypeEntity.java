package org.regkas.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.boatpos.common.model.AbstractMasterDataEntity;

import com.google.gson.annotations.Expose;

@SuppressWarnings({"unused", "JpaDataSourceORMInspection"})
@Entity
@Table(name = "receiptType")
@DiscriminatorColumn(name = "name", discriminatorType = DiscriminatorType.STRING)
public abstract class ReceiptTypeEntity extends AbstractMasterDataEntity {

    @NotNull
    @Size(min = 3, max = 50)
    @Expose
    @Column(updatable = false, insertable = false)
    private String name;

    @NotNull
    @Expose
    private Boolean signatureMandatory;

    public ReceiptTypeEntity() {}

    public ReceiptTypeEntity(
            Long id,
            Integer version,
            Boolean enabled,
            Integer priority,
            String pictureUrl,
            String pictureUrlThumb,
            String name,
            Boolean signatureMandatory) {
        super(id, version, enabled, priority, '#', pictureUrl, pictureUrlThumb);
        this.name = name;
        this.signatureMandatory = signatureMandatory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSignatureMandatory() {
        return signatureMandatory;
    }

    public void setSignatureMandatory(Boolean signatureMandatory) {
        this.signatureMandatory = signatureMandatory;
    }
}
