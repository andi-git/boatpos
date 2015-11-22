package org.boatpos.dao.api;

import org.boatpos.model.AbstractEntity;

import javax.persistence.PersistenceContext;

/**
 * An abstract DAO to get CRUD-functions for the current dao.
 *
 * @param <ENTITY> a concrete type of {@link AbstractEntity}
 */
public interface GenericDao<ENTITY extends AbstractEntity> {

    /**
     * Search an {@link AbstractEntity} via {@link AbstractEntity#id}.
     *
     * @param id the unique {@link AbstractEntity#id}
     * @return the {@link AbstractEntity} within the {@link PersistenceContext}
     */
    ENTITY getById(Long id);

    /**
     * The {@link AbstractEntity} to persist.
     *
     * @param entity the {@link AbstractEntity} to persist
     * @return the {@link AbstractEntity} within the {@link PersistenceContext}
     */
    ENTITY save(ENTITY entity);

    /**
     * The {@link AbstractEntity} to update.
     *
     * @param entity the {@link AbstractEntity} to update
     * @return the {@link AbstractEntity} within the {@link PersistenceContext}
     */
    ENTITY update(ENTITY entity);

    /**
     * The {@link AbstractEntity} to delete.
     *
     * @param entity the {@link AbstractEntity} to delete
     */
    void delete(ENTITY entity);

    /**
     * Delete an {@link AbstractEntity} via {@link AbstractEntity#id}.
     *
     * @param id - the {@code id} of the {@link AbstractEntity} to delete
     */
    void delete(Long id);

    /**
     * The current type of the {@link ENTITY}.
     *
     * @return the current type of the {@link ENTITY}
     */
    Class<ENTITY> getType();
}
