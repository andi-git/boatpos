package org.boatpos.common.repository.api.values;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleIntegerObjectTest {

    @Test
    public void testAdd() throws Exception {
        MySimpleIntegerObject value3 = new MySimpleIntegerObject(3);
        MySimpleIntegerObject value4 = new MySimpleIntegerObject(4);
        assertEquals(value3, value3);
        assertEquals(value4, value4);
        assertNotEquals(value3, value4);
        MySimpleIntegerObject value6 = value3.add(value3);
        assertEquals(6, value6.get().intValue());
        assertNotEquals(value3, value6);
        assertNotEquals(value4, value6);
        MySimpleIntegerObject value7 = value3.add(value4);
        assertEquals(7, value7.get().intValue());
        assertNotEquals(value3, value7);
        assertNotEquals(value4, value7);
        assertEquals(3, value3.add(new MySimpleIntegerObject(null)).get().intValue());
    }

    @Test
    public void testSubtract() throws Exception {
        MySimpleIntegerObject value6 = new MySimpleIntegerObject(6);
        MySimpleIntegerObject value2 = new MySimpleIntegerObject(2);
        assertEquals(value6, value6);
        assertEquals(value2, value2);
        assertNotEquals(value6, value2);
        MySimpleIntegerObject value4 = value6.subtract(value2);
        assertEquals(4, value4.get().intValue());
        assertNotEquals(value6, value4);
        assertNotEquals(value2, value4);
        assertEquals(6, value6.subtract (new MySimpleIntegerObject(null)).get().intValue());
    }

    private static class MySimpleIntegerObject extends SimpleIntegerObject<MySimpleIntegerObject> {

        public MySimpleIntegerObject(Integer value) {
            super(value);
        }
    }
}