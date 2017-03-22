package org.boatpos.domain.core.mapping;

import org.boatpos.common.domain.core.mapping.Mapping;
import org.boatpos.model.PromotionBeforeEntity;
import org.boatpos.service.api.bean.PromotionBeforeBean;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.CDI;

/**
 * Mapping between {@link PromotionBeforeEntity} and {@link PromotionBeforeBean}.
 */
@Dependent
public class PromotionBeforeMapping extends Mapping<PromotionBeforeEntity, PromotionBeforeBean> {

    public static PromotionBeforeMapping fromCDI() {
        return CDI.current().select(PromotionBeforeMapping.class).get();
    }
}
