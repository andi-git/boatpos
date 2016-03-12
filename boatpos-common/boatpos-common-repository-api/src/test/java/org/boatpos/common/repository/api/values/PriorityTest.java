package org.boatpos.common.repository.api.values;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PriorityTest {

    @Test
    public void test() {
        assertEquals(1, new Priority(1).get().intValue());
    }
}