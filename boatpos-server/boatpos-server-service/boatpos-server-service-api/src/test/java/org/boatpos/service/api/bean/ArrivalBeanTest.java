package org.boatpos.service.api.bean;

import org.boatpos.test.JavaBeanTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ArrivalBeanTest extends JavaBeanTest<ArrivalBean> {

    @Override
    protected Class<ArrivalBean> getType() {
        return ArrivalBean.class;
    }

    @Test
    public void testConstructor() {
        new ArrivalBean(1);
    }

    @Test
    public void testEqualsAndHashCode() {
        ArrivalBean arrivalBean1 = new ArrivalBean(1);
        ArrivalBean arrivalBean2 = new ArrivalBean(2);
        assertEquals(arrivalBean1, arrivalBean1);
        assertEquals(arrivalBean2, arrivalBean2);
        assertNotEquals(arrivalBean1, arrivalBean2);
        assertEquals(arrivalBean1.hashCode(), arrivalBean1.hashCode());
        assertEquals(arrivalBean2.hashCode(), arrivalBean2.hashCode());
        assertNotEquals(arrivalBean1.hashCode(), arrivalBean2.hashCode());
    }
}