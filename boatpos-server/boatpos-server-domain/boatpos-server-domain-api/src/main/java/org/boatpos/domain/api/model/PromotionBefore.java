package org.boatpos.domain.api.model;

import org.boatpos.domain.api.values.TimeCredit;
import org.boatpos.model.PromotionBeforeEntity;
import org.boatpos.service.api.bean.PromotionBeforeBean;

/**
 * The domain model for a promotion before the rental.
 */
public interface PromotionBefore extends Promotion<PromotionBefore, PromotionBeforeEntity, PromotionBeforeBean> {

    TimeCredit getTimeCredit();

    PromotionBefore setTimeCredit(TimeCredit timeCredit);
}
