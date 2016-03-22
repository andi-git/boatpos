package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

/**
 * The phone-number.
 */
public class Phone extends SimpleValueObject<Phone, String> {

    public Phone(String value) {
        super(value);
    }
}
