package org.boatpos.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;
import org.boatpos.domain.api.model.Rental;

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
