package org.boatpos.repository.api.values;

import org.boatpos.repository.api.model.Rental;

import java.math.BigDecimal;

/**
 * The calculated price to pay for a {@link Rental}.
 */
public class PriceCalculated extends SimpleValueObject<PriceCalculated, BigDecimal> {

    public PriceCalculated(BigDecimal value) {
        super(value);
    }

    public PriceCalculated(String value) {
        this(new BigDecimal(value));
    }
}
