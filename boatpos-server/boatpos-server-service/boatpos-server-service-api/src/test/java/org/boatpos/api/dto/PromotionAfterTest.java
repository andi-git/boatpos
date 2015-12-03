package org.boatpos.api.dto;

import org.boatpos.test.JavaBeanTest;
import org.junit.Test;

public class PromotionAfterTest extends JavaBeanTest<PromotionAfter> {

    @Override
    protected Class<PromotionAfter> getType() {
        return PromotionAfter.class;
    }

    @Test
    public void testConstructor() {
        createPromotionAfter();
    }

    public static PromotionAfter createPromotionAfter() {
        return new PromotionAfter(1L, 1, "HolliKnolli", "price / 3", 1);
    }
}