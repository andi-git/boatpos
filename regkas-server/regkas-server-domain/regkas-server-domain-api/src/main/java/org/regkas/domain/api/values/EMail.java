package org.regkas.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;

/**
 * An email.
 */
public class EMail extends SimpleValueObject<EMail, String> {

    public EMail(String value) {
        super(value);
    }
}
