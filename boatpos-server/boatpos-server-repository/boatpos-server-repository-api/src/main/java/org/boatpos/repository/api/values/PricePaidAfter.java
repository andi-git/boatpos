package org.boatpos.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.boatpos.repository.api.model.Rental;

import java.math.BigDecimal;

/**
 * The paid price to pay after a {@link Rental}.
 */
public class PricePaidAfter extends SimpleValueObject<PricePaidAfter, BigDecimal> {

    public PricePaidAfter(BigDecimal value) {
        super(value);
    }

    public PricePaidAfter(String value) {
        this(new BigDecimal(value));
    }
}
