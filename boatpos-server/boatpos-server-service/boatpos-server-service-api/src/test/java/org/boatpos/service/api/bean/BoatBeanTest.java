package org.boatpos.service.api.bean;

import org.boatpos.test.JavaBeanTest;
import org.junit.Test;

import java.math.BigDecimal;

public class BoatBeanTest extends JavaBeanTest<BoatBean> {

    @Override
    protected Class<BoatBean> getType() {
        return BoatBean.class;
    }

    @Test
    public void testConstructor() {
        createBoatE();
        createBoatT();
    }

    public static BoatBean createBoatE() {
        return new BoatBean(1L, 1, "E-Boot", "E", new BigDecimal("16.8"), new BigDecimal("9.5"), new BigDecimal("14.3"), 22, 1, true, "s", "m", "l");
    }

    public static BoatBean createBoatT() {
        return new BoatBean(2L, 1, "Tretboot klein", "T2", new BigDecimal("12.8"), new BigDecimal("7.5"), new BigDecimal("11.3"), 6, 2, true, "s", "m", "l");
    }
}
