package org.boatpos.repository.api.repository;

import org.boatpos.repository.api.model.DomainModel;
import org.boatpos.repository.api.values.DomainId;

import java.util.Optional;

/**
 * The repository for the {@link DomainModel}.
 */
public interface DomainModelRepository<MODEL extends DomainModel> {

    /**
     * Get the {@link DomainModel} based on the {@link DomainId}.
     *
     * @param id the {@link DomainId} of the {@link DomainModel}
     * @return the current {@link DomainModel} with all attributes from the repository
     */
    Optional<MODEL> loadBy(DomainId id);
}
