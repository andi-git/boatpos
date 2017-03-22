package org.boatpos.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;
import org.boatpos.domain.api.model.Rental;

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
