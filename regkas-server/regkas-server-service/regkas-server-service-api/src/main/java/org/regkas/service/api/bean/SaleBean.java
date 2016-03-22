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
    private List<SaleElementBean> saleElements = new ArrayList<>();

    public SaleBean() {
    }

    public SaleBean(List<SaleElementBean> saleElements) {
        this.saleElements = saleElements;
    }

    public List<SaleElementBean> getSaleElements() {
        return saleElements;
    }

    public void setSaleElements(List<SaleElementBean> saleElements) {
        this.saleElements = saleElements;
    }
}
