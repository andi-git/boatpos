package org.regkas.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class TimeTypeTest {

    @Test
    public void test() {
        assertEquals(3, TimeType.values().length);
    }

    @Test
    public void testGet() {
        assertEquals(TimeType.Current, TimeType.get("current"));
        assertEquals(TimeType.Month, TimeType.get("month"));
        assertEquals(TimeType.Current, TimeType.get("xxx"));
    }

    @Test
    public void testGetOrNull() {
        //noinspection OptionalGetWithoutIsPresent
        assertEquals(TimeType.Current, TimeType.getOrNull("current").get());
        //noinspection OptionalGetWithoutIsPresent
        assertEquals(TimeType.Month, TimeType.getOrNull("month").get());
        assertFalse(TimeType.getOrNull("xxx").isPresent());
    }
}