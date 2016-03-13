package org.boatpos.common.service.api.bean;

import com.google.common.base.Objects;
import com.google.gson.annotations.Expose;

import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Basic class for DTOs based on entities with an {@link #id} and a {@link #version}.
 */
@SuppressWarnings("unused")
public abstract class AbstractBeanBasedOnEntity extends AbstractBean {

    /**
     * The technical id / primary key.
     */
    @Id
    @NotNull
    @Min(0)
    @Expose
    private Long id;

    /**
     * The version for optimistic locking.
     */
    @NotNull
    @Min(0)
    @Expose
    private Integer version;

    public AbstractBeanBasedOnEntity() {
    }

    public AbstractBeanBasedOnEntity(Long id, Integer version) {
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
        AbstractBeanBasedOnEntity that = (AbstractBeanBasedOnEntity) o;
        return Objects.equal(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
