package org.boatpos.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.boatpos.repository.api.model.Boat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
