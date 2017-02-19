package org.regkas.service.api.bean;

import com.google.common.collect.Lists;
import org.boatpos.common.test.JavaBeanTest;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BillBeanTest extends JavaBeanTest<BillBean> {

    @Test
    public void testConstructor() {
        new BillBean(new CompanyBean(),
                "cashbox",
                "id",
                LocalDateTime.now(),
                BigDecimal.ONE,
                BigDecimal.ONE,
                BigDecimal.ONE,
                BigDecimal.ONE,
                BigDecimal.ONE,
                Lists.newArrayList(),
                BigDecimal.ONE,
                "Standard-Beleg",
                "");
    }
}