package org.boatpos.service.api.bean;

import com.google.common.base.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Delete a rental.
 */
@SuppressWarnings("unused")
public class RentalDayNumberWrapper extends AbstractBean {

    @NotNull
    @Min(0)
    private Integer dayNumber;

    public RentalDayNumberWrapper() {
    }

    public RentalDayNumberWrapper(Integer dayNumber) {
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
        RentalDayNumberWrapper arrivalBean = (RentalDayNumberWrapper) o;
        return Objects.equal(dayNumber, arrivalBean.dayNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dayNumber);
    }
}
