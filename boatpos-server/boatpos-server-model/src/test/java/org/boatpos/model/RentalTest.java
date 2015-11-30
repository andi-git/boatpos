package org.boatpos.model;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class RentalTest extends JavaBeanTest<Rental> {

    @Test
    public void testConstructor() {
        new Rental(null, 1, 1, LocalDate.now(), null, LocalDateTime.now(), null, null, false, false, false, null, null, false);
    }

    @Override
    protected Class<Rental> getType() {
        return Rental.class;
    }
}