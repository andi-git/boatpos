package org.regkas.repository.api.values;

import java.time.LocalDateTime;

import org.boatpos.common.repository.api.values.SimpleValueObject;

public class JournalDate extends SimpleValueObject<JournalDate, LocalDateTime> {

    public JournalDate(LocalDateTime value) {
        super(value);
    }

    public JournalDate(int year, int month, int day, int hour, int minute, int second) {
        super(LocalDateTime.of(year, month, day, hour, minute, second));
    }
}
