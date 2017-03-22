package org.boatpos.common.domain.api.values;

import org.boatpos.common.domain.api.model.MasterData;

/**
 * Flag, if a {@link MasterData} is enabled or not.
 */
public class Enabled extends SimpleBooleanObject<Enabled> {

    public static Enabled TRUE = new Enabled(true);

    public static Enabled FALSE = new Enabled(false);

    public Enabled(Boolean value) {
        super(value);
    }
}
