package org.boatpos.repository.api.repository;

import org.boatpos.repository.api.builder.BoatBuilder;
import org.boatpos.repository.api.model.Boat;
import org.boatpos.repository.api.values.DomainId;
import org.boatpos.repository.api.values.Name;
import org.boatpos.repository.api.values.ShortName;

import java.util.Optional;

/**
 * The repository for the {@link Boat}.
 */
public interface BoatRepository extends MasterDataRepository<Boat> {

    /**
     * Get the {@link BoatBuilder}.
     *
     * @return the {@link BoatBuilder}
     */
    BoatBuilder builder();

    /**
     * Load all attributes based on the {@link DomainId}.
     *
     * @param name the {@link Name} of the {@link Boat}
     * @return the current {@link Boat} with all attributes from the repository
     */
    Optional<Boat> loadBy(Name name);

    /**
     * Load all attributes based on the {@link DomainId}.
     *
     * @param shortName the {@link ShortName} of the {@link Boat}
     * @return the current {@link Boat} with all attributes from the repository
     */
    Optional<Boat> loadBy(ShortName shortName);
}
