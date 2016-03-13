package org.boatpos.common.repository.core.model;

import com.google.gson.annotations.Expose;
import org.boatpos.common.model.AbstractEntity;
import org.boatpos.common.model.AbstractMasterDataEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Representation of a boat.
 */
@SuppressWarnings({"unused", "JpaDataSourceORMInspection"})
@Entity
@Table(name = "foo")
public class FooEntity extends AbstractEntity {

    public FooEntity() {
    }

    public FooEntity(Long id, Integer version) {
        super(id, version);
    }
}
