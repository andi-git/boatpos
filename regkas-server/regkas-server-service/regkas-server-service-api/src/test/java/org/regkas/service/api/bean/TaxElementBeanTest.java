package org.regkas.service.api.bean;

import org.boatpos.common.test.JavaBeanTest;
import org.junit.Test;

import java.math.BigDecimal;

public class TaxElementBeanTest extends JavaBeanTest<TaxElementBean> {

    @Test
    public void testConstructor() {
        new TaxElementBean(20, 1, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
    }
}