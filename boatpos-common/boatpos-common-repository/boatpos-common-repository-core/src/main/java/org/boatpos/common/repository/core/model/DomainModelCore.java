package org.boatpos.common.repository.core.model;

import org.boatpos.common.model.AbstractEntity;
import org.boatpos.common.repository.api.model.DomainModel;
import org.boatpos.common.repository.api.values.DomainId;
import org.boatpos.common.repository.api.values.Version;
import org.boatpos.common.repository.core.JPAHelper;
import org.boatpos.common.util.log.LogWrapper;

import javax.enterprise.inject.spi.CDI;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.ParameterizedType;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class DomainModelCore<MODEL extends DomainModel, ENTITY extends AbstractEntity> implements DomainModel<MODEL, ENTITY> {

    private final Class<ENTITY> typeEntity;

    private final LogWrapper log;

    private ENTITY entity;

    public DomainModelCore(DomainId id, Version version) {
        this();
        setId(id);
        setVersion(version);
    }

    public DomainModelCore() {
        super();
        //noinspection unchecked
        this.typeEntity = (Class<ENTITY>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        try {
            //noinspection unchecked
            entity = typeEntity.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //noinspection unchecked
        this.log = new LogWrapper((Class<MODEL>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public DomainModelCore(ENTITY abstractEntity) {
        this();
        this.entity = abstractEntity;
    }

    @Override
    public MODEL persist() {
        checkNotNull(entity, "'entity' must not be null -> maybe not loaded?");
        if (getId() == null || getId().get() == null) {
            log.debug("persist {}: {}", getTypeEntity().getName(), entity);
            getJpaHelper().getEntityManager().persist(entity);
        } else {
            log.debug("merge {}: {}", getTypeEntity().getName(), entity);
            entity = getJpaHelper().getEntityManager().merge(entity);
        }
        try {
            // flush it to get possible exception right here
            getJpaHelper().getEntityManager().flush();
        } catch (final Exception e) {
            throw handleException(e, "exception on persist entity: " + entity.getClass().getName());
        }
        //noinspection unchecked
        return (MODEL) this;
    }

    public void delete() {
        log.debug("delete {}: {}", getTypeEntity().getName(), entity);
        checkNotNull(entity, "'entity' must not be null -> maybe not loaded?");
        try {
            getJpaHelper().getEntityManager().remove(entity);
            getJpaHelper().getEntityManager().flush();
        } catch (final Exception e) {
            throw handleException(e, "exception on delete entity: " + entity.getClass().getName());
        }
    }

    @Override
    public DomainId getId() {
        return new DomainId(getEntity().getId());
    }

    private MODEL setId(DomainId id) {
        getEntity().setId(id == null ? null : id.get());
        //noinspection unchecked
        return (MODEL) this;
    }

    @Override
    public Version getVersion() {
        return new Version(getEntity().getVersion());
    }

    private MODEL setVersion(Version version) {
        getEntity().setVersion(version == null ? null : version.get());
        //noinspection unchecked
        return (MODEL) this;
    }

    protected Class<ENTITY> getTypeEntity() {
        return typeEntity;
    }

    @Override
    public ENTITY asEntity() {
        return entity;
    }

    protected ENTITY getEntity() {
        return entity;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || !(o == null || getClass() != o.getClass()) && ((DomainModel) o).asEntity().equals(getEntity());
    }

    @Override
    public int hashCode() {
        return getEntity().hashCode();
    }

    @Override
    public String toString() {
        return getEntity().toString();
    }

    protected RuntimeException handleException(final Exception e, String message) {
        if (e instanceof ConstraintViolationException) {
            return (ConstraintViolationException) e;
        } else {
            return new PersistenceException(message, e);
        }
    }

    private JPAHelper getJpaHelper() {
        return CDI.current().select(JPAHelper.class).get();
    }
}