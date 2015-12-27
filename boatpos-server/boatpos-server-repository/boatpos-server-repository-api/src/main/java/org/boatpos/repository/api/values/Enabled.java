package org.boatpos.repository.api.values;

import org.boatpos.repository.api.model.MasterData;

/**
 * Flag, if a {@link MasterData} is enabled or not.
 */
public class Enabled extends SimpleValueObject<Enabled, Boolean> {

    public static Enabled TRUE = new Enabled(true);

    public static Enabled FALSE = new Enabled(false);

    public Enabled(Boolean value) {
        super(value);
    }
}
