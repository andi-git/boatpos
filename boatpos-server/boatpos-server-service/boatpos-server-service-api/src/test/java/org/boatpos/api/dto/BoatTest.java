package org.boatpos.api.dto;

import org.boatpos.test.JavaBeanTest;
import org.junit.Test;

import java.math.BigDecimal;

public class BoatTest extends JavaBeanTest<Boat> {

    @Override
    protected Class<Boat> getType() {
        return Boat.class;
    }

    @Test
    public void testConstructor() {
        createBoatE();
        createBoatT();
    }

    public static Boat createBoatE() {
        return new Boat(1L, 1, "E-Boot", "E", new BigDecimal("16.8"), new BigDecimal("9.5"), new BigDecimal("14.3"), 22, 1);
    }

    public static Boat createBoatT() {
        return new Boat(2L, 1, "Tretboot klein", "T2", new BigDecimal("12.8"), new BigDecimal("7.5"), new BigDecimal("11.3"), 6, 2);
    }
}
