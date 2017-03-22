package org.regkas.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;

/**
 * The city of an address.
 */
public class City extends SimpleValueObject<City, String> {

    public City(String value) {
        super(value);
    }
}
