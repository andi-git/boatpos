package org.boatpos.common.model;

import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class LocalDateTimeConverterTest {

    private LocalDateTimeConverter localDateTimeConverter;

    @Before
    public void before() {
        localDateTimeConverter = new LocalDateTimeConverter();
    }


    @Test
    public void testConvertToDatabaseColumn() throws Exception {
        Timestamp timestamp = localDateTimeConverter.convertToDatabaseColumn(LocalDateTime.of(2015, 11, 30, 0, 36, 1, 1));
        assertEquals("2015-11-30 00:36:01.000000001", timestamp.toString());
    }

    @Test
    public void testConvertToEntityAttribute() throws Exception {
        LocalDateTime localDateTime = localDateTimeConverter.convertToEntityAttribute(Timestamp.valueOf("2015-11-30 00:36:01.000000001"));
        assertEquals(LocalDateTime.of(2015, 11, 30, 0, 36, 1, 1), localDateTime);
    }
}
