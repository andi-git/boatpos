package org.boatpos.model;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

@RunWith(Arquillian.class)
public class BoatTest extends JavaBeanTest<Boat> {

    @Test
    public void testConstructor() {
        new Boat("boat", "B", new BigDecimal("10.1"), new BigDecimal("6.1"), new BigDecimal("8.1"), 20);
    }

    @Override
    protected Class<Boat> getType() {
        return Boat.class;
    }
}