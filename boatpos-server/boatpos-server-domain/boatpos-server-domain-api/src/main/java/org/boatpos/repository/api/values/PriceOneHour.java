package org.boatpos.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.boatpos.repository.api.model.Boat;

import java.math.BigDecimal;

/**
 * The price of a {@link Boat} for one hour.
 */
public class PriceOneHour extends SimpleValueObject<PriceOneHour, BigDecimal> {

    public PriceOneHour(BigDecimal value) {
        super(value);
    }

    public PriceOneHour(String value) {
        this(new BigDecimal(value));
    }
}