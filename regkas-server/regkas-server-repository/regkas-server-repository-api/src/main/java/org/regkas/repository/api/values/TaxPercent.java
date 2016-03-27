package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

/**
 * The tax as percent.
 */
public class TaxPercent extends SimpleValueObject<TaxPercent, Integer> {

    public TaxPercent(Integer value) {
        super(value);
    }
}
