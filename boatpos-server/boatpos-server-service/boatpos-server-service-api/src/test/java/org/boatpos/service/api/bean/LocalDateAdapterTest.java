package org.boatpos.service.api.bean;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class LocalDateAdapterTest {

    @Test
    public void testUnmarshal() throws Exception {
        assertEquals(LocalDate.of(2015, 7, 1), new LocalDateAdapter().unmarshal("2015-07-01"));
    }

    @Test
    public void testMarshal() throws Exception {
        assertEquals("2015-07-01", new LocalDateAdapter().marshal(LocalDate.of(2015, 7, 1)));
    }
}