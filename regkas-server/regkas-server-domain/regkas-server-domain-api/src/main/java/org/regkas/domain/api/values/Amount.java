package org.regkas.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;

/**
 * Representation of an amount.
 */
public class Amount extends SimpleValueObject<Amount, Integer> {

    public Amount(Integer value) {
        super(value);
    }
}
