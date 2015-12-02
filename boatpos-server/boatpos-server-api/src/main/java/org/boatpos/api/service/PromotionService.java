package org.boatpos.api.service;

import org.boatpos.api.dto.Promotion;

import java.util.List;

/**
 * Service for {@link Promotion}.
 */
public interface PromotionService {

    /**
     * Get a {@link List} of all {@link Promotion}s ordered by {@link Promotion#priority}.
     *
     * @return a {@link List} of all {@link Promotion}s ordered by {@link Promotion#priority}
     */
    List<Promotion> getAll();

    /**
     * Get a {@link List} of all {@link Promotion}s which are relevant <u>before</u> the rental.
     *
     * @return a {@link List} of all {@link Promotion}s which are relevant <u>before</u> the rental
     */
    List<Promotion> getAllBeforeRental();

    /**
     * Get a {@link List} of all {@link Promotion}s which are relevant <u>after</u> the rental.
     *
     * @return a {@link List} of all {@link Promotion}s which are relevant <u>after</u> the rental
     */
    List<Promotion> getAllAfterRental();

    /**
     * Get a {@link Promotion} by it's id.
     *
     * @param id the id of the {@link Promotion}
     * @return the {@link Promotion} or {@code null} if the id is not available
     */
    Promotion getById(Long id);

    /**
     * Get a {@link Promotion} by it's name.
     *
     * @param name the name of the {@link Promotion}
     * @return the {@link Promotion} or {@code null} if it is not available
     */
    Promotion getByName(String name);

    /**
     * Save a new {@link Promotion}. The {@link Promotion#id} must no be set.
     *
     * @param promotion the {@link Promotion} to save
     * @return the saved {@link Promotion} extended with the id
     */
    Promotion save(Promotion promotion);

    /**
     * Update an existing {@link Promotion}. The {@link Promotion#id} must be set and valid.
     *
     * @param promotion the {@link Promotion} to update
     * @return the updated {@link Promotion}
     */
    Promotion update(Promotion promotion);

    /**
     * Delete an existing {@link Promotion}. The {@link Promotion#id} must be set and valid.
     *
     * @param promotion the {@link Promotion} to delete
     * @return {@code true} if the operation was successful
     */
    boolean delete(Promotion promotion);

    /**
     * Delete an existing {@link Promotion} via the (valid) id.
     *
     * @param id the id of the {@link Promotion} to delete
     * @return {@code true} if the operation was successful
     */
    boolean deleteById(Long id);
}
