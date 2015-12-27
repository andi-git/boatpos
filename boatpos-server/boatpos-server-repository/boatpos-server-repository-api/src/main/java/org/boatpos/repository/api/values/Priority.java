package org.boatpos.repository.api.values;

import org.boatpos.repository.api.model.MasterData;

import javax.validation.constraints.NotNull;

/**
 * The priority of a {@link MasterData}, this is important for ordering.
 */
public class Priority extends SimpleValueObject<Priority, Integer> {

    public Priority(Integer value) {
        super(value);
    }
}
