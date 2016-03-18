package org.regkas.model;

import com.google.gson.annotations.Expose;
import org.boatpos.common.model.AbstractMasterDataEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity of a tax-set.
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "user")
public class TaxSetEntity extends AbstractMasterDataEntity {

    @NotNull
    @Size(min = 3, max = 50)
    @Expose
    private String name;

    @NotNull
    @Min(1)
    @Max(99)
    @Expose
    private Integer taxPercent;

    public TaxSetEntity() {
    }

    public TaxSetEntity(Long id, Integer version, boolean enabled, Integer priority, Character keyBinding, String pictureUrl, String pictureUrlThumb, String name, Integer taxPercent) {
        super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
        this.name = name;
        this.taxPercent = taxPercent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(Integer taxPercent) {
        this.taxPercent = taxPercent;
    }
}
