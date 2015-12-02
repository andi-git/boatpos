package org.boatpos.dao.core;

import org.boatpos.dao.api.BoatPosDB;
import org.boatpos.dao.api.GenericDao;
import org.boatpos.model.AbstractEntity;
import org.boatpos.util.log.LogWrapper;
import org.boatpos.util.log.SLF4J;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;
import java.util.Collection;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class GenericDaoCore<ENTITY extends AbstractEntity> implements GenericDao<ENTITY> {

    @Inject
    @SLF4J
    private LogWrapper log;

    @Inject
    @BoatPosDB
    private EntityManager entityManager;

    @Override
    public Optional<ENTITY> getById(final Long id) {
        log.debug("search {} via id: ", getType(), id);
        ENTITY entity = getEntityManager().find(getType(), id);
        return Optional.ofNullable(entity);
    }

    public ENTITY save(final ENTITY entity) {
        log.debug("save {}: {}", getType(), entity);
        checkNotNull(entity, "'entity' must not be null");
        getEntityManager().persist(entity);
        try {
            // flush it to get possible exception right here
            getEntityManager().flush();
        } catch (final Exception e) {
            throw handleException(e, "exception on save entity: " + entity.getClass().getName());
        }
        return entity;
    }

    @Override
    public ENTITY update(final ENTITY entity) {
        log.debug("update {}: {}", getType(), entity);
        checkNotNull(entity, "'entity' must not be null");
        try {
            final ENTITY result = getEntityManager().merge(entity);
            getEntityManager().flush();
            return result;
        } catch (Exception e) {
            log.error("exception on update", e);
            throw handleException(e, "exception on update entity: " + entity.getClass().getName());
        }
    }

    @Override
    public void delete(final Long id) {
        log.debug("delete {}: {}", getType(), id);
        checkNotNull(id, "'id' must not be null");
        Optional<ENTITY> entity = getById(id);
        if (!entity.isPresent()) {
            String message = "no entity with id " + id + " available";
            throw handleException(new RuntimeException(message), message);
        }
        delete(entity.get());
    }

    @Override
    public void delete(final ENTITY entity) {
        log.debug("delete {}: {}", getType(), entity);
        checkNotNull(entity, "'entity' must not be null");
        try {
            getEntityManager().remove(entity);
            getEntityManager().flush();
        } catch (final Exception e) {
            throw handleException(e, "exception on delete entity: " + entity.getClass().getName());
        }
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected Optional<ENTITY> getSingleResult(final TypedQuery<ENTITY> typedQuery) {
        return getSingleResult(typedQuery.getResultList());
    }

    protected Optional<ENTITY> getSingleResult(final Collection<ENTITY> collection) {
        if (collection.size() == 0) {
            return Optional.empty();
        } else if (collection.size() == 1) {
            return Optional.of(collection.iterator().next());
        } else {
            throw new NonUniqueResultException("multiple entities of " + collection.iterator().next().getClass().getName() + " available");
        }
    }

    protected TypedQuery<ENTITY> createNamedQuery(final String queryName) {
        return getEntityManager().createNamedQuery(queryName, getType());
    }

    private RuntimeException handleException(final Exception e, String message) {
        if (e.getCause() instanceof ConstraintViolationException) {
            return (ConstraintViolationException) e.getCause();
        } else {
            return new PersistenceException(message, e);
        }
    }
}
