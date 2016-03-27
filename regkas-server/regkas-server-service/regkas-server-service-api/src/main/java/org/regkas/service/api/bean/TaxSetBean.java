package org.regkas.service.api.bean;

import org.boatpos.common.service.api.bean.AbstractMasterDataBean;

public class TaxSetBean extends AbstractMasterDataBean {

    private String name;

    private Integer taxPercent;

    public TaxSetBean() {
    }

    public TaxSetBean(String name, Integer taxPercent) {
        this.name = name;
        this.taxPercent = taxPercent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(Integer taxPercent) {
        this.taxPercent = taxPercent;
    }
}
