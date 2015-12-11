package org.boatpos.service.api.bean;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Abstract DTO for master-data.
 */
public class AbstractMasterDataBean extends AbstractBeanBasedOnEntity {

    /**
     * Flag if this {@link AbstractMasterDataBean} is enabled or disabled.
     */
    @Expose
    private boolean enabled;

    /**
     * The priority of this {@link BoatBean}.
     */
    @NotNull
    @Min(0)
    @Expose
    private Integer priority;

    public AbstractMasterDataBean() {
    }

    public AbstractMasterDataBean(Long id, Integer version, boolean enabled, Integer priority) {
        super(id, version);
        this.enabled = enabled;
        this.priority = priority;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
