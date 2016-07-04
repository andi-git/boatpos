package org.boatpos.service.api.bean;

import org.boatpos.common.test.JavaBeanTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RemovePromotionsAfterBeanTest extends JavaBeanTest<RemovePromotionsAfterBean> {

    @Test
    public void testConstructor() {
        new RemovePromotionsAfterBean(1);
    }

    @Test
    public void testEqualsAndHashCode() {
        RemovePromotionsAfterBean removePromotionsAfterBean1 = new RemovePromotionsAfterBean(1);
        RemovePromotionsAfterBean removePromotionsAfterBean2 = new RemovePromotionsAfterBean(2);
        assertEquals(removePromotionsAfterBean1, removePromotionsAfterBean1);
        assertEquals(removePromotionsAfterBean2, removePromotionsAfterBean2);
        assertNotEquals(removePromotionsAfterBean1, removePromotionsAfterBean2);
        assertEquals(removePromotionsAfterBean1.hashCode(), removePromotionsAfterBean1.hashCode());
        assertEquals(removePromotionsAfterBean2.hashCode(), removePromotionsAfterBean2.hashCode());
        assertNotEquals(removePromotionsAfterBean1.hashCode(), removePromotionsAfterBean2.hashCode());
    }
}