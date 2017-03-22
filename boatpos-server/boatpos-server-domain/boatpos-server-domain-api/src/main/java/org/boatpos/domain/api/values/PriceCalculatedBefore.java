package org.boatpos.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;
import org.boatpos.domain.api.model.Rental;

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
