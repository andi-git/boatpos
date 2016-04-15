package org.regkas.service.api.bean;

import org.boatpos.common.test.JavaBeanTest;
import org.junit.Test;

import java.math.BigDecimal;

public class ProductGroupIncomeBeanTest extends JavaBeanTest<ProductGroupIncomeBean> {

    @Test
    public void testConstructor() {
        new ProductGroupIncomeBean("", BigDecimal.ONE, 0, 0);
    }
}