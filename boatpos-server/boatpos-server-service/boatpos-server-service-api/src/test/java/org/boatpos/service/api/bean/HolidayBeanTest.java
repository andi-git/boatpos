package org.boatpos.service.api.bean;

import org.boatpos.common.test.JavaBeanTest;
import org.junit.Test;

import java.time.LocalDate;

public class HolidayBeanTest extends JavaBeanTest<HolidayBean> {

    @Test
    public void testConstructor() {
        new HolidayBean(1L, 1, LocalDate.now(), "name");
    }
}