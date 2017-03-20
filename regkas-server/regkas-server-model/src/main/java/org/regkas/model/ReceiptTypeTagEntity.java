package org.regkas.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Tages-Beleg")
public class ReceiptTypeTagEntity extends ReceiptTypeEntity {

    public ReceiptTypeTagEntity() {
    }

    public ReceiptTypeTagEntity(Long id, Integer version, Boolean enabled, Integer priority, String pictureUrl, String pictureUrlThumb, String name, Boolean signatureMandatory) {
        super(id, version, enabled, priority, pictureUrl, pictureUrlThumb, name, signatureMandatory);
    }
}
