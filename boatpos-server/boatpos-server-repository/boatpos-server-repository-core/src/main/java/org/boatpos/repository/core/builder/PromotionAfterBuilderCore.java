package org.boatpos.repository.core.builder;

import org.boatpos.model.PromotionAfterEntity;
import org.boatpos.model.PromotionBeforeEntity;
import org.boatpos.repository.api.builder.PromotionAfterBuilder;
import org.boatpos.repository.api.builder.PromotionBeforeBuilder;
import org.boatpos.repository.api.model.PromotionAfter;
import org.boatpos.repository.api.model.PromotionBefore;
import org.boatpos.repository.api.values.TimeCredit;
import org.boatpos.repository.core.model.PromotionAfterCore;
import org.boatpos.repository.core.model.PromotionBeforeCore;
import org.boatpos.service.api.bean.PromotionAfterBean;
import org.boatpos.service.api.bean.PromotionBeforeBean;

import javax.enterprise.context.Dependent;

@Dependent
public class PromotionAfterBuilderCore extends PromotionBuilderCore<PromotionAfterBuilder, PromotionAfter, PromotionAfterCore, PromotionAfterEntity, PromotionAfterBean> implements PromotionAfterBuilder {

    @Override
    public PromotionAfter build() {
        return new PromotionAfterCore(id, version, enabled, priority, name, formulaPrice, rentals);
    }
}
