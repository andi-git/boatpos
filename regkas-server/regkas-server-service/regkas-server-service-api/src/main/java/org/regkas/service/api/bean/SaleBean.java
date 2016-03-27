package org.regkas.service.api.bean;

import com.google.gson.annotations.Expose;
import org.boatpos.common.service.api.bean.AbstractBean;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Representation of a sale.
 */
@SuppressWarnings("unused")
public class SaleBean extends AbstractBean {

    @Valid
    @Expose
    private List<ReceiptElementBean> saleElements = new ArrayList<>();

    public SaleBean() {
    }

    public SaleBean(List<ReceiptElementBean> saleElements) {
        this.saleElements = saleElements;
    }

    public List<ReceiptElementBean> getSaleElements() {
        return saleElements;
    }

    public void setSaleElements(List<ReceiptElementBean> saleElements) {
        this.saleElements = saleElements;
    }
}
