package org.regkas.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Set;

/**
 * Representation of a special tax-set: Null.
 */
@Entity
@DiscriminatorValue("Satz-Null")
public class TaxSetNullEntity extends TaxSetEntity {

    public TaxSetNullEntity() {
    }

    public TaxSetNullEntity(Long id, Integer version, Boolean enabled, Integer priority, String pictureUrl, String pictureUrlThumb, String name, Integer taxPercent, Set<ProductGroupEntity> productGroups) {
        super(id, version, enabled, priority, pictureUrl, pictureUrlThumb, name, taxPercent, productGroups);
    }
}
