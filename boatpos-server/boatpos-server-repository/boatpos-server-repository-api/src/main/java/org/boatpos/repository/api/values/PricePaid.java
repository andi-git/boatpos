package org.boatpos.repository.api.values;

import org.boatpos.repository.api.model.Rental;

import java.math.BigDecimal;

/**
 * The paid price to pay for a {@link Rental}.
 */
public class PricePaid extends SimpleValueObject<PricePaid, BigDecimal> {

    public PricePaid(BigDecimal value) {
        super(value);
    }

    public PricePaid(String value) {
        this(new BigDecimal(value));
    }
}
