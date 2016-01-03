package org.boatpos.repository.api.values;

import org.boatpos.repository.api.model.Rental;

import java.math.BigDecimal;

/**
 * The calculated price to pay for a {@link Rental}.
 */
public class PriceCalculatedAfter extends SimpleValueObject<PriceCalculatedAfter, BigDecimal> {

    public PriceCalculatedAfter(BigDecimal value) {
        super(value);
    }

    public PriceCalculatedAfter(String value) {
        this(new BigDecimal(value));
    }
}
