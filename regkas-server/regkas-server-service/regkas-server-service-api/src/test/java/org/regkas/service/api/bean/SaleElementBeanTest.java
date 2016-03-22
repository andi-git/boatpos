package org.regkas.service.api.bean;

import org.boatpos.common.test.JavaBeanTest;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class SaleElementBeanTest extends JavaBeanTest<SaleElementBean> {

    @Test
    public void testConstructor() {
        new SaleElementBean(new ProductGroupBean(), BigDecimal.ZERO);
    }
}