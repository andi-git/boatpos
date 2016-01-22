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

    /**
     * The keybord-binding for client-input.
     */
    private Character keyBinding;

    public AbstractMasterDataBean() {
    }

    public AbstractMasterDataBean(Long id, Integer version, boolean enabled, Integer priority, Character keyBinding) {
        super(id, version);
        this.enabled = enabled;
        this.priority = priority;
        this.keyBinding = keyBinding;
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

    public Character getKeyBinding() {
        return keyBinding;
    }

    public void setKeyBinding(Character keyBinding) {
        this.keyBinding = keyBinding;
    }
}
