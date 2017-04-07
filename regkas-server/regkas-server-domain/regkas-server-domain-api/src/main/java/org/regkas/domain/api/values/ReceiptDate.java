package org.regkas.domain.api.values;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.boatpos.common.domain.api.values.SimpleValueObject;

/**
 * The date of a receipt.
 */
public class ReceiptDate extends SimpleValueObject<ReceiptDate, LocalDateTime> {

    public ReceiptDate(LocalDateTime value) {
        super(value);
    }

    public ReceiptDate(int year, int month, int day, int hour, int minute, int second) {
        super(LocalDateTime.of(year, month, day, hour, minute, second));
    }

    @Override
    public String asStringToBeSigned() {
        return value.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

}
