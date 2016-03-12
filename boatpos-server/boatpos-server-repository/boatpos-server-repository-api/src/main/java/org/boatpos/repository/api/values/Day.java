package org.boatpos.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

import java.time.LocalDate;

/**
 * A day.
 */
public class Day extends SimpleValueObject<Day, LocalDate> {

    public Day(LocalDate value) {
        super(value);
    }

    public Day(int year, int month, int day) {
        super(LocalDate.of(year, month, day));
    }
}
