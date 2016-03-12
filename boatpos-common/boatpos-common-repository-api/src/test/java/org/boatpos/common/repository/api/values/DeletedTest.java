package org.boatpos.common.repository.api.values;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeletedTest {

    @Test
    public void test() {
        assertTrue(new Deleted(true).get());
        assertTrue(Deleted.TRUE.get());
        assertFalse(Deleted.FALSE.get());
    }
}