package org.boatpos.common.service.core;

import org.boatpos.common.model.AbstractMasterDataEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Representation of a boat.
 */
@SuppressWarnings({"unused", "JpaDataSourceORMInspection"})
@Entity
@Table(name = "foo")
public class FooEntity extends AbstractMasterDataEntity {

    public FooEntity() {
    }

    public FooEntity(Long id, Integer version, boolean enabled, Integer priority, Character keyBinding, String pictureUrl, String pictureUrlThumb) {
        super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
    }
}
