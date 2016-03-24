package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

/**
 * The country of an address.
 */
public class Country extends SimpleValueObject<Country, String> {

    public Country(String value) {
        super(value);
    }
}
