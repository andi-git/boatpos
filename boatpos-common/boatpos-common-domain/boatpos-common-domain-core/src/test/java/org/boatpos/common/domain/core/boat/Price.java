package org.boatpos.common.domain.core.boat;

import org.boatpos.common.domain.api.values.SimpleValueObject;

import java.math.BigDecimal;

/**
 * The price of a {@link Boat} for one hour.
 */
public class Price extends SimpleValueObject<Price, BigDecimal> {

    public Price(BigDecimal value) {
        super(value);
    }

    public Price(String value) {
        this(new BigDecimal(value));
    }
}
