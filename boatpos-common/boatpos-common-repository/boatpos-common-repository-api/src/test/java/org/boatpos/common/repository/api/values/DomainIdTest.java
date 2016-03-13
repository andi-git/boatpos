package org.boatpos.common.repository.api.values;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DomainIdTest {

    @Test
    public void test() {
        assertEquals(1L, new DomainId(1L).get().longValue());
    }
}