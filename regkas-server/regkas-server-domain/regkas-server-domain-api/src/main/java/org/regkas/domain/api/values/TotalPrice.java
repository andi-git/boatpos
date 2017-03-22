package org.regkas.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleBigDecimalObject;

import java.math.BigDecimal;

/**
 * Representation of a total-price (a sum).
 */
public class TotalPrice extends SimpleBigDecimalObject<TotalPrice> {

    public static final TotalPrice ZERO = new TotalPrice("0.00");

    public TotalPrice(BigDecimal value) {
        super(value);
    }

    public TotalPrice(String value) {
        super(value);
    }
}
