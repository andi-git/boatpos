package org.regkas.service.api.bean;

import org.boatpos.common.test.JavaBeanTest;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class IncomeBeanTest extends JavaBeanTest<IncomeBean> {

    @Test
    public void testConstructor() {
        new IncomeBean(LocalDate.now(), LocalDate.now(), new ArrayList<>(), BigDecimal.ONE, new ArrayList<>());
    }
}