package org.boatpos.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.boatpos.repository.api.model.Rental;

/**
 * The id of a specific day of a {@link Rental}.
 */
public class DayId extends SimpleValueObject<DayId, Integer> {

    public DayId(Integer value) {
        super(value);
    }
}
