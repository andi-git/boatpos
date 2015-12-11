package org.boatpos.service.api.bean;

import com.google.common.base.Objects;
import com.google.gson.annotations.Expose;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * The departure of a boat.
 */
@SuppressWarnings("unused")
public class DepartureBean extends AbstractBean {

    /**
     * The {@link BoatBean} of the rental.
     */
    @NotNull
    @Valid
    @Expose
    private BoatBean boatBean;

    /**
     * All {@link CommitmentBean}s of the rental.
     */
    @Valid
    @Expose
    private Set<CommitmentBean> commitmentBeans;

    /**
     * The {@link PromotionBean} of the rental.
     */
    @Valid
    @Expose
    private PromotionBean promotionBean;

    public DepartureBean() {
    }

    public DepartureBean(BoatBean boatBean, Set<CommitmentBean> commitmentBeans, PromotionBean promotionBean) {
        this.boatBean = boatBean;
        this.commitmentBeans = commitmentBeans;
        this.promotionBean = promotionBean;
    }

    public BoatBean getBoatBean() {
        return boatBean;
    }

    public void setBoatBean(BoatBean boatBean) {
        this.boatBean = boatBean;
    }

    public Set<CommitmentBean> getCommitmentBeans() {
        return commitmentBeans;
    }

    public void setCommitmentBeans(Set<CommitmentBean> commitmentBeans) {
        this.commitmentBeans = commitmentBeans;
    }

    public PromotionBean getPromotionBean() {
        return promotionBean;
    }

    public void setPromotionBean(PromotionBean promotionBean) {
        this.promotionBean = promotionBean;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartureBean departureBean = (DepartureBean) o;
        return Objects.equal(boatBean, departureBean.boatBean) &&
                Objects.equal(commitmentBeans, departureBean.commitmentBeans) &&
                Objects.equal(promotionBean, departureBean.promotionBean);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(boatBean, commitmentBeans, promotionBean);
    }
}
