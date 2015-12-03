package org.boatpos.service.api.bean;

/**
 * A {@link PromotionBean} that is handled before the rental.
 */
@SuppressWarnings("unused")
public class PromotionBeanAfterBean extends PromotionBean {

    public PromotionBeanAfterBean() {
    }

    public PromotionBeanAfterBean(Long id, Integer version, String name, String priceCalculation, Integer priority) {
        super(id, version, name, priceCalculation, priority);
    }
}
