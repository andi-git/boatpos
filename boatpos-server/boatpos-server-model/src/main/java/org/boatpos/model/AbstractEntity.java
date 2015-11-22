package org.boatpos.model;

import com.google.common.base.Objects;
import com.google.gson.GsonBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Basic class for all entities with an {@link #id} and a {@link #version}.
 */
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Integer version;

    public AbstractEntity() {
    }

    public AbstractEntity(Long id, Integer version) {
        this.id = id;
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equal(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this);
    }
}
