package org.boatpos.common.repository.api.values;

import org.boatpos.common.repository.api.model.MasterData;

/**
 * The priority of a {@link MasterData}, this is important for ordering.
 */
public class Priority extends SimpleValueObject<Priority, Integer> {

    public Priority(Integer value) {
        super(value);
    }
}
