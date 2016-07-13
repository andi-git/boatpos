package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

import java.math.BigDecimal;

/**
 * The tax as percent.
 */
public class TaxPercent extends SimpleValueObject<TaxPercent, Integer> {

    public TaxPercent(Integer value) {
        super(value);
    }

    public BigDecimal asDivisor() {
        if (get() == null || get() == 0) {
            return new BigDecimal("0.00");
        } else {
            return new BigDecimal("1.00").add(BigDecimal.valueOf(get()).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP));
        }
    }
}
