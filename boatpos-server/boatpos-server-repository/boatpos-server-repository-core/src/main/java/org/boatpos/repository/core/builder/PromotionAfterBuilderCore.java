package org.boatpos.repository.core.builder;

import org.boatpos.model.PromotionAfterEntity;
import org.boatpos.repository.api.builder.PromotionAfterBuilder;
import org.boatpos.repository.api.model.PromotionAfter;
import org.boatpos.repository.core.model.PromotionAfterCore;
import org.boatpos.service.api.bean.PromotionAfterBean;

import javax.enterprise.context.Dependent;

@Dependent
public class PromotionAfterBuilderCore extends PromotionBuilderCore<PromotionAfterBuilder, PromotionAfter, PromotionAfterCore, PromotionAfterEntity, PromotionAfterBean> implements PromotionAfterBuilder {

    @Override
    public PromotionAfter build() {
        return new PromotionAfterCore(id, version, enabled, priority, name, formulaPrice, rentals, keyBinding, pictureUrl, pictureUrlThumb);
    }
}
