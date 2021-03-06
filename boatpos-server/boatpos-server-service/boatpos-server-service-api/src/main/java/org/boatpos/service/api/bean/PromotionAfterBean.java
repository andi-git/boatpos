package org.boatpos.service.api.bean;

/**
 * A {@link PromotionBean} that is handled before the rental.
 */
@SuppressWarnings("unused")
public class PromotionAfterBean extends PromotionBean {

    public PromotionAfterBean() {
    }

    public PromotionAfterBean(Long id, Integer version, String name, String priceCalculation, Integer priority, boolean enabled, Character keyBinding, String pictureUrl, String pictureUrlThumb) {
        super(id, version, name, priceCalculation, priority, enabled, keyBinding, pictureUrl, pictureUrlThumb);
    }
}
