package org.regkas.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;

/**
 * The country of an address.
 */
public class Country extends SimpleValueObject<Country, String> {

    public Country(String value) {
        super(value);
    }
}
