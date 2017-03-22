package org.boatpos.domain.core.model;

import org.boatpos.common.domain.api.values.DomainId;
import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.domain.api.values.KeyBinding;
import org.boatpos.common.domain.api.values.PictureUrl;
import org.boatpos.common.domain.api.values.PictureUrlThumb;
import org.boatpos.common.domain.api.values.Priority;
import org.boatpos.common.domain.api.values.SimpleValueObject;
import org.boatpos.common.domain.api.values.Version;
import org.boatpos.domain.api.values.FormulaPrice;
import org.boatpos.domain.api.values.Name;
import org.boatpos.domain.api.values.TimeCredit;
import org.boatpos.domain.core.mapping.PromotionBeforeMapping;
import org.boatpos.model.PromotionBeforeEntity;
import org.boatpos.domain.api.model.PromotionBefore;
import org.boatpos.domain.api.model.Rental;
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