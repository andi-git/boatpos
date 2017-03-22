package org.boatpos.common.repository.api.values;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PictureUrThumblTest {

    @Test
    public void test() {
        assertEquals("abc", new PictureUrlThumb("abc").get());
    }
}