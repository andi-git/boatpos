package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.model.MasterData;
import org.boatpos.common.repository.api.values.SimpleBooleanObject;

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
