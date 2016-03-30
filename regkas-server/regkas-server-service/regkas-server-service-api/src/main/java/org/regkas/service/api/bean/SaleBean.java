package org.regkas.service.api.bean;

import com.google.gson.annotations.Expose;
import org.boatpos.common.service.api.bean.AbstractBean;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Representation of a sale.
 */
@SuppressWarnings("unused")
public class SaleBean extends AbstractBean {

    @NotNull
    @Expose
    private String paymentMethod;

    @NotNull
    @Expose
    private String receiptType;

    @Valid
    @Expose
    private List<ReceiptElementBean> saleElements = new ArrayList<>();

    public SaleBean() {
    }

    public SaleBean(String paymentMethod, String receiptType, List<ReceiptElementBean> saleElements) {
        this.paymentMethod = paymentMethod;
        this.receiptType = receiptType;
        this.saleElements = saleElements;
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

    public List<ReceiptElementBean> getSaleElements() {
        return saleElements;
    }

    public void setSaleElements(List<ReceiptElementBean> saleElements) {
        this.saleElements = saleElements;
    }
}
