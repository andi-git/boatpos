package org.boatpos.repository.api.values;

import org.boatpos.repository.api.model.Rental;

import java.math.BigDecimal;

/**
 * The calculated price to pay before a {@link Rental}.
 */
public class PriceCalculatedBefore extends SimpleValueObject<PriceCalculatedBefore, BigDecimal> {

    public PriceCalculatedBefore(BigDecimal value) {
        super(value);
    }

    public PriceCalculatedBefore(String value) {
        this(new BigDecimal(value));
    }
}
