package org.boatpos.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.boatpos.repository.api.model.Boat;

import java.math.BigDecimal;

/**
 * The price of a {@link Boat} for 30 minutes.
 */
public class PriceThirtyMinutes extends SimpleValueObject<PriceThirtyMinutes, BigDecimal> {

    public PriceThirtyMinutes(BigDecimal value) {
        super(value);
    }

    public PriceThirtyMinutes(String value) {
        this(new BigDecimal(value));
    }
}
