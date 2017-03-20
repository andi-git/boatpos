package org.regkas.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Sammel-Beleg")
public class ReceiptTypeSammelEntity extends ReceiptTypeEntity {

    public ReceiptTypeSammelEntity() {
    }

    public ReceiptTypeSammelEntity(Long id, Integer version, Boolean enabled, Integer priority, String pictureUrl, String pictureUrlThumb, String name, Boolean signatureMandatory) {
        super(id, version, enabled, priority, pictureUrl, pictureUrlThumb, name, signatureMandatory);
    }
}
