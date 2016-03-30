package org.regkas.service.api.bean;

import org.boatpos.common.test.JavaBeanTest;
import org.junit.Test;

import java.math.BigDecimal;

public class SimpleProductBeanTest extends JavaBeanTest<SimpleProductBean> {

    @Test
    public void testConstructor() {
        new SimpleProductBean(1L, 1, true, 1, ' ', "", "", "name", BigDecimal.ONE);
    }
}