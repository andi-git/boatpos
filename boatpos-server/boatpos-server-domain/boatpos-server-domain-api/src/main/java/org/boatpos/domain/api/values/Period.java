package org.boatpos.domain.api.values;

import java.time.LocalDate;

/**
 * The period of the time.
 */
public class Period {

    private LocalDate start;

    private LocalDate end;

    public Period(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public static Period day(LocalDate date) {
        return new Period(date, date);
    }

    public static Period month(LocalDate date) {
        return new Period(
                LocalDate.from(date).withDayOfMonth(1),
                LocalDate.from(date).withDayOfMonth(date.lengthOfMonth()));
    }

    public static Period year(LocalDate date) {
        return new Period(
                LocalDate.from(date).withMonth(1).withDayOfMonth(1),
                LocalDate.from(date).withMonth(12).withDayOfMonth(date.lengthOfMonth()));
    }

    @Override
    public String toString() {
        return "Period{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
