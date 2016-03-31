package org.regkas.service.api.bean;

import org.boatpos.common.test.JavaBeanTest;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BillTaxSetElementBeanTest extends JavaBeanTest<BillTaxSetElementBean> {

    @Test
    public void testConstructor() {
        new BillTaxSetElementBean("name", 20, 1, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE);
    }
}