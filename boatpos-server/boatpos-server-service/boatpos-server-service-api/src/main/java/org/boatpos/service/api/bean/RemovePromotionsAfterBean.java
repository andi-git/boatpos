package org.boatpos.service.api.bean;

import com.google.common.base.Objects;
import org.boatpos.common.service.api.bean.AbstractBean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * To remove all promotions-after of a rental.
 */
@SuppressWarnings("unused")
public class RemovePromotionsAfterBean extends AbstractBean {

    @NotNull
    @Min(0)
    private Integer dayNumber;

    public RemovePromotionsAfterBean() {
    }

    public RemovePromotionsAfterBean(Integer dayNumber) {
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
        RemovePromotionsAfterBean arrivalBean = (RemovePromotionsAfterBean) o;
        return Objects.equal(dayNumber, arrivalBean.dayNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dayNumber);
    }
}
