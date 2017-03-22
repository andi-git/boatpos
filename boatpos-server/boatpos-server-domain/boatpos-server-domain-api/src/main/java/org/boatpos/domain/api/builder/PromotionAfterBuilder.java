package org.boatpos.domain.api.builder;

import org.boatpos.domain.api.model.PromotionAfter;
import org.boatpos.domain.api.model.PromotionBefore;
import org.boatpos.model.PromotionAfterEntity;
import org.boatpos.service.api.bean.PromotionAfterBean;

/**
 * Builder for {@link PromotionBefore}.
 */
public interface PromotionAfterBuilder extends PromotionBuilder<PromotionAfterBuilder, PromotionAfter, PromotionAfterEntity, PromotionAfterBean> {
}
