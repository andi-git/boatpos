package org.boatpos.service.api.bean;

import org.boatpos.test.JavaBeanTest;
import org.junit.Test;

public class PromotionBeforeBeanTest extends JavaBeanTest<PromotionBeforeBean> {

    @Override
    protected Class<PromotionBeforeBean> getType() {
        return PromotionBeforeBean.class;
    }

    @Test
    public void testConstructor() {
        createPromotionBefore();
    }

    public static PromotionBeforeBean createPromotionBefore() {
        return new PromotionBeforeBean(1L, 1, "Fahr 3 zahl 2", 180, "price / 3", 2, true);
    }
}