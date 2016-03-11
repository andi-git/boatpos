package org.boatpos.service.api.bean;

import org.boatpos.common.test.JavaBeanTest;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PaymentBeanTest extends JavaBeanTest<PaymentBean> {

    @Override
    protected Class<PaymentBean> getType() {
        return PaymentBean.class;
    }

    @Test
    public void testConstructor() {
        new PaymentBean(1, new BigDecimal("20.0"), "card");
    }

    @Test
    public void testEqualsAndHashCode() {
        PaymentBean paymentBean1 = new PaymentBean(1, new BigDecimal("20.0"), "card");
        PaymentBean paymentBean2 = new PaymentBean(2, new BigDecimal("20.0"), "cash");
        assertEquals(paymentBean1, paymentBean1);
        assertEquals(paymentBean2, paymentBean2);
        assertNotEquals(paymentBean1, paymentBean2);
        assertEquals(paymentBean1.hashCode(), paymentBean1.hashCode());
        assertEquals(paymentBean2.hashCode(), paymentBean2.hashCode());
        assertNotEquals(paymentBean1.hashCode(), paymentBean2.hashCode());
    }
}