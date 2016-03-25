package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

/**
 * The city of an address.
 */
public class City extends SimpleValueObject<City, String> {

    public City(String value) {
        super(value);
    }
}
