package org.regkas.repository.core.model.util;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.model.util.TotalPriceEuroToCentConverter;
import org.regkas.repository.api.values.TotalPrice;

import javax.inject.Inject;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class TotalPriceEuroToCentConverterBasicTest {

    @Inject
    private TotalPriceEuroToCentConverter totalPriceEuroToCentConverter;

    @Test
    public void testConvert() throws Exception {
        assertEquals(1200, totalPriceEuroToCentConverter.convert(new TotalPrice("12.00")).get().intValue());
        assertEquals(1212, totalPriceEuroToCentConverter.convert(new TotalPrice("12.12")).get().intValue());
        assertEquals(1212, totalPriceEuroToCentConverter.convert(new TotalPrice("12.129")).get().intValue());
        assertEquals(0, totalPriceEuroToCentConverter.convert(new TotalPrice("0")).get().intValue());
        assertEquals(0, totalPriceEuroToCentConverter.convert(null).get().intValue());
        BigDecimal bdNull = null;
        assertEquals(0, totalPriceEuroToCentConverter.convert(new TotalPrice(bdNull)).get().intValue());
    }
}