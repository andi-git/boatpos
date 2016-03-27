package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

import java.math.BigDecimal;

/**
 * Representation of a total-price (a sum).
 */
public class TotalPrice extends SimpleValueObject<TotalPrice, BigDecimal> {

    public TotalPrice(BigDecimal value) {
        super(value);
    }
}
