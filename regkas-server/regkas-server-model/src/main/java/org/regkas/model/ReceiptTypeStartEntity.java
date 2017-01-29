package org.regkas.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Representation of an element of a receipt: Start-Beleg.
 */
@Entity
@DiscriminatorValue("Start-Beleg")
public class ReceiptTypeStartEntity extends ReceiptTypeEntity {

    public ReceiptTypeStartEntity() {
    }

    public ReceiptTypeStartEntity(Long id, Integer version, Boolean enabled, Integer priority, String pictureUrl, String pictureUrlThumb, String name) {
        super(id, version, enabled, priority, pictureUrl, pictureUrlThumb, name);
    }
}