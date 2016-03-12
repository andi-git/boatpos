package org.boatpos.common.repository.api.values;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EnabledTest {

    @Test
    public void test() {
        assertTrue(new Enabled(true).get());
        assertTrue(Enabled.TRUE.get());
        assertFalse(Enabled.FALSE.get());
    }
}