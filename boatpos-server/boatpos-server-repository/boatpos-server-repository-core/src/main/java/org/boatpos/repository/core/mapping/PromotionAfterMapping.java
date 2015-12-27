package org.boatpos.repository.core.mapping;

import org.boatpos.model.PromotionAfterEntity;
import org.boatpos.service.api.bean.PromotionAfterBean;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.CDI;

/**
 * Mapping between {@link PromotionAfterEntity} and {@link PromotionAfterBean}.
 */
@Dependent
public class PromotionAfterMapping extends Mapping<PromotionAfterEntity, PromotionAfterBean> {

    public static PromotionAfterMapping fromCDI() {
        return CDI.current().select(PromotionAfterMapping.class).get();
    }
}
