package org.boatpos.repository.api.values;

import org.boatpos.repository.api.model.Rental;

import java.math.BigDecimal;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The paid price to pay after a {@link Rental}.
 */
public class PricePaidComplete extends SimpleValueObject<PricePaidComplete, BigDecimal> {

    public PricePaidComplete(BigDecimal value) {
        super(value);
    }

    public PricePaidComplete(PricePaidBefore pricePaidBefore, PricePaidAfter pricePaidAfter) {
        super(calculate(pricePaidBefore, pricePaidAfter));
    }

    private static BigDecimal calculate(PricePaidBefore pricePaidBefore, PricePaidAfter pricePaidAfter) {
        checkNotNull(pricePaidBefore, "'pricePaidBefore' must not be null");
        checkNotNull(pricePaidAfter, "'pricePaidAfter' must not be null");
        return pricePaidBefore.get().add(pricePaidAfter.get()).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public PricePaidComplete(String value) {
        this(new BigDecimal(value));
    }
}