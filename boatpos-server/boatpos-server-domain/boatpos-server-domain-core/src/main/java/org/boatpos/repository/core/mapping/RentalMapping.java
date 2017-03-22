package org.boatpos.repository.core.mapping;

import org.boatpos.common.model.PaymentMethod;
import org.boatpos.common.repository.core.mapping.Mapping;
import org.boatpos.model.PromotionBeforeEntity;
import org.boatpos.model.RentalEntity;
import org.boatpos.service.api.bean.PromotionAfterBean;
import org.boatpos.service.api.bean.PromotionBeforeBean;
import org.boatpos.service.api.bean.RentalBean;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;

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
        if (rentalEntity.getPromotion() != null) {
            if (rentalEntity.getPromotion() instanceof PromotionBeforeEntity) {
                rentalBean.setPromotionBeforeBean((PromotionBeforeBean) promotionMapping.mapEntity(rentalEntity.getPromotion()));
            } else {
                rentalBean.setPromotionAfterBean((PromotionAfterBean) promotionMapping.mapEntity(rentalEntity.getPromotion()));
            }
        }
        rentalBean.setPaymentMethodBefore(map(rentalEntity.getPaymentMethodBefore()));
        rentalBean.setPaymentMethodAfter(map(rentalEntity.getPaymentMethodAfter()));
        return rentalBean;
    }

    @Override
    public RentalEntity mapDto(RentalBean rentalBean) {
        RentalEntity rentalEntity = super.mapDto(rentalBean);
        if (rentalBean.getPromotionBeforeBean() != null) {
            rentalEntity.setPromotion(promotionMapping.mapDto(rentalBean.getPromotionBeforeBean()));
        } else {
            rentalEntity.setPromotion(promotionMapping.mapDto(rentalBean.getPromotionAfterBean()));
        }
        rentalEntity.setPaymentMethodBefore(PaymentMethod.getOrNull(rentalBean.getPaymentMethodBefore()));
        return rentalEntity;
    }

    private String map(PaymentMethod paymentMethod) {
        return paymentMethod != null ? paymentMethod.toString() : null;
    }
}
