package org.regkas.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Representation of an element of a receipt: Storno-Beleg.
 */
@Entity
@DiscriminatorValue("Storno-Beleg")
public class ReceiptTypeStornoEntity extends ReceiptTypeEntity {

    public ReceiptTypeStornoEntity() {
    }

    public ReceiptTypeStornoEntity(Long id, Integer version, Boolean enabled, Integer priority, String pictureUrl, String pictureUrlThumb, String name, Boolean signatureMandatory) {
        super(id, version, enabled, priority, pictureUrl, pictureUrlThumb, name, signatureMandatory);
    }
}
