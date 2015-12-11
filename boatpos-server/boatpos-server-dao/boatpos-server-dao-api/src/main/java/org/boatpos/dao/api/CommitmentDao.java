package org.boatpos.dao.api;

import org.boatpos.model.Commitment;

import java.util.List;
import java.util.Optional;

/**
 * The DAO for {@link Commitment}.
 */
public interface CommitmentDao extends GenericMasterDataDao<Commitment> {

    /**
     * Get a {@link Commitment} by it's {@link Commitment#name}.
     *
     * @param name the name of the {@link Commitment}
     * @return an {@link Optional} of {@link Commitment}
     */
    Optional<Commitment> getByName(String name);

    /**
     * Get a {@link List} of all {@link Commitment}s ordered by {@link Commitment#priority}.
     *
     * @return a {@link List} of all {@link Commitment}s ordered by {@link Commitment#priority}
     */
    List<Commitment> getAll();

    /**
     * Get a {@link List} of all enabled ({@link Commitment#enabled}) {@link Commitment}s ordered by {@link
     * Commitment#priority}.
     *
     * @return a {@link List} of all enabled {@link Commitment}s ordered by {@link Commitment#priority}
     */
    List<Commitment> getAllEnabled();

    /**
     * Get a {@link List} of all disabled ({@link Commitment#enabled}) {@link Commitment}s ordered by {@link
     * Commitment#priority}.
     *
     * @return a {@link List} of all disabled {@link Commitment}s ordered by {@link Commitment#priority}
     */
    List<Commitment> getAllDisabled();
}
