package org.boatpos.domain.api.repository;

import org.boatpos.common.domain.api.repository.MasterDataRepositoryWithDto;
import org.boatpos.common.domain.api.values.DomainId;
import org.boatpos.domain.api.builder.BoatBuilder;
import org.boatpos.domain.api.model.Boat;
import org.boatpos.domain.api.values.Name;
import org.boatpos.domain.api.values.ShortName;

import java.util.Optional;

/**
 * The repository for the {@link Boat}.
 */
public interface BoatRepository extends MasterDataRepositoryWithDto<Boat, BoatBuilder> {

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
