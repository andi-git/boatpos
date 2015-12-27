package org.boatpos.service.api.bean;

import com.google.common.base.Objects;
import com.google.gson.annotations.Expose;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * The departure of a boat.
 */
@SuppressWarnings("unused")
public class DepartureBean extends AbstractBean {

    /**
     * The id of the {@link BoatBean}.
     */
    @NotNull
    @Min(0)
    @Expose
    private Long boatId;

    /**
     * All ids of the {@link CommitmentBean}s.
     */
    @Valid
    @Expose
    private Set<Long> commitmentIds = new HashSet<>();

    /**
     * The id of the {@link PromotionBean}.
     */
    @Min(0)
    @Expose
    private Long promotionId;

    public DepartureBean() {
    }

    public DepartureBean(Long boatId, Set<Long> commitmentIds, Long promotionId) {
        this.boatId = boatId;
        this.commitmentIds = commitmentIds;
        this.promotionId = promotionId;
    }

    public Long getBoatId() {
        return boatId;
    }

    public void setBoatId(Long boatId) {
        this.boatId = boatId;
    }

    public Set<Long> getCommitmentIds() {
        return commitmentIds;
    }

    public void setCommitmentIds(Set<Long> commitmentIds) {
        this.commitmentIds = commitmentIds;
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
        DepartureBean that = (DepartureBean) o;
        return Objects.equal(boatId, that.boatId) &&
                Objects.equal(commitmentIds, that.commitmentIds) &&
                Objects.equal(promotionId, that.promotionId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(boatId, commitmentIds, promotionId);
    }
}
