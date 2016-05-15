package org.regkas.service.api.bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The period of the time.
 */
public class Period {

    private LocalDateTime start;

    private LocalDateTime end;

    public Period(LocalDateTime start, LocalDateTime end) {
        this.start = LocalDateTime.of(start.getYear(), start.getMonth(), start.getDayOfMonth(), 0, 0, 0, 0);
        this.end = LocalDateTime.of(end.getYear(), end.getMonth(), end.getDayOfMonth(), 23, 59, 59, 999999999);
    }

    /**
     * The day where the period starts. It's accuracy is day, so:
     * <pre>
     * <ul>
     *     <li>hour: 0</li>
     *     <li>minute: 0</li>
     *     <li>second: 0</li>
     *     <li>nano: 0</li>
     * </ul>
     * </pre>
     *
     * @return the start-day of the period
     */
    public LocalDateTime getStartDay() {
        return start;
    }

    /**
     * The day where the period ends. It's accuracy is day, so:
     * <pre>
     * <ul>
     *     <li>hour: 23</li>
     *     <li>minute: 59</li>
     *     <li>second: 59</li>
     *     <li>nano: 999.999.999</li>
     * </ul>
     * </pre>
     *
     * @return the end-day of the period
     */
    public LocalDateTime getEndDay() {
        return end;
    }

    public static Period day(LocalDateTime date) {
        return new Period(date, date);
    }

    public static Period day(LocalDate date) {
        return new Period(LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 0, 0, 0), LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 0, 0, 0));
    }

    public static Period month(LocalDateTime date) {
        return new Period(
                LocalDateTime.from(date).withDayOfMonth(1),
                LocalDateTime.from(date).withDayOfMonth(date.toLocalDate().lengthOfMonth()));
    }

    public static Period year(LocalDateTime date) {
        return new Period(
                LocalDateTime.from(date).withMonth(1).withDayOfMonth(1),
                LocalDateTime.from(date).withMonth(12).withDayOfMonth(date.toLocalDate().lengthOfMonth()));
    }

    public static Period forever() {
        return new Period(LocalDateTime.of(2015, 1, 1, 0, 0, 0, 0), LocalDateTime.of(2100, 12, 31, 23, 59, 59, 999999999));
    }
}
