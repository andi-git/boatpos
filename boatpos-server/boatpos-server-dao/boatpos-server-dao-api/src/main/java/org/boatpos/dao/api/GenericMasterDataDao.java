package org.boatpos.dao.api;

import org.boatpos.model.AbstractMasterDataEntity;

import java.util.List;

/**
 * Generic DAO for state (enabled / disabled).
 */
public interface GenericMasterDataDao<ENTITY extends AbstractMasterDataEntity> extends GenericDao<ENTITY> {

    /**
     * Get a {@link List} of all {@link ENTITY}s ordered by {@link AbstractMasterDataEntity#priority}.
     *
     * @return a {@link List} of all {@link ENTITY}s ordered by {@link AbstractMasterDataEntity#priority}
     */
    List<ENTITY> getAll();

    /**
     * Get a {@link List} of all enabled {@link ENTITY}s ordered by {@link AbstractMasterDataEntity#priority}.
     *
     * @return a {@link List} of all enabled {@link ENTITY}s ordered by {@link AbstractMasterDataEntity#priority}
     */
    List<ENTITY> getAllEnabled();

    /**
     * Get a {@link List} of all disabled {@link ENTITY}s ordered by {@link AbstractMasterDataEntity#priority}.
     *
     * @return a {@link List} of all disabled {@link ENTITY}s ordered by {@link AbstractMasterDataEntity#priority}
     */
    List<ENTITY> getAllDisabled();

}
