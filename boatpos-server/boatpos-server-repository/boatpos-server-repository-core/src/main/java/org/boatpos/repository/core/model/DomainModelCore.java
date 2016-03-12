package org.boatpos.repository.core.model;

import org.boatpos.common.model.AbstractEntity;
import org.boatpos.repository.api.model.DomainModel;
import org.boatpos.repository.api.values.DomainId;
import org.boatpos.repository.api.values.Version;
import org.boatpos.repository.core.repository.JPAHelper;
import org.boatpos.service.api.bean.AbstractBeanBasedOnEntity;
import org.boatpos.common.util.log.LogWrapper;

import javax.enterprise.inject.spi.CDI;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.ParameterizedType;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class DomainModelCore<MODEL extends DomainModel, ENTITY extends AbstractEntity, DTO extends AbstractBeanBasedOnEntity> implements DomainModel<MODEL, ENTITY, DTO> {

    private final JPAHelper jpaHelper;

    private final Class<MODEL> typeDomainModel;

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
        this.jpaHelper = CDI.current().select(JPAHelper.class).get();
        //noinspection unchecked
        this.typeDomainModel = (Class<MODEL>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        //noinspection unchecked
        this.typeEntity = (Class<ENTITY>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        try {
            //noinspection unchecked
            entity = typeEntity.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.log = new LogWrapper(typeDomainModel);
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
            jpaHelper.getEntityManager().persist(entity);
        } else {
            log.debug("merge {}: {}", getTypeEntity().getName(), entity);
            entity = jpaHelper.getEntityManager().merge(entity);
        }
        try {
            // flush it to get possible exception right here
            jpaHelper.getEntityManager().flush();
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
            jpaHelper.getEntityManager().remove(entity);
            jpaHelper.getEntityManager().flush();
        } catch (final Exception e) {
            throw handleException(e, "exception on delete entity: " + entity.getClass().getName());
        }
    }


    @Override
    public DomainId getId() {
        return new DomainId(getEntity().getId());
    }

    @Override
    public MODEL setId(DomainId id) {
        getEntity().setId(id == null ? null : id.get());
        //noinspection unchecked
        return (MODEL) this;
    }

    @Override
    public Version getVersion() {
        return new Version(getEntity().getVersion());
    }

    @Override
    public MODEL setVersion(Version version) {
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

    public ENTITY getEntity() {
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
}