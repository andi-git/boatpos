package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        return String.valueOf(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(value));
    }

}
