package org.regkas.service.core.util;

import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.regkas.repository.api.values.TotalPrice;
import org.regkas.repository.api.values.TotalPriceCent;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;

@ApplicationScoped
public class TotalPriceEuroToCentConverter {

    private static final BigDecimal MULTIPLICAND = new BigDecimal("100.00");

    public TotalPriceCent convert(TotalPrice totalPrice) {
        if (totalPrice == null) {
            return new TotalPriceCent(0);
        }
        return new TotalPriceCent(SimpleValueObject.nullSafe(totalPrice).multiply(MULTIPLICAND).intValue());
    }
}