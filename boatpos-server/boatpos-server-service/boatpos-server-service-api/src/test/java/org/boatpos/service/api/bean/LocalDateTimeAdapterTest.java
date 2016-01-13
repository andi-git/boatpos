package org.boatpos.service.api.bean;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class LocalDateTimeAdapterTest {

    @Test
    public void testUnmarshal() throws Exception {
        assertEquals(LocalDateTime.of(2015, 7, 1, 12, 0, 5), new LocalDateTimeAdapter().unmarshal("2015-07-01T12:00:05"));
    }

    @Test
    public void testMarshal() throws Exception {
        assertEquals("2015-07-01T12:00:05", new LocalDateTimeAdapter().marshal(LocalDateTime.of(2015, 7, 1, 12, 0, 5)));
    }
}