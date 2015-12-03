package org.boatpos.api.dto;

import org.boatpos.test.JavaBeanTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ArrivalTest extends JavaBeanTest<Arrival> {

    @Override
    protected Class<Arrival> getType() {
        return Arrival.class;
    }

    @Test
    public void testConstructor() {
        new Arrival(1);
    }

    @Test
    public void testEqualsAndHashCode() {
        Arrival arrival1 = new Arrival(1);
        Arrival arrival2 = new Arrival(2);
        assertEquals(arrival1, arrival1);
        assertEquals(arrival2, arrival2);
        assertNotEquals(arrival1, arrival2);
        assertEquals(arrival1.hashCode(), arrival1.hashCode());
        assertEquals(arrival2.hashCode(), arrival2.hashCode());
        assertNotEquals(arrival1.hashCode(), arrival2.hashCode());
    }
}