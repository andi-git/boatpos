package org.regkas.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Jahres-Beleg")
public class ReceiptTypeJahrEntity extends ReceiptTypeEntity {

    public ReceiptTypeJahrEntity() {
    }

    public ReceiptTypeJahrEntity(Long id, Integer version, Boolean enabled, Integer priority, String pictureUrl, String pictureUrlThumb, String name, Boolean signatureMandatory) {
        super(id, version, enabled, priority, pictureUrl, pictureUrlThumb, name, signatureMandatory);
    }
}
