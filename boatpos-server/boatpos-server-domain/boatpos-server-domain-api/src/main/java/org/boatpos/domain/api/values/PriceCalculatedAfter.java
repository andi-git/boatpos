package org.boatpos.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;
import org.boatpos.domain.api.model.Rental;

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
