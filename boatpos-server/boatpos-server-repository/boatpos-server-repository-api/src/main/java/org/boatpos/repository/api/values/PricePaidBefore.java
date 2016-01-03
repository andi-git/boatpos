package org.boatpos.repository.api.values;

import org.boatpos.repository.api.model.Rental;

import java.math.BigDecimal;

/**
 * The paid price to pay before a {@link Rental}.
 */
public class PricePaidBefore extends SimpleValueObject<PricePaidBefore, BigDecimal> {

    public PricePaidBefore(BigDecimal value) {
        super(value);
    }

    public PricePaidBefore(String value) {
        this(new BigDecimal(value));
    }
}
