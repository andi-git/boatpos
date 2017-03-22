package org.boatpos.common.repository.api.values;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleValueObjectTest {

    private MySimpleValueObject mySimpleValueObject1 = new MySimpleValueObject("string1");
    private MySimpleValueObject mySimpleValueObject2 = new MySimpleValueObject("string2");
    private MySimpleValueObject mySimpleValueObject3 = new MySimpleValueObject("string1");
    private MySimpleValueObject mySimpleValueObjectNull = new MySimpleValueObject(null);

    @Test
    public void testGet() throws Exception {
        assertEquals("string1", mySimpleValueObject1.get());
    }

    @Test
    public void testOrElseGet() throws Exception {
        assertEquals("test", mySimpleValueObjectNull.orElseGet("test"));
    }

    @Test
    public void testClone() throws Exception {
        assertEquals("string1", mySimpleValueObject1.clone().get());
    }

    @Test
    public void testEquals() throws Exception {
        assertTrue(mySimpleValueObject1.equals(mySimpleValueObject3));
        assertFalse(mySimpleValueObject1.equals(mySimpleValueObject2));
        assertFalse(mySimpleValueObject2.equals(mySimpleValueObject3));
    }

    @Test
    public void testHashCode() throws Exception {
        assertEquals(mySimpleValueObject1.hashCode(), mySimpleValueObject3.hashCode());
        assertNotEquals(mySimpleValueObject1.hashCode(), mySimpleValueObject2.hashCode());
        assertNotEquals(mySimpleValueObject2.hashCode(), mySimpleValueObject3.hashCode());
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("org.boatpos.common.repository.api.values.SimpleValueObjectTest$MySimpleValueObject: string1", mySimpleValueObject1.toString());
    }

    @Test
    public void testCompareTo() throws Exception {
        assertEquals(0, mySimpleValueObject1.compareTo(mySimpleValueObject3));
        assertEquals(-1, mySimpleValueObject1.compareTo(mySimpleValueObject2));
        assertEquals(1, mySimpleValueObject2.compareTo(mySimpleValueObject1));
    }

    @Test
    public void testNullSafe() throws Exception {
        assertEquals("string1", SimpleValueObject.nullSafe(mySimpleValueObject1));
        assertNull(SimpleValueObject.nullSafe(null));
    }

    @Test
    public void testIsPresent() throws Exception {
        assertTrue(mySimpleValueObject1.isPresent());
        assertFalse(mySimpleValueObjectNull.isPresent());
    }

    private static class MySimpleValueObject extends SimpleValueObject<MySimpleValueObject, String> {

        public MySimpleValueObject(String value) {
            super(value);
        }
    }
}