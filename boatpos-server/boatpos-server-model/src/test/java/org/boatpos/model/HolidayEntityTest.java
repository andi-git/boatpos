package org.boatpos.model;

import org.boatpos.common.test.JavaBeanTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;

@RunWith(Arquillian.class)
public class HolidayEntityTest extends JavaBeanTest<HolidayEntity> {

    @Test
    public void testConstructor() {
        new HolidayEntity();
        new HolidayEntity(1L, 1, LocalDate.now(), "name");
    }
}