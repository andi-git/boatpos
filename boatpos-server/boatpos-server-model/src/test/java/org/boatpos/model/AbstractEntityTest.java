package org.boatpos.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractEntityTest {

    private Boat boat1;
    private Boat boat2;
    private Boat boat3;

    @Before
    public void before() {
        boat1 = new Boat();
        boat1.setId(1L);
        boat2 = new Boat();
        boat2.setId(2L);
        boat3 = new Boat();
        boat3.setId(1L);
    }

    @Test
    public void testEquals() throws Exception {
        assertEquals(boat1, boat1);
        assertEquals(boat2, boat2);
        assertEquals(boat3, boat3);

        assertEquals(boat1, boat3);
        assertEquals(boat3, boat1);
        assertNotEquals(boat1, boat2);
        assertNotEquals(boat2, boat1);
        assertNotEquals(boat2, boat3);
    }

    @Test
    public void testHashCode() throws Exception {
        assertEquals(boat1.hashCode(), boat1.hashCode());
        assertEquals(boat2.hashCode(), boat2.hashCode());
        assertEquals(boat3.hashCode(), boat3.hashCode());

        assertEquals(boat1.hashCode(), boat3.hashCode());
        assertNotEquals(boat1.hashCode(), boat2.hashCode());
        assertNotEquals(boat2.hashCode(), boat3.hashCode());
    }

    @Test
    public void testToString() throws Exception {
        boat1.setName("boot");
        assertEquals("{\"name\":\"boot\",\"id\":1}", boat1.toString());
    }
}