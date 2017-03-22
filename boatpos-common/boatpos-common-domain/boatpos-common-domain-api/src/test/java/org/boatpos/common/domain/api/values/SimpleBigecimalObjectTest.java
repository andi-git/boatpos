package org.boatpos.common.domain.api.values;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SimpleBigecimalObjectTest {

    @Test
    public void testAdd() throws Exception {
        MySimpleBigDecimalObject value3 = new MySimpleBigDecimalObject("3.00");
        MySimpleBigDecimalObject value4 = new MySimpleBigDecimalObject("4.00");
        assertEquals(value3, value3);
        assertEquals(value4, value4);
        assertNotEquals(value3, value4);
        MySimpleBigDecimalObject value6 = value3.add(value3);
        assertEquals(new BigDecimal("6.00"), value6.get());
        assertNotEquals(value3, value6);
        assertNotEquals(value4, value6);
        MySimpleBigDecimalObject value7 = value3.add(value4);
        assertEquals(new BigDecimal("7.00"), value7.get());
        assertNotEquals(value3, value7);
        assertNotEquals(value4, value7);
        BigDecimal bdNull = null;
        //noinspection ConstantConditions
        assertEquals(new BigDecimal("3.00"), value3.add(new MySimpleBigDecimalObject(bdNull)).get());
    }

    private static class MySimpleBigDecimalObject extends SimpleBigDecimalObject<MySimpleBigDecimalObject> {

        public MySimpleBigDecimalObject(BigDecimal value) {
            super(value);
        }

        public MySimpleBigDecimalObject(String value) {
            super(value);
        }
    }
}