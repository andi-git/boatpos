package org.boatpos.model.util;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class LocalDateConverterTest {

    private LocalDateConverter localDateConverter;

    @Before
    public void before() {
        localDateConverter = new LocalDateConverter();
    }

    @Test
    public void testConvertToDatabaseColumn() throws Exception {
        Date date = localDateConverter.convertToDatabaseColumn(LocalDate.of(2015, 11, 30));
        assertEquals("2015-11-30", date.toString());
    }

    @Test
    public void testConvertToEntityAttribute() throws Exception {
        LocalDate localDate = localDateConverter.convertToEntityAttribute(Date.valueOf("2015-11-30"));
        assertEquals(LocalDate.of(2015, 11, 30), localDate);
    }
}