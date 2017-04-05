package org.boatpos.service.api.bean;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.boatpos.common.service.api.bean.AbstractBean;

import com.google.common.base.Objects;

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

    @NotNull
    private String paymentMethod;

    @NotNull
    private String receiptType;

    public PaymentBean() {}

    public PaymentBean(Integer dayNumber, BigDecimal value, String paymentMethod, String receiptType) {
        this.dayNumber = dayNumber;
        this.value = value;
        this.paymentMethod = paymentMethod;
        this.receiptType = receiptType;
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentBean that = (PaymentBean) o;
        return Objects.equal(dayNumber, that.dayNumber) && Objects.equal(value, that.value) && Objects.equal(paymentMethod, that.paymentMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dayNumber, value, paymentMethod);
    }
}
