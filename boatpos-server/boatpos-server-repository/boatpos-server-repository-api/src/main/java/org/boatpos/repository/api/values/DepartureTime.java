package org.boatpos.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

import java.time.LocalDateTime;

/**
 * The timestamp of a departure.
 */
public class DepartureTime extends SimpleValueObject<DepartureTime, LocalDateTime> {

    public DepartureTime(LocalDateTime value) {
        super(value);
    }

    public DepartureTime(int year, int month, int day, int hour, int minute) {
        super(LocalDateTime.of(year, month, day, hour, minute));
    }
}
