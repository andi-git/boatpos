package org.regkas.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleBooleanObject;

/**
 * Flag, if something is generic or not.
 */
public class Generic extends SimpleBooleanObject<Generic> {

    public static Generic TRUE = new Generic(true);

    public static Generic FALSE = new Generic(false);

    public Generic(Boolean value) {
        super(value);
    }
}
