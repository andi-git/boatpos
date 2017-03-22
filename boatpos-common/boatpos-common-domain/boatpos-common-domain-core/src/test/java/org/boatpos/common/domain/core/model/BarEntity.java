package org.boatpos.common.domain.core.model;

import org.boatpos.common.model.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings({"unused", "JpaDataSourceORMInspection"})
@Entity
@Table(name = "bar")
public class BarEntity extends AbstractEntity {

    public BarEntity() {
    }

    public BarEntity(Long id, Integer version) {
        super(id, version);
    }
}
