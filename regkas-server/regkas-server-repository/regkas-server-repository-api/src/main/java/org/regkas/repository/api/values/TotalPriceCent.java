package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleLongObject;

/**
 * Representation of a total price in cents.
 */
public class TotalPriceCent extends SimpleLongObject<TotalPriceCent> {

    public TotalPriceCent(Long value) {
        super(value);
    }
}
