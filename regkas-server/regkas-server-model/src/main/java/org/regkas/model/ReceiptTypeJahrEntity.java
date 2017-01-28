package org.regkas.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Representation of an element of a receipt: Jahres-Beleg.
 */
@Entity
@DiscriminatorValue("Jahres-Beleg")
public class ReceiptTypeJahrEntity extends ReceiptTypeEntity {

    public ReceiptTypeJahrEntity() {
    }

    public ReceiptTypeJahrEntity(Long id, Integer version, Boolean enabled, Integer priority, String pictureUrl, String pictureUrlThumb, String name) {
        super(id, version, enabled, priority, pictureUrl, pictureUrlThumb, name);
    }
}
