package org.regkas.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;

/**
 * The username of RK-Online.
 */
public class RkOnlineUsername extends SimpleValueObject<RkOnlineUsername, String> {

    public RkOnlineUsername(String value) {
        super(value);
    }
}
