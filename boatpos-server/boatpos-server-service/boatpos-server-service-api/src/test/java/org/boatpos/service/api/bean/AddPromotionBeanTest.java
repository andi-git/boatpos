package org.boatpos.service.api.bean;

import org.boatpos.test.JavaBeanTest;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AddPromotionBeanTest extends JavaBeanTest<AddPromotionBean> {

    @Override
    protected Class<AddPromotionBean> getType() {
        return AddPromotionBean.class;
    }

    @Test
    public void testConstructor() {
        new AddPromotionBean(1, 1L);
    }

    @Test
    public void testEqualsAndHashCode() {
        AddPromotionBean addPromotionBean1 = new AddPromotionBean(1, 1L);
        AddPromotionBean addPromotionBean2 = new AddPromotionBean(2, 1L);
        assertEquals(addPromotionBean1, addPromotionBean1);
        assertEquals(addPromotionBean2, addPromotionBean2);
        assertNotEquals(addPromotionBean1, addPromotionBean2);
        assertEquals(addPromotionBean1.hashCode(), addPromotionBean1.hashCode());
        assertEquals(addPromotionBean2.hashCode(), addPromotionBean2.hashCode());
        assertNotEquals(addPromotionBean1.hashCode(), addPromotionBean2.hashCode());
    }
}