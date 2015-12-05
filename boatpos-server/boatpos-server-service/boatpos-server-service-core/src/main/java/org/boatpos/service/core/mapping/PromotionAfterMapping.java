package org.boatpos.service.core.mapping;

import org.boatpos.model.PromotionAfter;
import org.boatpos.service.api.bean.PromotionAfterBean;

import javax.enterprise.context.Dependent;

/**
 * Mapping between {@link PromotionAfter} and {@link PromotionAfterBean}.
 */
@Dependent
public class PromotionAfterMapping extends Mapping<PromotionAfter, PromotionAfterBean> {

    @Override
    protected Class<PromotionAfterBean> getMappedDtoClass() {
        return PromotionAfterBean.class;
    }

    @Override
    protected Class<PromotionAfter> getMappedEntityClass() {
        return PromotionAfter.class;
    }
}
