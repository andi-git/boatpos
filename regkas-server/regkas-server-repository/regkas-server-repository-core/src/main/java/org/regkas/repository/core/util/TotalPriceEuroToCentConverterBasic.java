package org.regkas.repository.core.util;

import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.regkas.repository.api.values.TotalPrice;
import org.regkas.repository.api.values.TotalPriceCent;
import org.regkas.repository.api.util.TotalPriceEuroToCentConverter;

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
