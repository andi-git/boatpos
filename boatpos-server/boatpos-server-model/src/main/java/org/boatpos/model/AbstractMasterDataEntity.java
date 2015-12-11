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
     * Flag if this {@link Boat} is enabled or disabled.
     */
    @Expose
    private boolean enabled;

    /**
     * The priority of the {@link Boat}.
     */
    @NotNull
    @Min(0)
    @Expose
    private Integer priority;

    public AbstractMasterDataEntity() {
    }

    public AbstractMasterDataEntity(Long id, Integer version, boolean enabled, Integer priority) {
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
