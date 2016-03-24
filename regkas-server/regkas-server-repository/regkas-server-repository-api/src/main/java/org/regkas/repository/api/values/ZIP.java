package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

/**
 * The zip-code of an address.
 */
public class ZIP extends SimpleValueObject<ZIP, String> {

    public ZIP(String value) {
        super(value);
    }
}
