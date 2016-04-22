package org.boatpos.service.api.bean;

import org.boatpos.common.test.JavaBeanTest;
import org.junit.Test;

import java.time.LocalDate;

public class PrinterBeanTest extends JavaBeanTest<PrinterBean> {

    @Test
    public void testConstructor() {
        new PrinterBean(1L, 1, "192.168.0.11");
    }
}