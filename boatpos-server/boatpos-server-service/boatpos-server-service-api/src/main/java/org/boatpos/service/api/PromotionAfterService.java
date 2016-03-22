package org.boatpos.service.api;

import org.boatpos.common.service.api.MasterDataService;
import org.boatpos.service.api.bean.PromotionAfterBean;

import java.util.Optional;

/**
 * Service for {@link PromotionAfterBean}.
 */
public interface PromotionAfterService extends MasterDataService<PromotionAfterBean> {

    /**
     * Get a {@link PromotionAfterBean} by it's name.
     *
     * @param name the name of the {@link PromotionAfterBean}
     * @return the {@link PromotionAfterBean} or {@code null} if it is not available
     */
    Optional<PromotionAfterBean> getByName(String name);
}
