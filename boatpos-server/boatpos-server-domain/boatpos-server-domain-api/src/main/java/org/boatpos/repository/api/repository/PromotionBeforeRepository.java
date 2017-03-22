package org.boatpos.repository.api.repository;

import org.boatpos.common.repository.api.repository.MasterDataRepository;
import org.boatpos.common.repository.api.repository.MasterDataRepositoryWithDto;
import org.boatpos.repository.api.builder.PromotionBeforeBuilder;
import org.boatpos.repository.api.model.PromotionBefore;
import org.boatpos.repository.api.values.Name;

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