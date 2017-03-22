package org.boatpos.domain.api.repository;

import org.boatpos.common.domain.api.repository.MasterDataRepositoryWithDto;
import org.boatpos.domain.api.model.PromotionAfter;
import org.boatpos.domain.api.values.Name;
import org.boatpos.domain.api.builder.PromotionAfterBuilder;

import java.util.Optional;

/**
 * The repository for the {@link PromotionAfter}s.
 */
public interface PromotionAfterRepository extends MasterDataRepositoryWithDto<PromotionAfter, PromotionAfterBuilder> {

    /**
     * Get a {@link PromotionAfter} by it's name.
     *
     * @param name the name of the {@link PromotionAfter}
     * @return an {@link Optional} of {@link PromotionAfter}
     */
    Optional<PromotionAfter> loadBy(Name name);
}
