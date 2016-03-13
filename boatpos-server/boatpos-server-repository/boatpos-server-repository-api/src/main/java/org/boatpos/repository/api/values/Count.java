package org.boatpos.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.boatpos.repository.api.model.Boat;

/**
 * Number of available {@link Boat}s.
 */
public class Count extends SimpleValueObject<Count, Integer> {

    public Count(Integer value) {
        super(value);
    }
}
