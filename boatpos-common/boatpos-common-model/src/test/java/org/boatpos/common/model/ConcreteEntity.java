package org.boatpos.common.model;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings({"unused", "JpaDataSourceORMInspection"})
public class ConcreteEntity extends AbstractMasterDataEntity {

    @NotNull
    @Size(min = 3, max = 50)
    @Expose
    private String name;

    public ConcreteEntity() {
    }

    public ConcreteEntity(Long id, Integer version, String name, Integer priority, boolean enabled, String pictureUrlThumb, String pictureUrl, Character keyBinding) {
        super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
