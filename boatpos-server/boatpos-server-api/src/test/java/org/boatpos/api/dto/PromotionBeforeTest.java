package org.boatpos.api.dto;

import org.boatpos.test.JavaBeanTest;
import org.junit.Test;

public class PromotionBeforeTest extends JavaBeanTest<PromotionBefore> {

    @Override
    protected Class<PromotionBefore> getType() {
        return PromotionBefore.class;
    }

    @Test
    public void testConstructor() {
        createPromotionBefore();
    }

    public static PromotionBefore createPromotionBefore() {
        return new PromotionBefore(1L, 1, "Fahr 3 zahl 2", 180, "price / 3", 2);
    }
}