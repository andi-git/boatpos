package org.regkas.service.api.bean;

import com.google.gson.annotations.Expose;
import org.boatpos.common.service.api.bean.AbstractBean;
import org.boatpos.common.service.api.bean.LocalDateAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Representation of the income.
 */
public class IncomeBean extends AbstractBean {

    @Expose
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate start;

    @Expose
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate end;

    @Expose
    private List<ProductGroupIncomeBean> incomeElements;

    @Expose
    private List<TaxElementBean> taxElements;

    @Expose
    private BigDecimal totalIncome;

    @Expose
    private BigDecimal paymentCash;

    @Expose
    private BigDecimal paymentCard;

    public IncomeBean() {
    }

    public IncomeBean(LocalDate start, LocalDate end, List<ProductGroupIncomeBean> incomeElements, BigDecimal totalIncome, BigDecimal paymentCash, BigDecimal paymentCard, List<TaxElementBean> taxElements) {
        this.start = start;
        this.end = end;
        this.incomeElements = incomeElements;
        this.totalIncome = totalIncome;
        this.paymentCash = paymentCash;
        this.paymentCard = paymentCard;
        this.taxElements = taxElements;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public List<ProductGroupIncomeBean> getIncomeElements() {
        return incomeElements;
    }

    public void setIncomeElements(List<ProductGroupIncomeBean> incomeElements) {
        this.incomeElements = incomeElements;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public List<TaxElementBean> getTaxElements() {
        return taxElements;
    }

    public void setTaxElements(List<TaxElementBean> taxElements) {
        this.taxElements = taxElements;
    }

    public BigDecimal getPaymentCash() {
        return paymentCash;
    }

    public void setPaymentCash(BigDecimal paymentCash) {
        this.paymentCash = paymentCash;
    }

    public BigDecimal getPaymentCard() {
        return paymentCard;
    }

    public void setPaymentCard(BigDecimal paymentCard) {
        this.paymentCard = paymentCard;
    }
}
