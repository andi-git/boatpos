package org.boatpos.model;

import org.boatpos.test.JavaBeanTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RunWith(Arquillian.class)
public class RentalTest extends JavaBeanTest<Rental> {

    @Test
    public void testConstructor() {
        new Rental(null, 1, 1, LocalDate.now(), null, LocalDateTime.now(), null, null, false, false, false, null, null);
    }

    @Override
    protected Class<Rental> getType() {
        return Rental.class;
    }
}