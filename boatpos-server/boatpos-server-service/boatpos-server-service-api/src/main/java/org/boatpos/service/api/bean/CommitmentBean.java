package org.boatpos.service.api.bean;

import com.google.gson.annotations.Expose;
import org.boatpos.common.service.api.bean.AbstractMasterDataBean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Representation of a commitment.
 */
@SuppressWarnings("unused")
public class CommitmentBean extends AbstractMasterDataBean {

    /**
     * The name of the commitment.
     */
    @NotNull
    @Size(min = 3)
    @Expose
    private String name;

    /**
     * Check if a paper is needed.
     */
    @Expose
    private boolean paper;

    public CommitmentBean() {
    }

    public CommitmentBean(Long id, Integer version, String name, boolean paper, Integer priority, boolean enabled, Character keyBinding, String pictureUrl, String pictureUrlThumb) {
        super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
        this.name = name;
        this.paper = paper;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPaper() {
        return paper;
    }

    public void setPaper(boolean paper) {
        this.paper = paper;
    }
}