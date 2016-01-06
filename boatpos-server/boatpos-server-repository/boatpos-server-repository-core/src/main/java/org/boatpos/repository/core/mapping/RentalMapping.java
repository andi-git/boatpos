package org.boatpos.repository.core.mapping;

import org.boatpos.model.CommitmentEntity;
import org.boatpos.model.RentalEntity;
import org.boatpos.service.api.bean.CommitmentBean;
import org.boatpos.service.api.bean.RentalBean;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import java.util.Set;

/**
 * Mapping between {@link RentalEntity} and {@link RentalBean}.
 */
@Dependent
public class RentalMapping extends Mapping<RentalEntity, RentalBean> {

    @Inject
    private PromotionMapping promotionMapping;

    public static RentalMapping fromCDI() {
        return CDI.current().select(RentalMapping.class).get();
    }

    @Override
    public RentalBean mapEntity(RentalEntity rentalEntity) {
        RentalBean rentalBean = super.mapEntity(rentalEntity);
        rentalBean.setPromotionBean(promotionMapping.mapEntity(rentalEntity.getPromotion()));
        return rentalBean;
    }

    @Override
    public RentalEntity mapDto(RentalBean rentalBean) {
        RentalEntity rentalEntity = super.mapDto(rentalBean);
        rentalEntity.setPromotion(promotionMapping.mapDto(rentalBean.getPromotionBean()));
        return rentalEntity;
    }
}
