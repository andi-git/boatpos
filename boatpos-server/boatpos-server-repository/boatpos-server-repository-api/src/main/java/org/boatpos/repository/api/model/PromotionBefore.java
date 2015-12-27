package org.boatpos.repository.api.model;

import org.boatpos.model.PromotionBeforeEntity;
import org.boatpos.model.RentalEntity;
import org.boatpos.repository.api.values.FormulaPrice;
import org.boatpos.repository.api.values.Name;
import org.boatpos.repository.api.values.TimeCredit;
import org.boatpos.service.api.bean.PromotionBeforeBean;

import java.util.Set;

/**
 * The domain model for a promotion before the rental.
 */
public interface PromotionBefore extends Promotion<PromotionBefore, PromotionBeforeEntity, PromotionBeforeBean> {

    TimeCredit getTimeCredit();

    PromotionBefore setTimeCredit(TimeCredit timeCredit);
}
