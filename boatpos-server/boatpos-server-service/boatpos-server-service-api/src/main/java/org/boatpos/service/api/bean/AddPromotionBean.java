package org.boatpos.service.api.bean;

import com.google.common.base.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * The payment of a rental.
 */
@SuppressWarnings("unused")
public class AddPromotionBean extends AbstractBean {

    @NotNull
    @Min(0)
    private Integer dayNumber;

    @NotNull
    @Min(0)
    private Long promotionId;

    public AddPromotionBean() {
    }

    public AddPromotionBean(Integer dayNumber, Long promotionId) {
        this.dayNumber = dayNumber;
        this.promotionId = promotionId;
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddPromotionBean that = (AddPromotionBean) o;
        return Objects.equal(dayNumber, that.dayNumber) &&
                Objects.equal(promotionId, that.promotionId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dayNumber, promotionId);
    }
}
