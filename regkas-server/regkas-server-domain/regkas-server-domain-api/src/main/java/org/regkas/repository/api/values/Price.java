package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

import java.math.BigDecimal;

/**
 * Representation of a price.
 */
public class Price extends SimpleValueObject<Price, BigDecimal> {

    public Price(BigDecimal value) {
        super(value);
    }
}
