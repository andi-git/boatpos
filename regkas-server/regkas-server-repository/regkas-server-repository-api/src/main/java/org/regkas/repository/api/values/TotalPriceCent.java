package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleIntegerObject;

/**
 * Representation of a total price in cents.
 */
public class TotalPriceCent extends SimpleIntegerObject<TotalPriceCent> {

    public TotalPriceCent(Integer value) {
        super(value);
    }
}
