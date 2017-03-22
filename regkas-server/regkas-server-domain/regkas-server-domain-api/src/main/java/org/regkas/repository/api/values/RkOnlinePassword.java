package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

/**
 * The password of RK-Online.
 */
public class RkOnlinePassword extends SimpleValueObject<RkOnlinePassword, String> {

    public RkOnlinePassword(String value) {
        super(value);
    }
}
