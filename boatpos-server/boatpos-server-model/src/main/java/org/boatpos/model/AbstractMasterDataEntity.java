package org.boatpos.model;

import com.google.gson.annotations.Expose;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * This class has all basic-fields for master-data-entities.
 */
@MappedSuperclass
public class AbstractMasterDataEntity extends AbstractEntity {

    /**
     * Flag if this {@link BoatEntity} is enabled or disabled.
     */
    @Expose
    private boolean enabled;

    /**
     * The priority of the {@link BoatEntity}.
     */
    @NotNull
    @Min(0)
    @Expose
    private Integer priority;

    /**
     * The keybord-binding for client-input.
     */
    private Character keyBinding;

    public AbstractMasterDataEntity() {
    }

    public AbstractMasterDataEntity(Long id, Integer version, boolean enabled, Integer priority, Character keyBinding) {
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
