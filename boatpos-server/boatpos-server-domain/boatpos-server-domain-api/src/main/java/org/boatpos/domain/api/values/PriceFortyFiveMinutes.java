package org.boatpos.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;
import org.boatpos.domain.api.model.Boat;

import java.math.BigDecimal;

/**
 * The price of a {@link Boat} for 45 minutes.
 */
public class PriceFortyFiveMinutes extends SimpleValueObject<PriceFortyFiveMinutes, BigDecimal> {

    public PriceFortyFiveMinutes(BigDecimal value) {
        super(value);
    }

    public PriceFortyFiveMinutes(String value) {
        this(new BigDecimal(value));
    }
}
