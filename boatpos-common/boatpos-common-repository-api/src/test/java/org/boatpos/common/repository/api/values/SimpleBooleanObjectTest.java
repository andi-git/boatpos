package org.boatpos.common.repository.api.values;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleBooleanObjectTest {

    @Test
    public void testGet() throws Exception {
        assertTrue(new MySimpleBooleanObject(true).get());
        assertFalse(new MySimpleBooleanObject(false).get());
        assertFalse(new MySimpleBooleanObject(null).get());
    }

    private static class MySimpleBooleanObject extends SimpleBooleanObject<SimpleBooleanObject> {

        public MySimpleBooleanObject(Boolean value) {
            super(value);
        }
    }
}