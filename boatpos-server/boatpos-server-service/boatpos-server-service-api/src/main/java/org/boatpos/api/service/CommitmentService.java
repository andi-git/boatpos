package org.boatpos.api.service;

import org.boatpos.api.dto.Commitment;

import java.util.List;

/**
 * Service for {@link Commitment}s.
 */
public interface CommitmentService {

    /**
     * Get a {@link List} of all {@link Commitment}s ordered by {@link Commitment#priority}.
     *
     * @return a {@link List} of all {@link Commitment}s ordered by {@link Commitment#priority}
     */
    List<Commitment> getAll();

    /**
     * Get a {@link Commitment} by it's id.
     *
     * @param id the id of the {@link Commitment}
     * @return the {@link Commitment} or {@code null} if the id is not available
     */
    Commitment getById(Long id);

    /**
     * Get a {@link Commitment} by it's name.
     *
     * @param name the name of the {@link Commitment}
     * @return the {@link Commitment} or {@code null} if it is not available
     */
    Commitment getByName(String name);

    /**
     * Save a new {@link Commitment}. The {@link Commitment#id} must no be set.
     *
     * @param commitment the {@link Commitment} to save
     * @return the saved {@link Commitment} extended with the id
     */
    Commitment save(Commitment commitment);

    /**
     * Update an existing {@link Commitment}. The {@link Commitment#id} must be set and valid.
     *
     * @param commitment the {@link Commitment} to update
     * @return the updated {@link Commitment}
     */
    Commitment update(Commitment commitment);

    /**
     * Delete an existing {@link Commitment}. The {@link Commitment#id} must be set and valid.
     *
     * @param commitment the {@link Commitment} to delete
     * @return {@code true} if the operation was successful
     */
    boolean delete(Commitment commitment);

    /**
     * Delete an existing {@link Commitment} via the (valid) id.
     *
     * @param id the id of the {@link Commitment} to delete
     * @return {@code true} if the operation was successful
     */
    boolean deleteById(Long id);
}
