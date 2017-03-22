package org.regkas.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;

/**
 * The password of RK-Online.
 */
public class RkOnlinePassword extends SimpleValueObject<RkOnlinePassword, String> {

    public RkOnlinePassword(String value) {
        super(value);
    }
}
