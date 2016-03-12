package org.boatpos.service.api.bean;

import com.google.gson.annotations.Expose;
import org.boatpos.common.service.api.bean.AbstractBean;

import java.util.ArrayList;
import java.util.List;

/**
 * A summary of the count of the boats.
 */
public class BoatCountSummary extends AbstractBean {

    @Expose
    private List<BoatCountBean> boatCountList = new ArrayList<>();

    public List<BoatCountBean> getBoatCountList() {
        return boatCountList;
    }

    public void setBoatCountList(List<BoatCountBean> boatCountList) {
        this.boatCountList = boatCountList;
    }

    public void addBoatCount(BoatCountBean boatCountBean) {
        boatCountList.add(boatCountBean);
    }

    public void clearBoatCountList() {
        boatCountList.clear();
    }
}
