package org.boatpos.common.domain.api.values;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VersionTest {

    @Test
    public void test() {
        assertEquals(1, new Version(1).get().intValue());
    }
}