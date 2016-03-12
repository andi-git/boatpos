package org.boatpos.repository.api.repository;

import org.boatpos.common.repository.api.repository.MasterDataRepository;
import org.boatpos.repository.api.builder.PromotionAfterBuilder;
import org.boatpos.repository.api.model.PromotionAfter;
import org.boatpos.repository.api.values.Name;

import java.util.Optional;

/**
 * The repository for the {@link PromotionAfter}s.
 */
public interface PromotionAfterRepository extends MasterDataRepository<PromotionAfter> {

    /**
     * Get the {@link PromotionAfterBuilder}.
     *
     * @return the {@link PromotionAfterBuilder}
     */
    PromotionAfterBuilder builder();

    /**
     * Get a {@link PromotionAfter} by it's name.
     *
     * @param name the name of the {@link PromotionAfter}
     * @return an {@link Optional} of {@link PromotionAfter}
     */
    Optional<PromotionAfter> loadBy(Name name);
}
