package org.boatpos.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.boatpos.repository.api.model.Boat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Number of available {@link Boat}s.
 */
public class Count extends SimpleValueObject<Count, Integer> {

    public Count(Integer value) {
        super(value);
    }
}
