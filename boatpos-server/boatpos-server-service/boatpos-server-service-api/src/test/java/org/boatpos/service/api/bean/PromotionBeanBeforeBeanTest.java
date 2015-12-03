package org.boatpos.service.api.bean;

import org.boatpos.test.JavaBeanTest;
import org.junit.Test;

public class PromotionBeanBeforeBeanTest extends JavaBeanTest<PromotionBeanBeforeBean> {

    @Override
    protected Class<PromotionBeanBeforeBean> getType() {
        return PromotionBeanBeforeBean.class;
    }

    @Test
    public void testConstructor() {
        createPromotionBefore();
    }

    public static PromotionBeanBeforeBean createPromotionBefore() {
        return new PromotionBeanBeforeBean(1L, 1, "Fahr 3 zahl 2", 180, "price / 3", 2);
    }
}