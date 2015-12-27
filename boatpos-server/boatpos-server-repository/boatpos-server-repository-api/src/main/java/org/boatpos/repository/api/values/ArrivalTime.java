package org.boatpos.repository.api.values;

import java.time.LocalDateTime;

/**
 * The timestamp of an arrival.
 */
public class ArrivalTime extends SimpleValueObject<ArrivalTime, LocalDateTime> {

    public ArrivalTime(LocalDateTime value) {
        super(value);
    }

    public ArrivalTime(int year, int month, int day, int hour, int minute) {
        super(LocalDateTime.of(year, month, day, hour, minute));
    }
}
