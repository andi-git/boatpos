package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

import java.math.BigDecimal;

/**
 * Representation of an amount.
 */
public class Amount extends SimpleValueObject<Amount, Integer> {

    public Amount(Integer value) {
        super(value);
    }
}
