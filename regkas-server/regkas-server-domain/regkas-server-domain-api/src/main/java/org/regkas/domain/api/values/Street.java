package org.regkas.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;

/**
 * The street of an address.
 */
public class Street extends SimpleValueObject<Street, String> {

    public Street(String value) {
        super(value);
    }
}
