package org.boatpos.model;

import com.google.gson.annotations.Expose;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Expose
    private Character keyBinding;

    /**
     * The url for the small picture.
     */
    @Size(max = 100)
    @Expose
    private String pictureUrlThumb;

    /**
     * The url for the large picture.
     */
    @Size(max = 100)
    @Expose
    private String pictureUrl;

    public AbstractMasterDataEntity() {
    }

    public AbstractMasterDataEntity(Long id, Integer version, boolean enabled, Integer priority, Character keyBinding, String pictureUrl, String pictureUrlThumb) {
        super(id, version);
        this.enabled = enabled;
        this.priority = priority;
        this.keyBinding = keyBinding;
        this.pictureUrl = pictureUrl;
        this.pictureUrlThumb = pictureUrlThumb;
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

    public String getPictureUrlThumb() {
        return pictureUrlThumb;
    }

    public void setPictureUrlThumb(String pictureUrlThumb) {
        this.pictureUrlThumb = pictureUrlThumb;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
