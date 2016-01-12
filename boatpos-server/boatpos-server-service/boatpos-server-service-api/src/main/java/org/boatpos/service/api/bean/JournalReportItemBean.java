package org.boatpos.service.api.bean;

import com.google.common.base.Objects;

import java.math.BigDecimal;

/**
 * An item of the {@link JournalReportBean}.
 */
public class JournalReportItemBean extends AbstractBean {

    private BoatBean boatBean;

    private BigDecimal sum;

    public JournalReportItemBean() {
    }

    public JournalReportItemBean(BoatBean boatBean, BigDecimal sum) {
        this.boatBean = boatBean;
        this.sum = sum;
    }

    public BoatBean getBoatBean() {
        return boatBean;
    }

    public void setBoatBean(BoatBean boatBean) {
        this.boatBean = boatBean;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JournalReportItemBean that = (JournalReportItemBean) o;
        return Objects.equal(boatBean, that.boatBean);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(boatBean);
    }
}
