package org.regkas.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;

import java.math.BigDecimal;

/**
 * Representation of a price.
 */
public class Price extends SimpleValueObject<Price, BigDecimal> {

    public Price(BigDecimal value) {
        super(value);
    }
}
