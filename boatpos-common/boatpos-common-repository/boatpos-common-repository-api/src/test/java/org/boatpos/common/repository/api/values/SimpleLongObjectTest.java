package org.boatpos.common.repository.api.values;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SimpleLongObjectTest {

    @Test
    public void testAdd() throws Exception {
        MySimpleLongObject value3 = new MySimpleLongObject(3L);
        MySimpleLongObject value4 = new MySimpleLongObject(4L);
        assertEquals(value3, value3);
        assertEquals(value4, value4);
        assertNotEquals(value3, value4);
        MySimpleLongObject value6 = value3.add(value3);
        assertEquals(6, value6.get().longValue());
        assertNotEquals(value3, value6);
        assertNotEquals(value4, value6);
        MySimpleLongObject value7 = value3.add(value4);
        assertEquals(7, value7.get().longValue());
        assertNotEquals(value3, value7);
        assertNotEquals(value4, value7);
        assertEquals(3, value3.add(new MySimpleLongObject(null)).get().longValue());
    }

    @Test
    public void testSubtract() throws Exception {
        MySimpleLongObject value6 = new MySimpleLongObject(6L);
        MySimpleLongObject value2 = new MySimpleLongObject(2L);
        assertEquals(value6, value6);
        assertEquals(value2, value2);
        assertNotEquals(value6, value2);
        MySimpleLongObject value4 = value6.subtract(value2);
        assertEquals(4, value4.get().longValue());
        assertNotEquals(value6, value4);
        assertNotEquals(value2, value4);
        assertEquals(6, value6.subtract (new MySimpleLongObject(null)).get().longValue());
    }

    private static class MySimpleLongObject extends SimpleLongObject<MySimpleLongObject> {

        public MySimpleLongObject(Long value) {
            super(value);
        }
    }
}