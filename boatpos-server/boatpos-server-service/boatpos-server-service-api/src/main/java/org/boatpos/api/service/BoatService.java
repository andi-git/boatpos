package org.boatpos.api.service;

import org.boatpos.api.dto.Boat;

import java.util.List;

/**
 * Service for {@link Boat}s.
 */
public interface BoatService {

    /**
     * Get a {@link List} of all {@link Boat}s ordered by {@link Boat#priority}.
     *
     * @return a {@link List} of all {@link Boat}s ordered by {@link Boat#priority}
     */
    List<Boat> getAll();

    /**
     * Get a {@link Boat} by it's id.
     *
     * @param id the id of the {@link Boat}
     * @return the {@link Boat} or {@code null} if the id is not available
     */
    Boat getById(Long id);

    /**
     * Get a {@link Boat} by it's name.
     *
     * @param name the name of the {@link Boat}
     * @return the {@link Boat} or {@code null} if it is not available
     */
    Boat getByName(String name);

    /**
     * Get a {@link Boat} by it's short-name.
     *
     * @param shortName the short-name of the {@link Boat}
     * @return the {@link Boat} or {@code null} if it is not available
     */
    Boat getByShortName(String shortName);

    /**
     * Save a new {@link Boat}. The {@link Boat#id} must no be set.
     *
     * @param boat the {@link Boat} to save
     * @return the saved {@link Boat} extended with the id
     */
    Boat save(Boat boat);

    /**
     * Update an existing {@link Boat}. The {@link Boat#id} must be set and valid.
     *
     * @param boat the {@link Boat} to update
     * @return the updated {@link Boat}
     */
    Boat update(Boat boat);

    /**
     * Delete an existing {@link Boat}. The {@link Boat#id} must be set and valid.
     *
     * @param boat the {@link Boat} to delete
     * @return {@code true} if the operation was successful
     */
    boolean delete(Boat boat);

    /**
     * Delete an existing {@link Boat} via the (valid) id.
     *
     * @param id the id of the {@link Boat} to delete
     * @return {@code true} if the operation was successful
     */
    boolean deleteById(Long id);
}
