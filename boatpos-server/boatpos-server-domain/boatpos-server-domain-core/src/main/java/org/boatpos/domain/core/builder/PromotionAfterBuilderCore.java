package org.boatpos.domain.core.builder;

import org.boatpos.domain.core.model.PromotionAfterCore;
import org.boatpos.model.PromotionAfterEntity;
import org.boatpos.domain.api.builder.PromotionAfterBuilder;
import org.boatpos.domain.api.model.PromotionAfter;
import org.boatpos.service.api.bean.PromotionAfterBean;

import javax.enterprise.context.Dependent;

@Dependent
public class PromotionAfterBuilderCore extends PromotionBuilderCore<PromotionAfterBuilder, PromotionAfter, PromotionAfterCore, PromotionAfterEntity, PromotionAfterBean> implements PromotionAfterBuilder {

    @Override
    public PromotionAfter build() {
        return new PromotionAfterCore(id, version, enabled, priority, name, formulaPrice, rentals, keyBinding, pictureUrl, pictureUrlThumb);
    }
}
