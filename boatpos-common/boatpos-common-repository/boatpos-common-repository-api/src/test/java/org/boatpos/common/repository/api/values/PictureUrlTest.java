package org.boatpos.common.repository.api.values;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PictureUrlTest {

    @Test
    public void test() {
        assertEquals("abc", new PictureUrl("abc").get());
    }
}