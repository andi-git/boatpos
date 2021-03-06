package org.regkas.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Training-Beleg")
public class ReceiptTypeTrainingEntity extends ReceiptTypeEntity {

    public ReceiptTypeTrainingEntity() {
    }

    public ReceiptTypeTrainingEntity(Long id, Integer version, Boolean enabled, Integer priority, String pictureUrl, String pictureUrlThumb, String name, Boolean signatureMandatory) {
        super(id, version, enabled, priority, pictureUrl, pictureUrlThumb, name, signatureMandatory);
    }
}
