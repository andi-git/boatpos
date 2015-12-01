package org.boatpos.dao.api;

import org.boatpos.model.Boat;

import java.util.List;
import java.util.Optional;

/**
 * The DAO for {@link Boat}.
 */
public interface BoatDao extends GenericDao<Boat> {

    /**
     * Get a {@link Boat} by it's {@link Boat#name}.
     *
     * @param name the name of the {@link Boat}
     * @return an {@link Optional} of {@link Boat}
     */
    Optional<Boat> getByName(String name);

    /**
     * Get a {@link Boat} by it's {@link Boat#shortName}.
     *
     * @param shortName the shortName of the {@link Boat}
     * @return an {@link Optional} of {@link Boat}
     */
    Optional<Boat> getByShortName(String shortName);

    /**
     * Get a {@link List} of all {@link Boat}s ordered by {@link Boat#priority}.
     *
     * @return a {@link List} of all {@link Boat}s ordered by {@link Boat#priority}
     */
    List<Boat> getAll();
}
