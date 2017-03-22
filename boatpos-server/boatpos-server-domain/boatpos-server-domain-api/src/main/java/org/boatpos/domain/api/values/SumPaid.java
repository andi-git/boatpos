package org.boatpos.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;

import java.math.BigDecimal;

/**
 * The sum of payments.
 */
public class SumPaid extends SimpleValueObject<SumPaid, BigDecimal> {

    public SumPaid(BigDecimal value) {
        super(value);
    }

    public SumPaid(String value) {
        this(new BigDecimal(value));
    }
}
