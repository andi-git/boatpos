package org.boatpos.service.api.bean;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
     * The url for the small picture.
     */
    @NotNull
    @Size(max = 100)
    @Expose
    private String pictureUrlThumb;

    /**
     * The url for the large picture.
     */
    @NotNull
    @Size(max = 100)
    @Expose
    private String pictureUrl;

    /**
     * The keybord-binding for client-input.
     */
    private Character keyBinding;

    public AbstractMasterDataBean() {
    }

    public AbstractMasterDataBean(Long id, Integer version, boolean enabled, Integer priority, Character keyBinding, String pictureUrl, String pictureUrlThumb) {
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
