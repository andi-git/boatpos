package org.boatpos.domain.api.repository;

import org.boatpos.common.domain.api.repository.MasterDataRepositoryWithDto;
import org.boatpos.domain.api.builder.CommitmentBuilder;
import org.boatpos.domain.api.model.Commitment;
import org.boatpos.domain.api.values.Name;

import java.util.Optional;

/**
 * The repository for the {@link Commitment}.
 */
public interface CommitmentRepository extends MasterDataRepositoryWithDto<Commitment, CommitmentBuilder> {

    /**
     * Get a {@link Commitment} by it's name.
     *
     * @param name the name of the {@link Commitment}
     * @return an {@link Optional} of {@link Commitment}
     */
    Optional<Commitment> loadBy(Name name);
}
