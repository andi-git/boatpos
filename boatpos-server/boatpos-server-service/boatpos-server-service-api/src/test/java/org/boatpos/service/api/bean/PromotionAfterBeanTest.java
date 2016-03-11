package org.boatpos.service.api.bean;

import org.boatpos.common.test.JavaBeanTest;
import org.junit.Test;

public class PromotionAfterBeanTest extends JavaBeanTest<PromotionAfterBean> {

    @Override
    protected Class<PromotionAfterBean> getType() {
        return PromotionAfterBean.class;
    }

    @Test
    public void testConstructor() {
        createPromotionAfter();
    }

    public static PromotionAfterBean createPromotionAfter() {
        return new PromotionAfterBean(1L, 1, "HolliKnolli", "price / 3", 1, true, 'a', "", "");
    }
}