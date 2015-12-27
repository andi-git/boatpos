package org.boatpos.repository.core.mapping;

import org.boatpos.model.PromotionEntity;
import org.boatpos.model.PromotionAfterEntity;
import org.boatpos.model.PromotionBeforeEntity;
import org.boatpos.service.api.bean.PromotionAfterBean;
import org.boatpos.service.api.bean.PromotionBean;
import org.boatpos.service.api.bean.PromotionBeforeBean;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 * Mapping between {@link PromotionEntity} and {@link PromotionBean}.
 */
@Dependent
public class PromotionMapping extends Mapping<PromotionEntity, PromotionBean> {

    @Inject
    private PromotionBeforeMapping promotionBeforeMapping;

    @Inject
    private PromotionAfterMapping promotionAfterMapping;

    public PromotionBean mapEntity(PromotionEntity entity) {
        return entity instanceof PromotionAfterEntity ?
                promotionAfterMapping.mapEntity((PromotionAfterEntity) entity) :
                promotionBeforeMapping.mapEntity((PromotionBeforeEntity) entity);
    }

    public void mapEntity(PromotionEntity entity, PromotionBean dto) {
        if (entity instanceof PromotionAfterEntity) {
            promotionAfterMapping.mapEntity((PromotionAfterEntity) entity, (PromotionAfterBean) dto);
        } else {
            promotionBeforeMapping.mapEntity((PromotionBeforeEntity) entity, (PromotionBeforeBean) dto);
        }
    }

    public PromotionEntity mapDto(PromotionBean dto) {
        return dto instanceof PromotionAfterBean ?
                promotionAfterMapping.mapDto((PromotionAfterBean) dto) :
                promotionBeforeMapping.mapDto((PromotionBeforeBean) dto);
    }

    public void mapDto(PromotionBean dto, PromotionEntity entity) {
        if (dto instanceof PromotionAfterBean) {
            promotionAfterMapping.mapDto((PromotionAfterBean) dto, (PromotionAfterEntity) entity);
        } else {
            promotionBeforeMapping.mapDto((PromotionBeforeBean) dto, (PromotionBeforeEntity) entity);
        }
    }
}