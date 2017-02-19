package org.regkas.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Set;

/**
 * Representation of a special tax-set: Normal.
 */
@Entity
@DiscriminatorValue("Satz-Normal")
public class TaxSetNormalEntity extends TaxSetEntity {

    public TaxSetNormalEntity() {
    }

    public TaxSetNormalEntity(Long id, Integer version, Boolean enabled, Integer priority, String pictureUrl, String pictureUrlThumb, String name, Integer taxPercent, Set<ProductGroupEntity> productGroups) {
        super(id, version, enabled, priority, pictureUrl, pictureUrlThumb, name, taxPercent, productGroups);
    }
}
