package org.regkas.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;

/**
 * A password.
 */
public class PasswordPlain extends SimpleValueObject<PasswordPlain, String> {

    public PasswordPlain(String value) {
        super(value);
    }
}
