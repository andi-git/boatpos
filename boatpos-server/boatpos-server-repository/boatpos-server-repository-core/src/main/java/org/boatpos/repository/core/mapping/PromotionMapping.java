package org.boatpos.repository.core.mapping;

import org.boatpos.model.PromotionAfterEntity;
import org.boatpos.model.PromotionBeforeEntity;
import org.boatpos.model.PromotionEntity;
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
        if (entity instanceof PromotionAfterEntity) {
            return promotionAfterMapping.mapEntity((PromotionAfterEntity) entity);
        } else if (entity instanceof PromotionBeforeEntity) {
            return promotionBeforeMapping.mapEntity((PromotionBeforeEntity) entity);
        } else {
            return null;
        }
    }

    public void mapEntity(PromotionEntity entity, PromotionBean dto) {
        if (entity instanceof PromotionAfterEntity) {
            promotionAfterMapping.mapEntity((PromotionAfterEntity) entity, (PromotionAfterBean) dto);
        } else {
            promotionBeforeMapping.mapEntity((PromotionBeforeEntity) entity, (PromotionBeforeBean) dto);
        }
    }

    public PromotionEntity mapDto(PromotionBean dto) {
        if (dto instanceof PromotionAfterBean) {
            return promotionAfterMapping.mapDto((PromotionAfterBean) dto);
        } else if (dto instanceof PromotionBeforeBean) {
            return promotionBeforeMapping.mapDto((PromotionBeforeBean) dto);
        } else {
            return null;
        }
    }

    public void mapDto(PromotionBean dto, PromotionEntity entity) {
        if (dto instanceof PromotionAfterBean) {
            promotionAfterMapping.mapDto((PromotionAfterBean) dto, (PromotionAfterEntity) entity);
        } else {
            promotionBeforeMapping.mapDto((PromotionBeforeBean) dto, (PromotionBeforeEntity) entity);
        }
    }
}