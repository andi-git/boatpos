package org.boatpos.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;
import org.boatpos.domain.api.model.Boat;

/**
 * Number of available {@link Boat}s.
 */
public class Count extends SimpleValueObject<Count, Integer> {

    public Count(Integer value) {
        super(value);
    }
}
