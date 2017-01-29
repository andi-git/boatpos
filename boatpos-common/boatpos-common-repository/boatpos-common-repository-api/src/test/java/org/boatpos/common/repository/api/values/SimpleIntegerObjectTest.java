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

    private static class MySimpleIntegerObject extends SimpleIntegerObject<MySimpleIntegerObject> {

        public MySimpleIntegerObject(Integer value) {
            super(value);
        }
    }
}