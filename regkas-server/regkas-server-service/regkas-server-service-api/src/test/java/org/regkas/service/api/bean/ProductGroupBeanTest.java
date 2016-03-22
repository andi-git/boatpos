package org.regkas.service.api.bean;

import org.boatpos.common.test.JavaBeanTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductGroupBeanTest extends JavaBeanTest<ProductGroupBean> {

    @Test
    public void testConstructor() {
        new ProductGroupBean(1L, 1, true, 1, ' ', "", "", "name", 20);
    }
}