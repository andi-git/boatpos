package org.boatpos.model;

import org.boatpos.common.test.JavaBeanTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;

@RunWith(Arquillian.class)
public class PrinterEntityTest extends JavaBeanTest<PrinterEntity> {

    @Test
    public void testConstructor() {
        new PrinterEntity(1L, 1, "0.0.0.0", 1);
    }
}