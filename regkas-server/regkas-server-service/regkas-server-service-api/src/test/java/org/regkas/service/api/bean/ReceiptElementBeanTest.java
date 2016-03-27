package org.regkas.service.api.bean;

import org.boatpos.common.test.JavaBeanTest;
import org.junit.Test;

import java.math.BigDecimal;

public class ReceiptElementBeanTest extends JavaBeanTest<ReceiptElementBean> {

    @Test
    public void testConstructor() {
        new ReceiptElementBean(new ProductBean(), 1, BigDecimal.ZERO);
    }
}