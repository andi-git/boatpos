package org.regkas.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Representation of an element of a receipt: Null-Beleg.
 */
@Entity
@DiscriminatorValue("Null-Beleg")
public class ReceiptTypeNullEntity extends ReceiptTypeEntity {

    public ReceiptTypeNullEntity() {
    }

    public ReceiptTypeNullEntity(Long id, Integer version, Boolean enabled, Integer priority, String pictureUrl, String pictureUrlThumb, String name) {
        super(id, version, enabled, priority, pictureUrl, pictureUrlThumb, name);
    }
}
