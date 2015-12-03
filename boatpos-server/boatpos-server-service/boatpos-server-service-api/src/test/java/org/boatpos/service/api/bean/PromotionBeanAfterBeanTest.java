package org.boatpos.service.api.bean;

import org.boatpos.test.JavaBeanTest;
import org.junit.Test;

public class PromotionBeanAfterBeanTest extends JavaBeanTest<PromotionBeanAfterBean> {

    @Override
    protected Class<PromotionBeanAfterBean> getType() {
        return PromotionBeanAfterBean.class;
    }

    @Test
    public void testConstructor() {
        createPromotionAfter();
    }

    public static PromotionBeanAfterBean createPromotionAfter() {
        return new PromotionBeanAfterBean(1L, 1, "HolliKnolli", "price / 3", 1);
    }
}