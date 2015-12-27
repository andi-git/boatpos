package org.boatpos.model;

import com.google.common.base.Objects;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Basic class for all entities with an {@link #id} and a {@link #version}.
 */
@MappedSuperclass
public abstract class AbstractEntity {

    /**
     * The technical id / primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull
    @Min(0)
    @Expose
    private Long id;

    /**
     * The version for optimistic locking.
     */
    @Version
    @NotNull
    @Min(0)
    @Expose
    private Integer version;

    public AbstractEntity() {
    }

    public AbstractEntity(Long id, Integer version) {
        this.id = id;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return getId() != null && Objects.equal(id, that.id);
    }

    @Override
    public int hashCode() {
        // super.hashCode is needed when id is null (on persisting a new entity)
        if (id != null) {
            return Objects.hashCode(id);
        } else {
            return super.hashCode();
        }
    }

    @Override
    public String toString() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
                .toJson(this);
    }
}
