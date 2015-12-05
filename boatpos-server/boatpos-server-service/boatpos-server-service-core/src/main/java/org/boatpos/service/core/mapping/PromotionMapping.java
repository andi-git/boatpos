package org.boatpos.service.core.mapping;

import org.boatpos.model.Promotion;
import org.boatpos.model.PromotionAfter;
import org.boatpos.model.PromotionBefore;
import org.boatpos.service.api.bean.PromotionAfterBean;
import org.boatpos.service.api.bean.PromotionBean;
import org.boatpos.service.api.bean.PromotionBeforeBean;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 * Mapping between {@link Promotion} and {@link PromotionBean}.
 */
@Dependent
public class PromotionMapping extends Mapping<Promotion, PromotionBean> {

    @Inject
    private PromotionBeforeMapping promotionBeforeMapping;

    @Inject
    private PromotionAfterMapping promotionAfterMapping;

    @Override
    protected Class<PromotionBean> getMappedDtoClass() {
        return PromotionBean.class;
    }

    @Override
    protected Class<Promotion> getMappedEntityClass() {
        return Promotion.class;
    }

    public PromotionBean mapEntity(Promotion entity) {
        return entity instanceof PromotionAfter ?
                promotionAfterMapping.mapEntity((PromotionAfter) entity) :
                promotionBeforeMapping.mapEntity((PromotionBefore) entity);
    }

    public void mapEntity(Promotion entity, PromotionBean dto) {
        if (entity instanceof PromotionAfter) {
            promotionAfterMapping.mapEntity((PromotionAfter) entity, (PromotionAfterBean) dto);
        } else {
            promotionBeforeMapping.mapEntity((PromotionBefore) entity, (PromotionBeforeBean) dto);
        }
    }

    public Promotion mapDto(PromotionBean dto) {
        return dto instanceof PromotionAfterBean ?
                promotionAfterMapping.mapDto((PromotionAfterBean) dto) :
                promotionBeforeMapping.mapDto((PromotionBeforeBean) dto);
    }

    public void mapDto(PromotionBean dto, Promotion entity) {
        if (dto instanceof PromotionAfterBean) {
            promotionAfterMapping.mapDto((PromotionAfterBean) dto, (PromotionAfter) entity);
        } else {
            promotionBeforeMapping.mapDto((PromotionBeforeBean) dto, (PromotionBefore) entity);
        }
    }
}