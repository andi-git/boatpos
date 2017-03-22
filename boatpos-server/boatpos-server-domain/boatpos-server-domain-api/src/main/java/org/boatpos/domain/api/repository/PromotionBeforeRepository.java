package org.boatpos.domain.api.repository;

import org.boatpos.common.domain.api.repository.MasterDataRepositoryWithDto;
import org.boatpos.domain.api.model.PromotionBefore;
import org.boatpos.domain.api.values.Name;
import org.boatpos.domain.api.builder.PromotionBeforeBuilder;

import java.util.Optional;

/**
 * The repository for the {@link PromotionBefore}s.
 */
public interface PromotionBeforeRepository extends MasterDataRepositoryWithDto<PromotionBefore, PromotionBeforeBuilder> {

    /**
     * Get a {@link PromotionBefore} by it's name.
     *
     * @param name the name of the {@link PromotionBefore}
     * @return an {@link Optional} of {@link PromotionBefore}
     */
    Optional<PromotionBefore> loadBy(Name name);
}
