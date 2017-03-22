package org.regkas.domain.api.values;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.boatpos.common.domain.api.values.SimpleValueObject;

public class JournalDate extends SimpleValueObject<JournalDate, LocalDateTime> {

    public JournalDate(LocalDateTime value) {
        super(value);
    }

    public JournalDate(int year, int month, int day, int hour, int minute, int second) {
        super(LocalDateTime.of(year, month, day, hour, minute, second));
    }

    @Override
    public String asStringToBeSigned() {
        return String.valueOf(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(value));
    }

}
