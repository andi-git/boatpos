package org.boatpos.repository.api.repository;

import org.boatpos.common.repository.api.repository.MasterDataRepository;
import org.boatpos.common.repository.api.repository.MasterDataRepositoryWithDto;
import org.boatpos.repository.api.builder.CommitmentBuilder;
import org.boatpos.repository.api.model.Commitment;
import org.boatpos.repository.api.values.Name;

import java.util.Optional;

/**
 * The repository for the {@link Commitment}.
 */
public interface CommitmentRepository extends MasterDataRepositoryWithDto<Commitment> {

    /**
     * Get the {@link CommitmentBuilder}.
     *
     * @return the {@link CommitmentBuilder}
     */
    CommitmentBuilder builder();

    /**
     * Get a {@link Commitment} by it's name.
     *
     * @param name the name of the {@link Commitment}
     * @return an {@link Optional} of {@link Commitment}
     */
    Optional<Commitment> loadBy(Name name);
}
