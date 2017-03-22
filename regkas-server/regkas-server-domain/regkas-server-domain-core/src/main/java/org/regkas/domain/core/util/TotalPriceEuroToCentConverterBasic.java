package org.regkas.domain.core.util;

import org.boatpos.common.domain.api.values.SimpleValueObject;
import org.regkas.domain.api.values.TotalPrice;
import org.regkas.domain.api.values.TotalPriceCent;
import org.regkas.domain.api.util.TotalPriceEuroToCentConverter;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;

@ApplicationScoped
public class TotalPriceEuroToCentConverterBasic implements TotalPriceEuroToCentConverter {

    private static final BigDecimal MULTIPLICAND = new BigDecimal("100.00");

    @Override
    public TotalPriceCent convert(TotalPrice totalPrice) {
        if (totalPrice == null) {
            return new TotalPriceCent(0L);
        }
        return new TotalPriceCent(SimpleValueObject.nullSafe(totalPrice).multiply(MULTIPLICAND).longValue());
    }
}
