package org.boatpos.domain.core.model;

import org.boatpos.common.domain.api.values.DomainId;
import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.domain.api.values.KeyBinding;
import org.boatpos.common.domain.api.values.PictureUrl;
import org.boatpos.common.domain.api.values.PictureUrlThumb;
import org.boatpos.common.domain.api.values.Priority;
import org.boatpos.common.domain.api.values.Version;
import org.boatpos.domain.api.values.FormulaPrice;
import org.boatpos.domain.api.values.Name;
import org.boatpos.model.PromotionAfterEntity;
import org.boatpos.domain.api.model.PromotionAfter;
import org.boatpos.domain.api.model.Rental;
import org.boatpos.domain.core.mapping.PromotionAfterMapping;
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