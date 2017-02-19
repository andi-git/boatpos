package org.regkas.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Set;

/**
 * Representation of a special tax-set: Ermäßigt1.
 */
@Entity
@DiscriminatorValue("Satz-Ermaessigt-1")
public class TaxSetErmaessigt1Entity extends TaxSetEntity {

    public TaxSetErmaessigt1Entity() {
    }

    public TaxSetErmaessigt1Entity(Long id, Integer version, Boolean enabled, Integer priority, String pictureUrl, String pictureUrlThumb, String name, Integer taxPercent, Set<ProductGroupEntity> productGroups) {
        super(id, version, enabled, priority, pictureUrl, pictureUrlThumb, name, taxPercent, productGroups);
    }
}
