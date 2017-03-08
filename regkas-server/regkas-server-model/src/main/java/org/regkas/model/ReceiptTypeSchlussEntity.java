package org.regkas.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Representation of an element of a receipt: Schluss-Beleg.
 */
@Entity
@DiscriminatorValue("Schluss-Beleg")
public class ReceiptTypeSchlussEntity extends ReceiptTypeEntity {

    public ReceiptTypeSchlussEntity() {
    }

    public ReceiptTypeSchlussEntity(Long id, Integer version, Boolean enabled, Integer priority, String pictureUrl, String pictureUrlThumb, String name, Boolean signatureMandatory) {
        super(id, version, enabled, priority, pictureUrl, pictureUrlThumb, name, signatureMandatory);
    }
}
