package org.boatpos.repository.api.values;

import org.boatpos.repository.api.model.Boat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
