package org.boatpos.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PaymentMethodTest {

    @Test
    public void test() {
        assertEquals(2, PaymentMethod.values().length);
    }
}