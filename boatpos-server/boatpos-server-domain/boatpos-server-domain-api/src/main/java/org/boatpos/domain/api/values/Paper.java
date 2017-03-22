package org.boatpos.domain.api.values;

import org.boatpos.common.domain.api.model.MasterData;
import org.boatpos.common.domain.api.values.SimpleBooleanObject;

/**
 * Flag, if a {@link MasterData} is enabled or not.
 */
public class Paper extends SimpleBooleanObject<Paper> {

    public static Paper TRUE = new Paper(true);

    public static Paper FALSE = new Paper(false);

    public Paper(Boolean value) {
        super(value);
    }
}
