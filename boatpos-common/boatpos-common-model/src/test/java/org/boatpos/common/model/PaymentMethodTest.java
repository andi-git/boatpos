package org.boatpos.common.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PaymentMethodTest {

    @Test
    public void test() {
        assertEquals(2, PaymentMethod.values().length);
    }

    @Test
    public void testGet() {
        assertEquals(PaymentMethod.Cash, PaymentMethod.get("cash"));
        assertEquals(PaymentMethod.Card, PaymentMethod.get("card"));
        assertEquals(PaymentMethod.Cash, PaymentMethod.get("xxx"));
    }

    @Test
    public void testGetOrNull() {
        assertEquals(PaymentMethod.Cash, PaymentMethod.getOrNull("cash"));
        assertEquals(PaymentMethod.Card, PaymentMethod.getOrNull("card"));
        assertNull(PaymentMethod.getOrNull("xxx"));
    }
}