package org.boatpos.service.core.mapping;

import org.boatpos.model.Boat;
import org.boatpos.model.Promotion;
import org.boatpos.model.PromotionBefore;
import org.boatpos.service.api.bean.BoatBean;
import org.boatpos.service.api.bean.PromotionBean;
import org.boatpos.service.api.bean.PromotionBeforeBean;

import javax.enterprise.context.Dependent;

/**
 * Mapping between {@link PromotionBefore} and {@link PromotionBeforeBean}.
 */
@Dependent
public class PromotionBeforeMapping extends Mapping<PromotionBefore, PromotionBeforeBean> {

    @Override
    protected Class<PromotionBeforeBean> getMappedDtoClass() {
        return PromotionBeforeBean.class;
    }

    @Override
    protected Class<PromotionBefore> getMappedEntityClass() {
        return PromotionBefore.class;
    }
}
