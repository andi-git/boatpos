package org.boatpos.service.api;

import org.boatpos.service.api.bean.PromotionBeforeBean;

import java.util.Optional;

/**
 * Service for {@link PromotionBeforeBean}.
 */
public interface PromotionBeforeService extends MasterDataService<PromotionBeforeBean> {

    /**
     * Get a {@link PromotionBeforeBean} by it's name.
     *
     * @param name the name of the {@link PromotionBeforeBean}
     * @return the {@link PromotionBeforeBean} or {@code null} if it is not available
     */
    Optional<PromotionBeforeBean> getByName(String name);
}
