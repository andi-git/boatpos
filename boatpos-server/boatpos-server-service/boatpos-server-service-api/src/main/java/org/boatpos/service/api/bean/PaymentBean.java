package org.boatpos.service.api.bean;

import com.google.common.base.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * The payment of a rental.
 */
@SuppressWarnings("unused")
public class PaymentBean extends AbstractBean {

    @NotNull
    @Min(0)
    private Integer dayNumber;

    @NotNull
    @Min(0)
    private BigDecimal value;

    public PaymentBean() {
    }

    public PaymentBean(Integer dayNumber, BigDecimal value) {
        this.dayNumber = dayNumber;
        this.value = value;
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentBean that = (PaymentBean) o;
        return Objects.equal(dayNumber, that.dayNumber) &&
                Objects.equal(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dayNumber, value);
    }
}
