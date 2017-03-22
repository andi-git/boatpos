package org.regkas.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;

/**
 * The phone-number.
 */
public class Phone extends SimpleValueObject<Phone, String> {

    public Phone(String value) {
        super(value);
    }
}
