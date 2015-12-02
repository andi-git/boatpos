package org.boatpos.api.dto;

import com.google.common.base.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * The arrival of a rental.
 */
@SuppressWarnings("unused")
public class Arrival extends AbstractDto {

    @NotNull
    @Min(0)
    private Integer dayNumber;

    public Arrival() {
    }

    public Arrival(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arrival arrival = (Arrival) o;
        return Objects.equal(dayNumber, arrival.dayNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dayNumber);
    }
}
