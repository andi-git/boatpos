package org.boatpos.repository.api.values;

import org.boatpos.repository.api.model.MasterData;

/**
 * Flag, if a {@link MasterData} is enabled or not.
 */
public class Paper extends SimpleValueObject<Paper, Boolean> {

    public static Paper TRUE = new Paper(true);

    public static Paper FALSE = new Paper(false);

    public Paper(Boolean value) {
        super(value);
    }
}
