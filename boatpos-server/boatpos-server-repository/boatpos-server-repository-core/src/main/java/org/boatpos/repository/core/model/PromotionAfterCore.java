package org.boatpos.repository.core.model;

import org.boatpos.model.PromotionAfterEntity;
import org.boatpos.repository.api.model.PromotionAfter;
import org.boatpos.repository.api.model.Rental;
import org.boatpos.repository.api.values.*;
import org.boatpos.repository.core.mapping.PromotionAfterMapping;
import org.boatpos.service.api.bean.PromotionAfterBean;

import java.util.Set;

public class PromotionAfterCore extends PromotionCore<PromotionAfter, PromotionAfterEntity, PromotionAfterBean> implements PromotionAfter {

    public PromotionAfterCore(DomainId id,
                              Version version,
                              Enabled enabled,
                              Priority priority,
                              Name name,
                              FormulaPrice formulaPrice,
                              Set<Rental> rentals,
                              KeyBinding keyBinding,
                              PictureUrl pictureUrl,
                              PictureUrlThumb pictureUrlThumb) {
        super(id, version, enabled, priority, name, formulaPrice, rentals, keyBinding, pictureUrl, pictureUrlThumb);
    }

    public PromotionAfterCore(PromotionAfterEntity promotionAfterEntity) {
        super(promotionAfterEntity);
    }

    public PromotionAfterCore(PromotionAfterBean promotionAfterBean) {
        this(PromotionAfterMapping.fromCDI().mapDto(promotionAfterBean));
    }

    @Override
    public PromotionAfterBean asDto() {
        return PromotionAfterMapping.fromCDI().mapEntity(getEntity());
    }
}