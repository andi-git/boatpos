package org.boatpos.repository.core.builder;

import org.boatpos.model.PromotionBeforeEntity;
import org.boatpos.model.PromotionEntity;
import org.boatpos.repository.api.builder.PromotionBeforeBuilder;
import org.boatpos.repository.api.builder.PromotionBuilder;
import org.boatpos.repository.api.model.Promotion;
import org.boatpos.repository.api.model.PromotionBefore;
import org.boatpos.repository.api.model.Rental;
import org.boatpos.repository.api.values.FormulaPrice;
import org.boatpos.repository.api.values.Name;
import org.boatpos.repository.api.values.TimeCredit;
import org.boatpos.repository.core.model.PromotionBeforeCore;
import org.boatpos.service.api.bean.PromotionBean;
import org.boatpos.service.api.bean.PromotionBeforeBean;

import javax.enterprise.context.Dependent;
import java.util.HashSet;
import java.util.Set;

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
