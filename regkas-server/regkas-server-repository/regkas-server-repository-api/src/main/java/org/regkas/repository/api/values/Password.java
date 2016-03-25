package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

/**
 * A password.
 */
public class Password extends SimpleValueObject<Password, String> {

    public Password(String value) {
        super(value);
    }
}
