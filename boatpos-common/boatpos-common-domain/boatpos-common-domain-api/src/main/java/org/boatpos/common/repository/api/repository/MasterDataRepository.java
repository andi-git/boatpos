package org.boatpos.common.repository.api.repository;

import org.boatpos.common.repository.api.builder.MasterDataBuilder;
import org.boatpos.common.repository.api.model.MasterData;
import org.boatpos.common.repository.api.values.Enabled;

import java.util.List;

/**
 * The repository for the {@link MasterData}.
 */
public interface MasterDataRepository<MODEL extends MasterData, BUILDER extends MasterDataBuilder> extends DomainModelRepository<MODEL, BUILDER> {

    /**
     * Get a {@link List} of all {@link MODEL}s ordered by {@code priority}.
     *
     * @return a {@link List} of all {@link MODEL}s ordered by {@code priority}
     */
    List<MODEL> loadAll();

    /**
     * Get a {@link List} of all enabled {@link MODEL}s ordered by priority.
     *
     * @param enabled flag if the commitments should enabled or disabled
     * @return a {@link List} of all enabled {@link MODEL}s ordered by priority
     */
    List<MODEL> loadAll(Enabled enabled);
}