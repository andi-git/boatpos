package org.boatpos.common.repository.core.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.ParameterizedType;
import java.util.function.Consumer;

import javax.enterprise.inject.spi.CDI;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import jdk.nashorn.internal.objects.annotations.Function;
import org.boatpos.common.repository.api.model.Model;
import org.boatpos.common.repository.core.JPAHelper;
import org.boatpos.common.util.log.LogWrapper;

public abstract class ModelCore<MODEL extends Model, ENTITY> implements Model<MODEL, ENTITY> {

    private final Class<ENTITY> typeEntity;

    private final LogWrapper log;

    private ENTITY entity;

    public ModelCore() {
        super();
        // noinspection unchecked
        this.typeEntity = (Class<ENTITY>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        try {
            // noinspection unchecked
            entity = typeEntity.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // noinspection unchecked
        this.log = new LogWrapper((Class<MODEL>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public ModelCore(ENTITY abstractEntity) {
        this();
        this.entity = abstractEntity;
    }

    @Override
    public MODEL persist() {
        return persistInternal((entity, jpaHelper) -> jpaHelper.getEntityManager().persist(entity));
    }

    protected MODEL persistInternal(RealPersist<ENTITY> realPersist) {
        checkNotNull(entity, "'entity' must not be null -> maybe not loaded?");
        realPersist.run(entity, getJpaHelper());
        try {
            // flush it to get possible exception right here
            getJpaHelper().getEntityManager().flush();
        } catch (final Exception e) {
            throw handleException(e, "exception on persist entity: " + entity.getClass().getName());
        }
        // noinspection unchecked
        return (MODEL) this;
    }

    @FunctionalInterface
    public interface RealPersist<ENTITY> {

        void run(ENTITY entity, JPAHelper jpaHelper);
    }

    @Override
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

    protected void setEntity(ENTITY entity) {
        this.entity = entity;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || !(o == null || getClass() != o.getClass()) && ((Model) o).asEntity().equals(getEntity());
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

    protected JPAHelper getJpaHelper() {
        return CDI.current().select(JPAHelper.class).get();
    }
}
