package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

/**
 * An email.
 */
public class EMail extends SimpleValueObject<EMail, String> {

    public EMail(String value) {
        super(value);
    }
}
