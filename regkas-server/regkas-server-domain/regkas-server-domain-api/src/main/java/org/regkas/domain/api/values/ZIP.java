package org.regkas.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;

/**
 * The zip-code of an address.
 */
public class ZIP extends SimpleValueObject<ZIP, String> {

    public ZIP(String value) {
        super(value);
    }
}
