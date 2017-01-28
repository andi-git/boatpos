package org.regkas.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Representation of an element of a receipt: Monats-Beleg.
 */
@Entity
@DiscriminatorValue("Monats-Beleg")
public class ReceiptTypeMonatEntity extends ReceiptTypeEntity {

    public ReceiptTypeMonatEntity() {
    }

    public ReceiptTypeMonatEntity(Long id, Integer version, Boolean enabled, Integer priority, String pictureUrl, String pictureUrlThumb, String name) {
        super(id, version, enabled, priority, pictureUrl, pictureUrlThumb, name);
    }
}
