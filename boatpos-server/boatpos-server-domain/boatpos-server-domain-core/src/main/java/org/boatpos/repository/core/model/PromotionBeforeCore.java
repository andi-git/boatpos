package org.boatpos.repository.core.model;

import org.boatpos.common.repository.api.values.*;
import org.boatpos.model.PromotionBeforeEntity;
import org.boatpos.repository.api.model.PromotionBefore;
import org.boatpos.repository.api.model.Rental;
import org.boatpos.repository.api.values.*;
import org.boatpos.repository.core.mapping.PromotionBeforeMapping;
import org.boatpos.service.api.bean.PromotionBeforeBean;

import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

public class PromotionBeforeCore extends PromotionCore<PromotionBefore, PromotionBeforeEntity, PromotionBeforeBean> implements PromotionBefore {

    public PromotionBeforeCore(DomainId id,
                               Version version,
                               Enabled enabled,
                               Priority priority,
                               Name name,
                               FormulaPrice formulaPrice,
                               Set<Rental> rentals,
                               TimeCredit timeCredit,
                               KeyBinding keyBinding,
                               PictureUrl pictureUrl,
                               PictureUrlThumb pictureUrlThumb) {
        super(id, version, enabled, priority, name, formulaPrice, rentals, keyBinding, pictureUrl, pictureUrlThumb);
        checkNotNull(timeCredit, "'timeCredit' must not be null");
        setTimeCredit(timeCredit);
    }

    public PromotionBeforeCore(PromotionBeforeEntity promotionBeforeEntity) {
        super(promotionBeforeEntity);
    }

    public PromotionBeforeCore(PromotionBeforeBean promotionBeforeBean) {
        this(PromotionBeforeMapping.fromCDI().mapDto(promotionBeforeBean));
    }

    @Override
    public TimeCredit getTimeCredit() {
        return new TimeCredit(getEntity().getTimeCredit());
    }

    @Override
    public PromotionBefore setTimeCredit(TimeCredit timeCredit) {
        getEntity().setTimeCredit(SimpleValueObject.nullSafe(timeCredit));
        return this;
    }

    @Override
    public PromotionBeforeBean asDto() {
        return PromotionBeforeMapping.fromCDI().mapEntity(getEntity());
    }
}