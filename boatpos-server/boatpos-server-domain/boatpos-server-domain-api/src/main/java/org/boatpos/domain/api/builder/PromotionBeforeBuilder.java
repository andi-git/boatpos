package org.boatpos.domain.api.builder;

import org.boatpos.domain.api.model.PromotionBefore;
import org.boatpos.domain.api.values.TimeCredit;
import org.boatpos.model.PromotionBeforeEntity;
import org.boatpos.service.api.bean.PromotionBeforeBean;

/**
 * Builder for {@link PromotionBefore}.
 */
public interface PromotionBeforeBuilder extends PromotionBuilder<PromotionBeforeBuilder, PromotionBefore, PromotionBeforeEntity, PromotionBeforeBean> {

    PromotionBeforeBuilder add(TimeCredit timeCredit);
}
