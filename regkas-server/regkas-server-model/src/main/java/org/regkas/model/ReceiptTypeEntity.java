package org.regkas.model;

import com.google.gson.annotations.Expose;
import org.boatpos.common.model.AbstractMasterDataEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Representation of an element of a receipt.
 */
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

    public ReceiptTypeEntity() {
    }

    public ReceiptTypeEntity(Long id, Integer version, Boolean enabled, Integer priority, String pictureUrl, String pictureUrlThumb, String name) {
        super(id, version, enabled, priority, '#', pictureUrl, pictureUrlThumb);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
