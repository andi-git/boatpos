package org.regkas.service.api.bean;

import org.boatpos.common.test.JavaBeanTest;
import org.junit.Test;

import java.math.BigDecimal;

public class ProductBeanTest extends JavaBeanTest<ProductBean> {

    @Test
    public void testConstructor() {
        new ProductBean(1L, 1, true, 1, ' ', "", "", "name", BigDecimal.ONE, new ProductGroupBean());
    }
}