package org.regkas.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Representation of an element of a receipt: Standard-Beleg.
 */
@Entity
@DiscriminatorValue("Standard-Beleg")
public class ReceiptTypeStandardEntity extends ReceiptTypeEntity {

    public ReceiptTypeStandardEntity() {
    }

    public ReceiptTypeStandardEntity(Long id, Integer version, Boolean enabled, Integer priority, String pictureUrl, String pictureUrlThumb, String name) {
        super(id, version, enabled, priority, pictureUrl, pictureUrlThumb, name);
    }
}
