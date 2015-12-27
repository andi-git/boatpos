package org.boatpos.repository.api.builder;

import org.boatpos.model.PromotionBeforeEntity;
import org.boatpos.repository.api.model.PromotionBefore;
import org.boatpos.repository.api.values.TimeCredit;
import org.boatpos.service.api.bean.PromotionBeforeBean;

/**
 * Builder for {@link PromotionBefore}.
 */
public interface PromotionBeforeBuilder extends PromotionBuilder<PromotionBeforeBuilder, PromotionBefore, PromotionBeforeEntity, PromotionBeforeBean> {

    PromotionBeforeBuilder add(TimeCredit timeCredit);
}
