package org.regkas.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Set;

/**
 * Representation of a special tax-set: Ermäßigt2.
 */
@Entity
@DiscriminatorValue("Satz-Ermaessigt-2")
public class TaxSetErmaessigt2Entity extends TaxSetEntity {

    public TaxSetErmaessigt2Entity() {
    }

    public TaxSetErmaessigt2Entity(Long id, Integer version, Boolean enabled, Integer priority, String pictureUrl, String pictureUrlThumb, String name, Integer taxPercent, Set<ProductGroupEntity> productGroups) {
        super(id, version, enabled, priority, pictureUrl, pictureUrlThumb, name, taxPercent, productGroups);
    }
}
