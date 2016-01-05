package org.boatpos.service.api.bean;

import com.google.common.base.Objects;
import com.google.gson.annotations.Expose;

public class BoatCountBean extends AbstractBean {

    @Expose
    private long id;
    @Expose
    private String name;
    @Expose
    private String shortName;
    @Expose
    private int count;
    @Expose
    private int max;

    public BoatCountBean() {
    }

    public BoatCountBean(long id, String name, String shortName, int count, int max) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.count = count;
        this.max = max;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoatCountBean boatCountBean = (BoatCountBean) o;
        return id == boatCountBean.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}