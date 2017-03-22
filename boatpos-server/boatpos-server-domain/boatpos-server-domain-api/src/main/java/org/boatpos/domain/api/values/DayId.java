package org.boatpos.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;
import org.boatpos.domain.api.model.Rental;

/**
 * The id of a specific day of a {@link Rental}.
 */
public class DayId extends SimpleValueObject<DayId, Integer> {

    public DayId(Integer value) {
        super(value);
    }
}
