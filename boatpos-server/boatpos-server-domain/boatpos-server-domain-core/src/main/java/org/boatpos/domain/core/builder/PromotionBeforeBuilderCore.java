package org.boatpos.domain.core.builder;

import org.boatpos.model.PromotionBeforeEntity;
import org.boatpos.domain.api.builder.PromotionBeforeBuilder;
import org.boatpos.domain.api.model.PromotionBefore;
import org.boatpos.domain.api.values.TimeCredit;
import org.boatpos.domain.core.model.PromotionBeforeCore;
import org.boatpos.service.api.bean.PromotionBeforeBean;

import javax.enterprise.context.Dependent;

@Dependent
public class PromotionBeforeBuilderCore extends PromotionBuilderCore<PromotionBeforeBuilder, PromotionBefore, PromotionBeforeCore, PromotionBeforeEntity, PromotionBeforeBean> implements PromotionBeforeBuilder {

    protected TimeCredit timeCredit;

    @Override
    public PromotionBeforeBuilder add(TimeCredit timeCredit) {
        this.timeCredit = timeCredit;
        return this;
    }

    @Override
    public PromotionBefore build() {
        return new PromotionBeforeCore(id, version, enabled, priority, name, formulaPrice, rentals, timeCredit, keyBinding, pictureUrl, pictureUrlThumb);
    }
}
