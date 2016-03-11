package org.boatpos.service.api.bean;

import org.boatpos.common.test.JavaBeanTest;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BoatCountBeanTest extends JavaBeanTest<BoatCountBean> {

    @Override
    protected Class<BoatCountBean> getType() {
        return BoatCountBean.class;
    }

    @Test
    public void testConstructor() {
        new BoatCountBean(1, "", "", 0, 0);
    }

    @Test
    public void testEqualsAndHashCode() {
        BoatCountBean boatCountBean1 = new BoatCountBean(1, "", "", 0, 0);
        BoatCountBean boatCountBean2 = new BoatCountBean(2, "", "", 0, 0);
        assertEquals(boatCountBean1, boatCountBean1);
        assertEquals(boatCountBean2, boatCountBean2);
        assertNotEquals(boatCountBean1, boatCountBean2);
        assertEquals(boatCountBean1.hashCode(), boatCountBean1.hashCode());
        assertEquals(boatCountBean2.hashCode(), boatCountBean2.hashCode());
        assertNotEquals(boatCountBean1.hashCode(), boatCountBean2.hashCode());
    }

}
